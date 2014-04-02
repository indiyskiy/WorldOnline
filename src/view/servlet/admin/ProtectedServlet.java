package view.servlet.admin;

import model.ServerInit;
import model.constants.AccessStatus;
import model.constants.AdminRule;
import model.constants.Component;
import model.constants.ProtectAdminLevel;
import model.database.requests.AdminUserRequest;
import model.logger.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;

public abstract class ProtectedServlet extends HttpServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, ProtectedServlet.class);
    private AdminRule adminRule;
    private ServerInit serverInit = ServerInit.getInstance();

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loggerFactory.info("service");
        this.adminRule = setAdminRule();
        HttpSession session = request.getSession(false);
        AccessStatus isPassed;

        isPassed = getIsPassed(session);
        if (isPassed != AccessStatus.Accept) {
            forward(getPageName(isPassed), request, response);
            return;
        }
        super.service(request, response);
    }

    private AccessStatus getIsPassed(HttpSession session) {
        if (session == null) {
            if (adminRule == AdminRule.Unregistered) {
                return AccessStatus.Accept;
            } else {
                loggerFactory.info("den 1");
                return AccessStatus.Denied;
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
                loggerFactory.info("den 2");
                return AccessStatus.Denied;
            }
        }
        if (!sessionKey.getClass().equals(String.class)) {
            return AccessStatus.NotLoggedIn;
        }
        String key = sessionKey.toString();
        ProtectAdminLevel protectAdminLevel = AdminUserRequest.getProtectedLevel(key);
        if (adminRule == AdminRule.Unregistered && protectAdminLevel != ProtectAdminLevel.Unregistered) {
            return AccessStatus.TooRegistered;
        }
        if (!adminRule.getAccessStatusMap().contains(protectAdminLevel)) {
            loggerFactory.info("den 3 " + protectAdminLevel);
            return AccessStatus.Denied;
        }
        return AccessStatus.Accept;
    }

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
                return "/login";
            case TooRegistered:
                return "/index";
            default:
                return "/unknownError.jsp";
        }
    }
}
