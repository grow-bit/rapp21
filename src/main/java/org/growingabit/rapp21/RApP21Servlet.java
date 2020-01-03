package org.growingabit.rapp21;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import com.google.appengine.api.utils.SystemProperty;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

public abstract class RApP21Servlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

        if (this._requireLogin()) {
            UserService userService = UserServiceFactory.getUserService();

            if (req.getUserPrincipal() == null) {
                resp.setContentType("text/html");
                resp.sendRedirect(userService.createLoginURL("/acl-check?redirect_to=" + req.getRequestURI()));
                return;
            }

            req.setAttribute("username", req.getUserPrincipal().getName());
            req.setAttribute("logoutUrl", userService.createLogoutURL("/logout"));
        }
        
        this._doGet(req, resp);

        String viewName = this._getViewName();
        if (viewName != null) {
            req.setAttribute("viewName", viewName);
            RequestDispatcher rd = req.getRequestDispatcher(viewName + ".jsp");
            rd.forward(req, resp);
        }
    }

    protected abstract void _doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    protected String _getViewName() {
        return null;
    }

    protected Boolean _requireLogin() {
        return true;
    }
}
