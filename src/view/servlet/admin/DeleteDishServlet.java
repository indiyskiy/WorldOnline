package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.DishRequest;
import model.database.requests.TextRequest;
import model.database.worldonlinedb.dishes.DishEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteDishServlet extends ProtectedServlet {
    private static final LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, DeleteDishServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
//            loggerFactory.debug("DeleteDishServlet doGet");
            Long dishID;
            try {
                dishID = Long.parseLong(request.getParameter("dishID"));
            } catch (Exception e) {
                throw new ServletException("incorrect dish id " + request.getParameter("dishID"));
            }
            DishEntity dishEntity = DishRequest.getDish(dishID);
            if (dishEntity != null) {
                DishRequest.updatePrice(dishEntity.getPrice());
                DishRequest.deleteDish(dishEntity);
                TextRequest.deleteTextGroup(dishEntity.getDishName());
                ServletHelper.sendForward("/completepriceinfo?priceID=" + dishEntity.getPrice().getPriceID(), this, request, response);
            } else {
                throw new ServletException("incorrect dish id " + dishID);
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
