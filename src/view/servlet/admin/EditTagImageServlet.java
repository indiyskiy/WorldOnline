package view.servlet.admin;

import controller.parser.adminparser.ImageTagUploadParser;
import helper.ImageHelper;
import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.TagRequest;
import model.database.worldonlinedb.TagEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class EditTagImageServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, EditTagImageServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            ImageTagUploadParser imageTagUploadParser = new ImageTagUploadParser();
            imageTagUploadParser.parse(request);
            TagEntity tagEntity = imageTagUploadParser.getTagEntity();
            File file = (File) imageTagUploadParser.getFileMap().values().toArray()[0];
            ImageHelper.saveTagIcon(file, tagEntity);
            long tagID = imageTagUploadParser.getTagID();
            ServletHelper.sendForward("/tagedit?tagID=" + tagID, this, request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            long tagID;
            try {
                tagID = Long.parseLong(request.getParameter("tagID"));
                TagEntity tagEntity = TagRequest.getTag(tagID);
                if (tagEntity != null) {
                    request.setAttribute("tagID", tagID);
                    request.setAttribute("title", cutTitle("Загрузка иконки для тэга[" +
                            tagID +
                            "]"));
                    ServletHelper.sendForward("/edittagimage.jsp?tagID=" + tagID, this, request, response);
                }
            } catch (Exception e) {
                throw new ServletException("incorrect tag id");
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
