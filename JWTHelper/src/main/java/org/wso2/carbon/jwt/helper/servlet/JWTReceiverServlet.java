package org.wso2.carbon.jwt.helper.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JWTReceiverServlet extends HttpServlet{

    private static final long serialVersionUID = -7182121722709941646L;
    private static final Log log = LogFactory.getLog(JWTReceiverServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("JWTReceiver Servlet doGet Hit !");
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("JWTReceiver Servlet doPost Hit !");
        super.doPost(req, resp);
    }
}
