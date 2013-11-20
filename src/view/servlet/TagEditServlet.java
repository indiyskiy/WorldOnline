package view.servlet;

import controller.parser.edit.TagEditParser;
import model.additionalentity.CompleteTagInfo;
import model.constants.databaseenumeration.TagType;
import model.database.requests.TagRequest;
import model.database.worldonlinedb.TagEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
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
public class TagEditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
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
//            request.setAttribute("errorMesage", e.getMessage());
//            ServletHelper.sendForward("/error.jsp", this, request, response);
            throw new ServletException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            TagEditParser parser = new TagEditParser();
            TagEntity tagEntity=parser.parse(request);
            if(parser.hasNoErrors()){
                TagRequest.editTag(tagEntity);
            } else {
                throw new ServletException(parser.getErrorsForHTML());
            }
            doGet(request, response);
        } catch (Exception e) {
//            request.setAttribute("errorMesage", e.getMessage());
//            ServletHelper.sendForward("/error.jsp", this, request, response);
            throw new ServletException(e);
        }
    }
}
