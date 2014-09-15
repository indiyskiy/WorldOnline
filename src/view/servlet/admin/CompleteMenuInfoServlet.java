package view.servlet.admin;

import model.additionalentity.admin.CompleteMenuInfo;
import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.MenuRequest;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CompleteMenuInfoServlet extends ProtectedServlet {
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, CompleteMenuInfoServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            ServletHelper.setUTF8(request, response);
            Long menuId = null;
            if (request.getParameter("menuID") != null && !request.getParameter("menuID").isEmpty()) {
                menuId = Long.parseLong(request.getParameter("menuID"));
            }
            if (menuId != null) {
                CompleteMenuInfo menuInfo = MenuRequest.getCompleteMenuInfo(menuId);
                if (menuInfo != null) {
                    request.setAttribute("menu", menuInfo.getMenuEntity());
                    request.setAttribute("textGroup", menuInfo.getCompleteTextGroupInfo().getTextGroup());
                    request.setAttribute("image", menuInfo.getImage());
                    request.setAttribute("title", cutTitle("Меню [" +
                            menuInfo.getSimpleMenu().getMenuID() +
                            "]" +
                            menuInfo.getSimpleMenu().getMenuName()));
//                    request.setAttribute("texts",menuInfo.getCompleteTextGroupInfo().getTextEntityMap().values());
//                    request.setAttribute("menu", menuInfo.getMenu());
//                    request.setAttribute("submenus", menuInfo.getSubmenus());
//                    request.setAttribute("parent", menuInfo.getParentMenu());
                }
                ServletHelper.sendForward("/completemenuinfo.jsp?menuId=" + menuId, this, request, response);
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
