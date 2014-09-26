package view.servlet.admin;

import model.additionalentity.CompleteTextGroupInfo;
import model.constants.AdminRule;
import model.constants.Component;
import model.constants.databaseenumeration.LanguageType;
import model.database.requests.TextRequest;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CompleteTextGroupInfoServlet extends ProtectedServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, CompleteTextGroupInfoServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            request.setCharacterEncoding("UTF-8");
            request.setAttribute("languages", LanguageType.values());
            String textGroupIDString = request.getParameter("textGroupID");
            if (textGroupIDString != null && !textGroupIDString.isEmpty()) {
                long textGroupID = Long.parseLong(textGroupIDString);
                CompleteTextGroupInfo completeTextGroupInfo = TextRequest.getCompleteTextGroupInfo(textGroupID);
                request.setAttribute("textGroup", completeTextGroupInfo);
                request.setAttribute("textes", TextRequest.getFullTextMap(completeTextGroupInfo.getTextMap()));
                request.setAttribute("title", cutTitle("Группа текстов [" +
                        completeTextGroupInfo.getTextGroupID() +
                        "]" +
                        completeTextGroupInfo.getTextGroupName()));
                request.setAttribute("hidden", ServletHelper.saveAllParamsToHiddenInputType(request));
                ServletHelper.sendForward("/completetextgroupinfo.jsp?textGroupID=" + textGroupID, this, request, response);
            }
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }


    @Override
    protected AdminRule setAdminRule() {
        return AdminRule.Moderator;
    }

    @Override
    public String getTitle() {
        return null;
    }
}
