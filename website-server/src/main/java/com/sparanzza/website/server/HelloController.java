package com.sparanzza.website.server;

import com.google.inject.Inject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloController {

    @Inject
    HelloService helloService;

    @GET
    @Path("/sayhello")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello(){
        return helloService.sayHello();
    }
}
