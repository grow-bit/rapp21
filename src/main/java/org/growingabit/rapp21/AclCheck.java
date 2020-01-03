package org.growingabit.rapp21;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

@WebServlet(
    name = "AclCheck",
    description = "AclCheck: check if user has access",
    urlPatterns = "/acl-check"
)
public class AclCheck extends RApP21Servlet {

    @Override
    protected void _doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userEmail = (String) req.getAttribute(RApP21Servlet.USER_EMAIL_ATTRIBUTE);
        String redirectTo = req.getParameter(RApP21Servlet.REDIRECT_TO_PARAM);
        req.setAttribute("aclCheckMessage", "L'utente " + userEmail + " e' autorizzato, redirect to " + redirectTo);
    }

    @Override
    protected String _getViewName() {
        return "AclCheck";
    }
}
