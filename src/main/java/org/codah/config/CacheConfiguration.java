package org.codah.config;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.enterprise.event.Observes;
import io.quarkus.runtime.StartupEvent;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.exceptions.TransportException;
import org.infinispan.commons.configuration.XMLStringConfiguration;

@Singleton
public class CacheConfiguration {
    private final Logger log = Logger.getLogger(CacheConfiguration.class);

    public static final Long MINUTE = 60*1000L;
    public static final Long HALF_HOUR = 30*60*1000L;
    public static final Long HOUR = 60*60*1000L;
    
    private final RemoteCacheManager remoteCacheManager;

    @ConfigProperty(name = "infinispan.cache-template")
    String cacheConfigTemplate;
    
    @Inject
    public CacheConfiguration(RemoteCacheManager remoteCacheManager) {
        this.remoteCacheManager = remoteCacheManager;
    }

    /**
     * Performs cache initializations.
     */
    void onStart(@Observes StartupEvent ev) {               
        log.info("Creating inifinispan caches ...");
    
        createCacheIfNotExist("authors", HOUR, HOUR);
        //TODO: register other caches here ...
    }

    /**
     * 
     * @param cacheName - Name of the cache to be created
     * @param lifespan - Maximum lifespan of a cache entry, after which the entry is expired cluster-wide, in milliseconds. 
     * @param maxIdle Maximum idle time a cache entry will be maintained in the cache, in milliseconds. 
     */
    public void createCacheIfNotExist(String cacheName, Long lifespan, Long maxIdle) {
        if (cacheExists(cacheName)) {
            log.debugf(">> Cache '%s' ALREADY EXIST --> Skipping", cacheName);
            return;
        }
        String cahceConfig = cacheConfigTemplate.replace("<name-override>", cacheName)
            .replace("<lifespan-override>", lifespan.toString())
            .replace("<maxidle-override>", maxIdle.toString());
        
        remoteCacheManager.administration().createCache(cacheName, new XMLStringConfiguration(cahceConfig));
        log.infof("--> CREATED Cache '%s' with lifespan/maxIdle [%d/%d] (ms)", cacheName, lifespan, maxIdle);
    }

    private boolean cacheExists(String cacheName) {
        try {
            if (remoteCacheManager.getCache(cacheName) == null) {
                log.debugf("Cache %s not present.", cacheName);
                return false;
            }
            return true;
        } catch (TransportException ex) {
            log.errorf("Error while trying to connect to the remote cache: %s", ex.getCause());
            return false;
        }
    }
}
