package org.growingabit.rapp21;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

import java.util.*;

import org.apache.commons.lang3.StringUtils;

@WebServlet(
    name = "SignUp",
    description = "SignUp: signup or redirected to /signup-status",
    urlPatterns = "/signup"
)
public class SignUp extends RApP21Servlet {

    private String viewName;

    @Override
    protected void _doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.viewName = "SignUp";
    }

    @Override
    protected void _doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // setto a null perche' faremo un redirect
        this.viewName = null;

        Map<String, String[]> params = req.getParameterMap();
        String firstName = params.containsKey("first_name") ? params.get("first_name")[0] : null;
        String lastName = params.containsKey("last_name") ? params.get("last_name")[0] : null;
        String school = params.containsKey("school") ? params.get("school")[0] : null;

        if (!StringUtils.isBlank(firstName) && !StringUtils.isBlank(lastName) && !StringUtils.isBlank(school)) {
            this.signUp.complete(firstName, lastName, school);
        }

        resp.sendRedirect("/");
    }

    protected String _getViewName() {
        return this.viewName;
    }
}
