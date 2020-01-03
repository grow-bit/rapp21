package org.growingabit.rapp21;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import com.google.appengine.api.utils.SystemProperty;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

import org.growingabit.rapp21.utils.*;

public abstract class RApP21Servlet extends HttpServlet {

    public static final String USER_EMAIL_ATTRIBUTE = "userEmail";
    public static final String LOGOUT_URL_ATTRIBUTE = "logoutUrl";
    public static final String VIEW_NAME_ATTRIBUTE = "viewName";
    public static final String REDIRECT_TO_PARAM = "redirect_to";

    protected Acl acl;

    @Override
    public void init() throws ServletException {
        this.acl = new Acl();
    }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

        if (this._requireLogin()) {
            UserService userService = UserServiceFactory.getUserService();

            if (req.getUserPrincipal() == null) {
                resp.setContentType("text/html");
                resp.sendRedirect(userService.createLoginURL("/acl-check?" + REDIRECT_TO_PARAM + "=" + req.getRequestURI()));
                return;
            }

            String userEmail = req.getUserPrincipal().getName().toLowerCase();
            this.acl.setUserEmail(userEmail);

            req.setAttribute(USER_EMAIL_ATTRIBUTE, userEmail);
            req.setAttribute(LOGOUT_URL_ATTRIBUTE, userService.createLogoutURL("/logout"));
        }
        
        this._doGet(req, resp);

        String viewName = this._getViewName();
        if (viewName != null) {
            req.setAttribute(VIEW_NAME_ATTRIBUTE, viewName);
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
