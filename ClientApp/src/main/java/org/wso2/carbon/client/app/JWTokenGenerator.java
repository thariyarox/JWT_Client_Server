package org.wso2.carbon.client.app;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JWTokenGenerator {

    private static final Log log = LogFactory.getLog(JWTokenGenerator.class);

    public static String generateToken(String tenantId, String tenantDomain, String resourceName) {

        String jwt = "{\"tenant_id\":\"" + tenantId + "\",\"tenant_domain\":\"" + tenantDomain + "\", \"resource_name\":\"" + resourceName + "\"}";

        log.info("JSON WEB TOKEN generated : " + jwt);

        return jwt;
    }
}
