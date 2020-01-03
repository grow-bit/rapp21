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

    public static final String USER_EMAIL_ATTRIBUTE = "userEmail";
    public static final String LOGOUT_URL_ATTRIBUTE = "logoutUrl";
    public static final String VIEW_NAME_ATTRIBUTE = "viewName";

    protected SignUpEntity signUp;
    protected RApP21Mail mail;

    @Override
    public void init() throws ServletException {
        this.mail = new RApP21Mail();
    }

    @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
        if(this.isLoginOk(req, resp)) {
            this._doPost(req, resp);

            this.view(req, resp);
        }
    }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
        if(this.isLoginOk(req, resp)) {
            this._doGet(req, resp);

            this.view(req, resp);
        }
    }

    private void view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String viewName = this._getViewName();
        if (viewName != null) {
            req.setAttribute(VIEW_NAME_ATTRIBUTE, viewName);
            RequestDispatcher rd = req.getRequestDispatcher(viewName + ".jsp");
            rd.forward(req, resp);
        }
    }

    private Boolean isLoginOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (this._requireLogin()) {
            UserService userService = UserServiceFactory.getUserService();
            String reqUri = req.getRequestURI();

            if (req.getUserPrincipal() == null) {
                resp.setContentType("text/html");
                resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
                return false;
            }

            this.signUp = new SignUpEntity(req.getUserPrincipal().getName(), this.mail);

            req.setAttribute(USER_EMAIL_ATTRIBUTE, this.signUp.getUserEmail());
            req.setAttribute(LOGOUT_URL_ATTRIBUTE, userService.createLogoutURL("/logout"));

            if (!this.signUp.isCompleted()) {
                if (reqUri.equals("/signup")) {
                    // signup non completato, puo' entrare su signup per inserire i dati
                    return true;
                } else {
                    // professore deve inserire i dati di signup
                    resp.sendRedirect("/signup");
                    return false;
                }
            }

            if (this.signUp.isPending() && !reqUri.equals("/signup-status")) {
                // signup del professore deve essere validato
                resp.sendRedirect("/signup-status");
                return false;
            }
        }

        return true;
    }

    protected void _doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    protected void _doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    protected String _getViewName() {
        return null;
    }

    protected Boolean _requireLogin() {
        return true;
    }
}
