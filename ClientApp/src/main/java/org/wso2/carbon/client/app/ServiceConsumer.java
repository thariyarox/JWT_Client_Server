package org.wso2.carbon.client.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class ServiceConsumer {

    public static String createResource(String serverHostPort, String tenantId, String tenantDomain, String resourceName) {

        String url = serverHostPort + ClientAppConstants.SERVICE_URL + "/create/" + resourceName;

        return callService(url);
    }

    public static String readResource(String serverHostPort, String tenantId, String tenantDomain, String resourceName) {
        String url = serverHostPort + ClientAppConstants.SERVICE_URL + "/read/" + resourceName;

        return callService(url);
    }

    private static String callService(String Url){

        String output = "";
        try {
            URL url = new URL(Url);

            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            String tmp = null;

            do{
                tmp = br.readLine();

                if(tmp != null)
                    output += tmp;

            } while(tmp != null);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return output;
    }


}
