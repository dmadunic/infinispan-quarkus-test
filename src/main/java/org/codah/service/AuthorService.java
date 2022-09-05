package org.codah.service;

import org.jboss.logging.Logger;

import io.quarkus.infinispan.client.Remote;

import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.codah.domain.Author;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;

@ApplicationScoped
public class AuthorService {
    private static final Logger log = Logger.getLogger(AuthorService.class);

    private static final String[] LAST_NAMES = { "Inky", "Pinky", "Blinky", "Clide", "Own", "Raynolds", "Redick", "Neuman" };
 
    @Inject @Remote("authors")
    RemoteCache<String, Author> cache;

    RemoteCacheManager remoteCacheManager;
    
    public AuthorService(RemoteCacheManager remoteCacheManager) {
        this.remoteCacheManager = remoteCacheManager;
    }

    public Author getByName(String name) {
        Author authorCached = cache.get(name);
        if (authorCached != null) {
            log.infof("Returning CACHED Auhtor object for firstName=%s", name);
            return authorCached;

        }
        log.infof("Returning new Auhtor with random last name and firstName=%s", name);
        Random random = new Random();
        String lastName = LAST_NAMES[random.nextInt(8)];
        Author author = new Author(name, lastName);
        cache.put(name, author);
        return author;
    }
}
