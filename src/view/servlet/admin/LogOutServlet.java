package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.Component;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 16.01.14
 * Time: 17:58
 * To change this template use File | Settings | File Templates.
 */
public class LogOutServlet extends ProtectedServlet {
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, LogOutServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            request.getSession().removeAttribute("User");
            request.getSession().invalidate();
            ServletHelper.sendForward("/index.jsp", this, request, response);
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
}
