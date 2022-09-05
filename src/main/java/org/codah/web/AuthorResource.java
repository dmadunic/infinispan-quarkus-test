package org.codah.web;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.codah.domain.Author;
import org.codah.service.AuthorService;

@Path("/author")
public class AuthorResource {
    
    @Inject
    AuthorService authorService;
    
    @GET
    @Path("/{name}")
    public Author getAuthor(@PathParam("name") String name) { 
        return authorService.getByName(name);
    }

}
