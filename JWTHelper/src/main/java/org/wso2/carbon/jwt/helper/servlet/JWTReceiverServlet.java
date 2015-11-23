package org.wso2.carbon.jwt.helper.servlet;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.jwt.helper.JWTHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTReceiverServlet extends HttpServlet {

    private static final long serialVersionUID = -7182121722709941646L;
    private static final Log log = LogFactory.getLog(JWTReceiverServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("JWTReceiver Servlet doGet got Hit !");

        String jwt = "";

        try {
            //Accept the tenantDomain as a parameter and call signJWT method;
            String tenantDomain = req.getParameter("tenant_domain");
            if (StringUtils.isBlank(tenantDomain)) {
                //Default tenant
                tenantDomain = "carbon.super";
            }
            jwt = JWTHelper.generateJWT(tenantDomain);
        } catch (Exception e) {
            log.error("error", e);
        }

        resp.setContentType("text/plain");
        resp.getWriter().write("Generated JWT = " + jwt);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("JWTReceiver Servlet doPost got Hit !");

        String jwt = "";

        try {

            //Accept the tenantDomain as a parameter and call signJWT method
            String tenantDomain = req.getParameter("tenant_domain");

            if (StringUtils.isBlank(tenantDomain)) {
                //Default tenant
                tenantDomain = "carbon.super";
            }

            jwt = JWTHelper.generateJWT(tenantDomain);
        } catch (Exception e) {
            log.error("error", e);
        }


        resp.setContentType("text/plain");
        resp.getWriter().write("Generated JWT = " + jwt);
    }
}
