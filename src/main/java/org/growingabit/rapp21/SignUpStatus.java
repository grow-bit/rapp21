package org.growingabit.rapp21;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

@WebServlet(
    name = "SignUpStatus",
    description = "SignUpStatus: check signup status",
    urlPatterns = "/signup-status"
)
public class SignUpStatus extends RApP21Servlet {

    public static final String SIGNUP_ATTRIBUTE = "signUp";

    @Override
    protected void _doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(SIGNUP_ATTRIBUTE, this.signUp.toMap());
    }

    protected String _getViewName() {
        return "SignUpStatus";
    }
}
