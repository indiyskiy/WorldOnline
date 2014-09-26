package view.servlet.admin;

import controller.parser.adminparser.TagEditParser;
import model.additionalentity.admin.CompleteTagInfo;
import model.constants.AdminRule;
import model.constants.Component;
import model.constants.databaseenumeration.TagType;
import model.database.requests.TagRequest;
import model.database.worldonlinedb.TagEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TagEditServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, TagEditServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            request.setAttribute("TagTypes", TagType.values());
            String tagIDString = request.getParameter("tagID");
            if (tagIDString != null) {
                int tagID = Integer.parseInt(tagIDString);
                CompleteTagInfo tagInfo = TagRequest.getCompleteTag(tagID);
                if (tagInfo != null) {
                    request.setAttribute("tag", tagInfo);
                    request.setAttribute("title", cutTitle("Тэг [" +
                            tagInfo.getTagID() +
                            "] " +
                            tagInfo.getTagName()));
                }
                ServletHelper.sendForward("/tagedit.jsp?tagID=" + tagIDString, this, request, response);
            }
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        try {
//            request.setCharacterEncoding("UTF-8");
//            TagEditParser parser = new TagEditParser();
//            TagEntity tagEntity = parser.parse(request);
//            if (parser.hasNoErrors()) {
//                TagRequest.editTag(tagEntity);
//            } else {
//                throw new ServletException(parser.getErrorsForHTML());
//            }
//            doGet(request, response);
//        } catch (Exception e) {
//            ServletHelper.sendError(e, request, response, this, loggerFactory);
//        }
        doGet(request, response);
    }

    @Override
    protected AdminRule setAdminRule() {
        return AdminRule.HeightModerator;
    }

    @Override
    public String getTitle() {
        return null;
    }
}
