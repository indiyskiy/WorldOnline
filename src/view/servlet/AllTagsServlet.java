package view.servlet;

import model.FileReader;
import model.constants.databaseenumeration.TagType;
import model.database.requests.TagRequest;
import model.database.worldonlinedb.TagEntity;
import model.xmlparser.GlobalXmlParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 19.11.13
 * Time: 15:09
 * To change this template use File | Settings | File Templates.
 */
public class AllTagsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
          ServletHelper.setUTF8(request,response);
            if (request.getParameter("TagType") == null || request.getParameter("TagType").isEmpty()) {
                TagType[] tagTypes = TagType.values();
                for (TagType tagType : tagTypes) {
                    String typeName = "tags_" + tagType.toString().toLowerCase();
                    ArrayList<TagEntity> tagEntities = TagRequest.getTags(tagType);
                    request.setAttribute(typeName, tagEntities);
                }
            } else {
                int type = Integer.parseInt(request.getParameter("TagType"));
                TagType tagType = TagType.parseInt(type);
                String typeName = "tags_" + tagType.toString().toLowerCase();
                ArrayList<TagEntity> tagEntities = TagRequest.getTags(tagType);
                request.setAttribute(typeName, tagEntities);
            }
            ServletHelper.sendForward("/alltags.jsp", this, request, response);
        } catch (Exception e) {
//            request.setAttribute("errorMesage", e.getMessage());
//            ServletHelper.sendForward("/error.jsp", this, request, response);
            throw new ServletException(e);
        }
    }


}
