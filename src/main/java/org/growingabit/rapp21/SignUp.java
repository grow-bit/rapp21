package org.growingabit.rapp21;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

@WebServlet(
    name = "SignUp",
    description = "SignUp: signup or redirected to /signup-status",
    urlPatterns = "/signup"
)
public class SignUp extends RApP21Servlet {

    private String viewName;

    @Override
    protected void _doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (this.signUp.isPending()) {
            resp.sendRedirect("/signup-status");
        } else {
            this.viewName = "SignUp";
        }
    }

    protected String _getViewName() {
        return this.viewName;
    }
}
