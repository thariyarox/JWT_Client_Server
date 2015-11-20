package org.wso2.carbon.service.app;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.wso2.carbon.service.app.JWTUtil;

import java.util.List;
import java.util.Map;

@Service
@Path("/service")
public class RegistryService {

    private static final Log log = LogFactory.getLog(RegistryService.class);



    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public String createResource(@Context HttpServletRequest httpServletRequest) {

    String resource_name = "";

        String jwt =  httpServletRequest.getHeader("JWT");

        log.info("JWT Extracted from the request : " + jwt);

        Map<String, String> claims = JWTUtil.getClaimsFromJWT(jwt);
        resource_name = claims.get("resource_name");

        return "You have created " + resource_name;
    }


    @GET
    @Path("/read")
    public String readResource(@Context HttpServletRequest httpServletRequest) {
        String resource_name = "";

        String jwt =  httpServletRequest.getHeader("JWT");

        log.info("JWT Extracted from the request : " + jwt);

        Map<String, String> claims = JWTUtil.getClaimsFromJWT(jwt);
        resource_name = claims.get("resource_name");

        return "You are reading " + resource_name;
    }
}
