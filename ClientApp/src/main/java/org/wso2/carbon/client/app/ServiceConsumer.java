package org.wso2.carbon.client.app;

import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.commons.logging.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServiceConsumer {

    private static final Log log = LogFactory.getLog(ServiceConsumer.class);

    public static String createResource(String serverHostPort, String tenantId, String tenantDomain, String resourceName) {

        String url = serverHostPort + ClientAppConstants.SERVICE_URL + "/create";

        String jwt = JWTokenGenerator.generateToken(tenantId, tenantDomain, resourceName);

        return callServiceWithHTTPClient(url, jwt , ClientAppConstants.HTTP_POST);
    }

    public static String readResource(String serverHostPort, String tenantId, String tenantDomain, String resourceName) {

        String url = serverHostPort + ClientAppConstants.SERVICE_URL + "/read";

        String jwt = JWTokenGenerator.generateToken(tenantId,tenantDomain, resourceName);

        return callServiceWithHTTPClient(url, jwt , ClientAppConstants.HTTP_GET);
    }

    private static String callServiceWithHTTPClient(String url, String jwt, String method) {

        String output = "";

        log.info("Calling Service : " + url);

        try {
            HttpClientBuilder builder = HttpClientBuilder.create();
            HttpClient client = builder.build();
            Object request;
            HttpResponse response = null;

            if(ClientAppConstants.HTTP_POST.equals(method)) {
                request = new HttpPost(url);
                ((HttpPost)request).addHeader("Accept", "application/json");
                ((HttpPost)request).addHeader("Content-type", "application/json");
                ((HttpPost)request).addHeader("JWT" , jwt);
                //StringEntity entity = new StringEntity(parameters);
                //((HttpPost)request).setEntity(entity);

                response = client.execute(((HttpPost)request));

            } else if (ClientAppConstants.HTTP_GET.equals(method)) {
                request = new HttpGet(url);
                ((HttpGet) request).addHeader("Accept", "application/json");
                ((HttpGet) request).addHeader("Content-type", "application/json");
                ((HttpGet) request).addHeader("JWT" , jwt);
                response = client.execute(((HttpGet) request));
            }

            if(response != null) {
                int statusCode = response.getStatusLine().getStatusCode();
                log.info("Service Retured Status Code : " + statusCode);

                BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

                StringBuffer result = new StringBuffer();
                String line = "";

                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }

                output = result.toString();

                log.info("Service Returned the Output : " + output);
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

        return output;
    }

}
