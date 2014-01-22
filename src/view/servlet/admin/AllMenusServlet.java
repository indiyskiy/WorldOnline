package view.servlet.admin;

import model.additionalentity.CompleteMenuInfo;
import model.additionalentity.MenuInfo;
import model.constants.Component;
import model.constants.databaseenumeration.LanguageType;
import model.constants.databaseenumeration.MenuType;
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
 * Date: 21.01.14
 * Time: 12:13
 * To change this template use File | Settings | File Templates.
 */
public class AllMenusServlet extends HttpServlet {
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, AllMenusServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServletHelper.setUTF8(request, response);
        try {
            ServletHelper.setUTF8(request, response);
            Long menuId = null;
            if (request.getParameter("MenuID") != null && !request.getParameter("MenuID").isEmpty()) {
                menuId = Long.parseLong(request.getParameter("MenuID"));
            }
            loggerFactory.info("menu id "+menuId);
            MenuInfo menuInfo = MenuRequest.getCompleteMenuInfo(menuId, LanguageType.Russian);
            if (menuInfo != null) {
                request.setAttribute("menu", menuInfo.getMenu());
                request.setAttribute("submenus", menuInfo.getSubmenus());
                request.setAttribute("parent", menuInfo.getParentMenu());
            }
            request.setAttribute("menuTypes", MenuType.values());
            request.setAttribute("tab","     ");
            ServletHelper.sendForward("/allmenus.jsp", this, request, response);
        } catch (Exception e) {
//            request.setAttribute("errorMesage", e.getMessage());
//            ServletHelper.sendForward("/error.jsp", this, request, response);
            loggerFactory.error(e);
            throw new ServletException(e);
        }
    }

}
