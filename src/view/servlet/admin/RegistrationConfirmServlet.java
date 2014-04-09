package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.Component;
import model.logger.LoggerFactory;
import model.mailer.RegistrationMailer;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationConfirmServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, RegistrationConfirmServlet.class);

    @Override
    protected AdminRule setAdminRule() {
        return AdminRule.All;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            ServletHelper.setUTF8(request, response);
            String url = request.getPathInfo().substring(1);
            loggerFactory.error(url);
            boolean isConfirmed = RegistrationMailer.confirmAccount(url);
            if (isConfirmed) {
                response.getOutputStream().print("Email confirmed");
            } else {
                response.getOutputStream().print("Error");
            }
        } catch (Exception e) {
            loggerFactory.error(e);
        }
    }
}
