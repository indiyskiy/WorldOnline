package view.servlet.admin;

import model.additionalentity.CompleteMenuInfo;
import model.constants.Component;
import model.database.requests.MenuRequest;
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
 * Date: 23.01.14
 * Time: 12:53
 * To change this template use File | Settings | File Templates.
 */
public class CompleteMenuInfoServlet extends HttpServlet {
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, CompleteMenuInfoServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            ServletHelper.setUTF8(request, response);
            Long menuId = null;
            if (request.getParameter("MenuID") != null && !request.getParameter("MenuID").isEmpty()) {
                menuId = Long.parseLong(request.getParameter("MenuID"));
            }
            if (menuId != null) {
                CompleteMenuInfo menuInfo = MenuRequest.getCompleteMenuInfo(menuId);
                if (menuInfo != null) {
                    request.setAttribute("menu", menuInfo.getMenuEntity());
                    request.setAttribute("textGroup", menuInfo.getCompleteTextGroupInfo().getTextGroup());
                    request.setAttribute("image", menuInfo.getImage());
//                    request.setAttribute("texts",menuInfo.getCompleteTextGroupInfo().getTextEntityMap().values());
//                    request.setAttribute("menu", menuInfo.getMenu());
//                    request.setAttribute("submenus", menuInfo.getSubmenus());
//                    request.setAttribute("parent", menuInfo.getParentMenu());
                }
                ServletHelper.sendForward("/completemenuinfo.jsp?MenuId=" + menuId, this, request, response);
            }
        } catch (Exception e) {
//            request.setAttribute("errorMesage", e.getMessage());
//            ServletHelper.sendForward("/error.jsp", this, request, response);
            loggerFactory.error(e);
            throw new ServletException(e);
        }
    }
}
