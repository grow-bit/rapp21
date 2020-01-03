package org.growingabit.rapp21;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

@WebServlet(
    name = "Acl",
    description = "Acl: check if user has access",
    urlPatterns = "/acl"
)
public class Acl extends RApP21Servlet {

    @Override
    protected void _doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirectTo = req.getParameter(RApP21Servlet.REDIRECT_TO_PARAM);
        String authorized = this.acl.check() ? "ok" : "ko";
        req.setAttribute("aclCheckMessage", "L'utente " + this.acl.getUserEmail() + " e' " + authorized + ", redirect to " + redirectTo);
    }

    @Override
    protected String _getViewName() {
        return "Acl";
    }
}
