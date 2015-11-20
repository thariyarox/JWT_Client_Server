package org.wso2.carbon.service.app;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Service
@Path("/service")
public class RegistryService {


    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public String createResource(@Context HttpServletRequest httpServletRequest) {

    String resource_name = "";

        String JWToken =  httpServletRequest.getHeader("JWT");

        return "You have created " + resource_name;
    }


    @GET
    @Path("/read")
    public String readResource(@Context HttpServletRequest httpServletRequest) {
        String resource_name = "";

        String JWToken =  httpServletRequest.getHeader("JWT");

        return "You are reading " + resource_name;
    }
}
