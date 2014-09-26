package view.servlet.admin;

import model.additionalentity.admin.CompleteMenuInfo;
import model.additionalentity.admin.CompleteTagGroupInfo;
import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.MenuRequest;
import model.database.requests.TagRequest;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CompleteTagGroupInfoServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, CompleteTagGroupInfoServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            ServletHelper.setUTF8(request, response);
            Long tagGroupID = Long.parseLong(request.getParameter("tagGroupID"));
            CompleteTagGroupInfo tagGroupInfo = TagRequest.getCompleteTagGroupInfo(tagGroupID);
            if (tagGroupInfo != null) {
                request.setAttribute("tagGroup", tagGroupInfo);

                request.setAttribute("title", cutTitle("Группа тегов [" +
                        tagGroupInfo.getTagGroupID() +
                        "]" +
                        tagGroupInfo.getName()));
                ServletHelper.sendForward("/completetaggroupinfo.jsp?tagGroupID=" + tagGroupID, this, request, response);
            }
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
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
