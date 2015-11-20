package org.wso2.carbon.service.app;

import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Service
@Path("/service")
public class RegistryService {


    @GET
    @Path("/create/{resource_name}")
    public String createResource(@PathParam("resource_name") String resource_name) {
        return "You have created " + resource_name;
    }


    @GET
    @Path("/read/{resource_name}")
    public String readResource(@PathParam("resource_name") String resource_name) {
        return "You are reading " + resource_name;
    }
}
