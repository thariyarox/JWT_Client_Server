package org.wso2.carbon.client.app;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServiceConsumer {

    public static String createResource(String serverHostPort, String tenantId, String tenantDomain, String resourceName) {

        String url = serverHostPort + ClientAppConstants.SERVICE_URL + "/create/" + resourceName;

        return callServiceWithHTTPClient(url);
    }

    public static String readResource(String serverHostPort, String tenantId, String tenantDomain, String resourceName) {
        String url = serverHostPort + ClientAppConstants.SERVICE_URL + "/read/" + resourceName;

        return callServiceWithHTTPClient(url);
    }

    private static String callServiceWithHTTPClient(String Url) {

        String output = "";

        try {
            HttpClientBuilder builder = HttpClientBuilder.create();
            HttpClient client = builder.build();
            HttpGet request = new HttpGet(Url);
            HttpResponse response = client.execute(request);

            //response.getStatusLine().getStatusCode()

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";

            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            output = result.toString();

        } catch (IOException e) {

            e.printStackTrace();
        }

        return output;
    }


}
