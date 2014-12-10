package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.Component;
import model.logger.LoggerFactory;
import helper.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOutServlet extends ProtectedServlet {
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, LogOutServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            request.getSession().removeAttribute("User");
            request.getSession().invalidate();
            ServletHelper.sendForward("/index", this, request, response);
        } catch (ServletException e) {
            loggerFactory.error(e);

        } catch (IOException e) {
            loggerFactory.error(e);
            throw new ServletException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }


    @Override
    protected AdminRule setAdminRule() {
        return AdminRule.Registered;
    }

    @Override
    public String getTitle() {
        return null;
    }
}
