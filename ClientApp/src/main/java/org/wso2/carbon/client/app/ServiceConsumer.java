package org.wso2.carbon.client.app;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServiceConsumer {

    public static String createResource(String serverHostPort, String tenantId, String tenantDomain, String resourceName) {

        //String url = serverHostPort + ClientAppConstants.SERVICE_URL + "/create/" + resourceName;
        String url = serverHostPort + ClientAppConstants.SERVICE_URL + "/create";

        return callServiceWithHTTPClient(url, JWTokenGenerator.generateToken(tenantId, tenantDomain), ClientAppConstants.HTTP_POST);
    }

    public static String readResource(String serverHostPort, String tenantId, String tenantDomain, String resourceName) {
        String url = serverHostPort + ClientAppConstants.SERVICE_URL + "/read";

        return callServiceWithHTTPClient(url, JWTokenGenerator.generateToken(tenantId,tenantDomain), ClientAppConstants.HTTP_GET);
    }

    private static String callServiceWithHTTPClient(String Url, String JWT, String method) {

        String output = "";

        try {
            HttpClientBuilder builder = HttpClientBuilder.create();
            HttpClient client = builder.build();
            Object request;
            HttpResponse response = null;

            if(ClientAppConstants.HTTP_POST.equals(method)) {
                request = new HttpPost(Url);
                ((HttpPost)request).addHeader("Accept", "application/json");
                ((HttpPost)request).addHeader("Content-type", "application/json");
                ((HttpPost)request).addHeader("JWT" , JWT);
                //StringEntity entity = new StringEntity(parameters);
                //((HttpPost)request).setEntity(entity);

                response = client.execute(((HttpPost)request));

            } else if (ClientAppConstants.HTTP_GET.equals(method)) {
                request = new HttpGet(Url);
                ((HttpGet) request).addHeader("Accept", "application/json");
                ((HttpGet) request).addHeader("Content-type", "application/json");
                ((HttpGet) request).addHeader("JWT" , JWT);
                response = client.execute(((HttpGet) request));
            }

            if(response != null) {
                //response.getStatusLine().getStatusCode();

                BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

                StringBuffer result = new StringBuffer();
                String line = "";

                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }

                output = result.toString();
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

        return output;
    }

}
