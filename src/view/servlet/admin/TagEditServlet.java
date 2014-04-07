package view.servlet.admin;

import controller.parser.adminparser.TagEditParser;
import model.additionalentity.CompleteTagInfo;
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

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 20.11.13
 * Time: 12:57
 * To change this template use File | Settings | File Templates.
 */
public class TagEditServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, TagEditServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            request.setAttribute("TagTypes", TagType.values());
            String tagIDString = request.getParameter("TagID");
            if (tagIDString != null) {
                int tagID = Integer.parseInt(tagIDString);
                CompleteTagInfo tagInfo = TagRequest.getCompleteTag(tagID);
                if (tagInfo != null && tagInfo.getTagEntity() != null) {
                    request.setAttribute("tag", tagInfo.getTagEntity());
                }
                ServletHelper.sendForward("/tagedit.jsp?TagID=" + tagIDString, this, request, response);
            }
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            request.setCharacterEncoding("UTF-8");
            TagEditParser parser = new TagEditParser();
            TagEntity tagEntity = parser.parse(request);
            if (parser.hasNoErrors()) {
                TagRequest.editTag(tagEntity);
            } else {
                throw new ServletException(parser.getErrorsForHTML());
            }
            doGet(request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    @Override
    protected AdminRule setAdminRule() {
        return AdminRule.HeightModerator;
    }
}
