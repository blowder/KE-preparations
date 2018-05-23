package com.example.rest;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

@Path("/")
public class ExampleRest {
    @Context
    private SecurityContext ctx;

    @Path("ping")
    @GET
    public String service() {
        return "pong";
    }
}
