package view.servlet.admin;

import model.additionalentity.CompleteTextGroupInfo;
import model.constants.AdminRule;
import model.constants.Component;
import model.constants.ProtectAdminLevel;
import model.constants.databaseenumeration.*;
import model.database.requests.TextRequest;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 13.01.14
 * Time: 14:54
 * To change this template use File | Settings | File Templates.
 */
public class CompleteTextGroupInfoServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, CompleteTextGroupInfoServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            request.setCharacterEncoding("UTF-8");
            request.setAttribute("languages", LanguageType.values());
            String textGroupIDString = request.getParameter("TextGroupID");
            if (textGroupIDString != null && !textGroupIDString.isEmpty()) {
                long textGroupID = Long.parseLong(textGroupIDString);
                CompleteTextGroupInfo completeTextGroupInfo = TextRequest.getCompleteTextGroupInfo(textGroupID);
                request.setAttribute("textGroup", completeTextGroupInfo.getTextGroup());
                request.setAttribute("textes", completeTextGroupInfo.getTextEntityMap().values());
                ServletHelper.sendForward("/completetextgroupinfo.jsp?TextGroupID=" + textGroupID, this, request, response);
            }
        } catch (Exception e) {
            loggerFactory.error(e.toString());
            throw new ServletException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }


    @Override
    protected AdminRule setAdminRule() {
        return AdminRule.Moderator;
    }
}
