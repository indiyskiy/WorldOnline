package view.servlet.admin;

import helper.ImageHelper;
import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.MenuRequest;
import model.database.worldonlinedb.MenuEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class EditMenuIconServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, EditMenuIconServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            ImageMenuUploadParser imageMenuUploadParser = new ImageMenuUploadParser();
            imageMenuUploadParser.parse(request);
            MenuEntity menuEntity = imageMenuUploadParser.getMenuEntity();
            File file = (File) imageMenuUploadParser.getFileMap().values().toArray()[0];
            ImageHelper.saveMenuIcon(file, menuEntity);
            long menuID = imageMenuUploadParser.getMenuID();
            ServletHelper.sendForward("/completemenuinfo?menuID=" + menuID, this, request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            long menuID;
            try {
                menuID = Long.parseLong(request.getParameter("menuID"));
                MenuEntity menuEntity = MenuRequest.getMenu(menuID);
                if (menuEntity != null) {
                    request.setAttribute("menuID", menuID);
                    request.setAttribute("title", cutTitle("Загрузка иконки для меню[" +
                            menuID +
                            "]"));
                    ServletHelper.sendForward("/editmenuicon.jsp?menuID=" + menuID, this, request, response);
                }
            } catch (Exception e) {
                throw new ServletException("incorrect menu id");
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
