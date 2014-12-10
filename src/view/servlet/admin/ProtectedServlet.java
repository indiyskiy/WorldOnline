package view.servlet.admin;

import model.ServerInit;
import model.constants.AccessStatus;
import model.constants.AdminRule;
import model.constants.Component;
import model.constants.ProtectAdminLevel;
import model.database.requests.AdminUserRequest;
import model.logger.LoggerFactory;
import helper.ServletHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public abstract class ProtectedServlet extends HttpServlet {
    private static final String TRIPLE_DOTS = "...";
    private final int MAX_TITLE_LENGTH = 43;
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, ProtectedServlet.class);
    private AdminRule adminRule;
    private boolean registered = false;
    private ServerInit serverInit = ServerInit.getInstance();
    protected String pageFrom;
    private HttpServletRequest request;

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            this.request = request;
            this.pageFrom = request.getRequestURI().substring(1);
            this.pageFrom = pageFrom.substring(pageFrom.indexOf("/")) + "?" + request.getQueryString();
            this.adminRule = setAdminRule();
            if (this.adminRule == null) {
                throw new ServletException("admin rule for this page is null");
            }
            HttpSession session = request.getSession(false);
            AccessStatus isPassed;
            isPassed = getIsPassed(session);
            request.setAttribute("registered", registered);
            request.setAttribute("pageFrom", pageFrom);
            if (isPassed != AccessStatus.Accept) {
                forward(getPageName(isPassed), request, response);
                return;
            }
            if (getTitle() != null) {
                request.setAttribute("title", cutTitle(getTitle()));
            }
            super.service(request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    protected String cutTitle(String title) {
        if (title.length() >= MAX_TITLE_LENGTH) {
            int halfValue = (MAX_TITLE_LENGTH - TRIPLE_DOTS.length()) / 2;
            return title.substring(0, halfValue) + TRIPLE_DOTS + title.substring(title.length() - halfValue, title.length());
        }
        return "<a href=\"\">" + title + "</a>";
    }

    private AccessStatus getIsPassed(HttpSession session) {
        if (session == null) {
            return isPassedForUnLoggined();
        } else {
            Object sessionKey = session.getAttribute("sessionKey");
            if (sessionKey == null) {
                return isPassedForUnLoggined();
            } else {
                String key = sessionKey.toString();
                ProtectAdminLevel protectAdminLevel = AdminUserRequest.getProtectedLevel(key);
                if (protectAdminLevel == null || protectAdminLevel == ProtectAdminLevel.Unregistered) {
                    return isPassedForUnLoggined();
                } else {
                    registered = true;
                    if (adminRule == AdminRule.Unregistered) {
                        return AccessStatus.TooRegistered;
                    }
                    if (!adminRule.getAccessStatusMap().contains(protectAdminLevel)) {
                        return AccessStatus.Denied;
                    }
                    return AccessStatus.Accept;
                }
            }
        }
    }

    private AccessStatus isPassedForUnLoggined() {
        if (adminRule.getAccessStatusMap().contains(ProtectAdminLevel.Unregistered)) {
            return AccessStatus.Accept;
        } else {
            return AccessStatus.NotLoggedIn;
        }
    }



  /*  private AccessStatus getIsPassed(HttpSession session) {
        if (session == null) {
            if (adminRule.getAccessStatusMap().contains(ProtectAdminLevel.Unregistered)) {
                return AccessStatus.Accept;
            } else {
                return AccessStatus.NotLoggedIn;
            }
        }
        Object sessionKey = session.getAttribute("sessionKey");
        if (adminRule == AdminRule.All) {
            return AccessStatus.Accept;
        }
        if (sessionKey == null) {
            if (adminRule == AdminRule.Unregistered) {
                return AccessStatus.Accept;
            } else {
                return AccessStatus.Denied;
            }
        }
        if (!sessionKey.getClass().equals(String.class)) {
            return AccessStatus.NotLoggedIn;
        }
        String key = sessionKey.toString();
        ProtectAdminLevel protectAdminLevel = AdminUserRequest.getProtectedLevel(key);
        if(protectAdminLevel!=ProtectAdminLevel.Unregistered){
            registered=true;
        }
        if (adminRule == AdminRule.Unregistered && protectAdminLevel != ProtectAdminLevel.Unregistered) {
            return AccessStatus.TooRegistered;
        }
        if (!adminRule.getAccessStatusMap().contains(protectAdminLevel)) {
            return AccessStatus.Denied;
        }
        return AccessStatus.Accept;
    }*/


    protected abstract AdminRule setAdminRule();

    private void forward(String page, HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    protected String getPageName(AccessStatus isPassed) {
        switch (isPassed) {
            case Accept:
                return "/unknownError.jsp";
            case Denied:
                return "/denied.jsp";
            case NotLoggedIn:
                request.getSession().setAttribute("pageFrom", pageFrom);
                return "/login";
            case TooRegistered:
                return "/index";
            default:
                return "/unknownError.jsp";
        }
    }

    public boolean isRegistered() {
        return registered;
    }

    abstract public String getTitle();

}
