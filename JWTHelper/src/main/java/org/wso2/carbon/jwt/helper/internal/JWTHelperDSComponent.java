package org.wso2.carbon.jwt.helper.internal;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.equinox.http.helper.ContextPathServletAdaptor;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.osgi.util.tracker.ServiceTracker;
import org.wso2.carbon.jwt.helper.HttpServiceTracker;
import org.wso2.carbon.jwt.helper.servlet.JWTReceiverServlet;
import org.wso2.carbon.user.core.service.RealmService;

import org.osgi.service.http.HttpService;

import javax.servlet.Servlet;

/**
 * @scr.component name="jwt.helper.dscomponent" immediate=true
 * @scr.reference name="realm.service"
 * interface="org.wso2.carbon.user.core.service.RealmService"cardinality="1..1"
 * policy="dynamic" bind="setRealmService" unbind="unsetRealmService"
 */


public class JWTHelperDSComponent {

    private static Log log = LogFactory.getLog(JWTHelperDSComponent.class);

    private static RealmService realmService;

    private HttpService httpService;

    public static final String JWT_RECEIVER_SERVLET_URL = "/jwtreceiver";

    private HttpServiceTracker serviceTracker;

    protected void activate(ComponentContext ctxt) {

        HttpServiceTracker serviceTracker = new HttpServiceTracker((BundleContext) ctxt);

        serviceTracker.open();
        
        
        log.info("JWTHelper bundle activated successfully..");
    }

    protected void deactivate(ComponentContext ctxt) {

        serviceTracker.close();
        serviceTracker = null;

        if (log.isDebugEnabled()) {
            log.debug("JWTHelper bundle is deactivated ");
        }
    }

    protected void setRealmService(RealmService realmService) {
        if (log.isDebugEnabled()) {
            log.debug("Setting the Realm Service");
        }
        JWTHelperDSComponent.realmService = realmService;
    }

    protected void unsetRealmService(RealmService realmService) {
        if (log.isDebugEnabled()) {
            log.debug("UnSetting the Realm Service");
        }
        JWTHelperDSComponent.realmService = null;
    }

    public static RealmService getRealmService() {
        return realmService;
    }

}
