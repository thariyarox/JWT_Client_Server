package org.wso2.carbon.jwt.helper.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.equinox.http.helper.ContextPathServletAdaptor;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.http.HttpService;
import org.wso2.carbon.jwt.helper.JWTHelperDataHolder;
import org.wso2.carbon.jwt.helper.servlet.JWTReceiverServlet;
import org.wso2.carbon.user.core.service.RealmService;

import javax.servlet.Servlet;

/**
 * @scr.component name="jwt.helper.dscomponent" immediate=true
 * @scr.reference name="realm.service"
 * interface="org.wso2.carbon.user.core.service.RealmService"cardinality="1..1"
 * policy="dynamic" bind="setRealmService" unbind="unsetRealmService"
 * @scr.reference name="osgi.httpservice" interface="org.osgi.service.http.HttpService"
 * cardinality="1..1" policy="dynamic" bind="setHttpService"
 * unbind="unsetHttpService"
 */


public class JWTHelperDSComponent {

    private static Log log = LogFactory.getLog(JWTHelperDSComponent.class);

    public static final String JWT_RECEIVER_SERVLET_URL = "/jwtgenerator";

    protected void activate(ComponentContext ctxt) {

        // Register JWT Receiver Servlet
        Servlet jwtReceiverServlet = new ContextPathServletAdaptor(new JWTReceiverServlet(), JWT_RECEIVER_SERVLET_URL);
        try {

            JWTHelperDataHolder.getInstance().getHttpService().registerServlet(JWT_RECEIVER_SERVLET_URL, jwtReceiverServlet, null, null);
        } catch (Exception e) {
            String errMsg = "Error when registering JWT Receiver Servlet via the HttpService.";
            log.error(errMsg, e);
            throw new RuntimeException(errMsg, e);
        }

        log.info("JWTHelper bundle activated successfully..");
    }

    protected void deactivate(ComponentContext ctxt) {

        if (log.isDebugEnabled()) {
            log.debug("JWTHelper bundle is deactivated ");
        }
    }

    protected void setRealmService(RealmService realmService) {

        JWTHelperDataHolder.getInstance().setRealmService(realmService);

        if (log.isDebugEnabled()) {
            log.debug("Setting the Realm Service");
        }
    }

    protected void unsetRealmService(RealmService realmService) {

        JWTHelperDataHolder.getInstance().setRealmService(null);

        if (log.isDebugEnabled()) {
            log.debug("UnSetting the Realm Service");
        }
    }

    public static RealmService getRealmService() {
        return JWTHelperDataHolder.getInstance().getRealmService();
    }

    protected void setHttpService(HttpService httpService) {

        JWTHelperDataHolder.getInstance().setHttpService(httpService);

        if (log.isDebugEnabled()) {
            log.info("HTTP Service is set in the JWTHelper bundle");
        }
    }

    protected void unsetHttpService(HttpService httpService) {

        JWTHelperDataHolder.getInstance().setHttpService(null);
        if (log.isDebugEnabled()) {
            log.debug("HTTP Service is unset in the JWTHelper bundle");
        }
    }

}
