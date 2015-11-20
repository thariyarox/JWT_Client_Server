package org.wso2.carbon.client.app;

/**
 * Created by tharindu on 11/19/15.
 */
public class JWTokenGenerator {

    public static String generateToken(String tenantId, String tenantDomain){
        String JWT = "{\"tenant_id\":\"" + tenantId + "\",\"tenant_domain\":\"" + tenantDomain + "\"}";

        return JWT;
    }
}
