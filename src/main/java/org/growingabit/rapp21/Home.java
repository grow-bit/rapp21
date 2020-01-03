package org.growingabit.rapp21;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

// [START example]
import com.google.appengine.api.utils.SystemProperty;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

@WebServlet(
    name = "Home",
    description = "Home: rapp21 home with login",
    urlPatterns = "/"
)
public class Home extends RApP21Servlet {

    @Override
    protected void _doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected String _getViewName() {
        return "Home";
    }
}
