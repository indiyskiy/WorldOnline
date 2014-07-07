package view.servlet.admin;

import model.additionalentity.admin.SimpleTagGroup;
import model.constants.AdminRule;
import model.constants.Component;
import model.constants.databaseenumeration.LanguageType;
import model.database.requests.TagRequest;
import model.database.worldonlinedb.TagEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AllTagsServlet extends ProtectedServlet {

    private LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, AllTagsServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ServletHelper.setUTF8(request, response);
            if (request.getParameter("TagGroup") == null || request.getParameter("TagGroup").isEmpty()) {
                ArrayList<TagEntity> tagEntities = TagRequest.getTags();
                request.setAttribute("tags", tagEntities);
            } else {
                long tagGroup = Long.parseLong(request.getParameter("TagGroup"));
                ArrayList<TagEntity> tagEntities = TagRequest.getTags(tagGroup);
                request.setAttribute("tags", tagEntities);
            }
            ArrayList<SimpleTagGroup> simpleTagGroups = TagRequest.getAllSimpleTagGroups(LanguageType.Russian);
            request.setAttribute("tagGroups", simpleTagGroups);
            ServletHelper.sendForward("/alltags.jsp", this, request, response);
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
}
