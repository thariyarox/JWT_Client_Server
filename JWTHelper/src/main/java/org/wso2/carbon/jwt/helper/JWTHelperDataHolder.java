package org.wso2.carbon.jwt.helper;

import org.osgi.service.http.HttpService;
import org.wso2.carbon.user.core.service.RealmService;

public class JWTHelperDataHolder {

    private static volatile JWTHelperDataHolder jwtHelperDataHolder = null;

    private RealmService realmService;

    private HttpService httpService;

    private JWTHelperDataHolder() {

    }

    public static JWTHelperDataHolder getInstance() {

        if (jwtHelperDataHolder == null) {
            synchronized (JWTHelperDataHolder.class) {
                if (jwtHelperDataHolder == null) {
                    jwtHelperDataHolder = new JWTHelperDataHolder();
                }
            }
        }

        return jwtHelperDataHolder;
    }

    public HttpService getHttpService() {
        return httpService;
    }

    public void setHttpService(HttpService httpService) {
        this.httpService = httpService;
    }

    public RealmService getRealmService() {
        return realmService;
    }

    public void setRealmService(RealmService realmService) {
        this.realmService = realmService;
    }

}
