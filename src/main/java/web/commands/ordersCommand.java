package web.commands;

import business.entities.Carport;
import business.entities.Order;

import business.entities.Request_obj;
import business.entities.User;
import business.exceptions.UserException;
import business.services.CarportFacade;
import business.services.OrderFacade;
import business.services.RequestFacade;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ordersCommand extends CommandProtectedPage {
    RequestFacade requestFacade;
    CarportFacade carportFacade;
    UserFacade userFacade;
    OrderFacade orderFacade;

    public ordersCommand(String pageToShow, String role) {
        super(pageToShow, role);
        requestFacade = new RequestFacade(database);
        carportFacade = new CarportFacade(database);
        userFacade = new UserFacade(database);
        orderFacade = new OrderFacade(database);

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            List<Order> orderList = new ArrayList<>();
            User user = (User) session.getAttribute("user");
            int user_id = user.getId();
            List<Order> re = orderFacade.getOrdersByUser(user.getName());

            for (Order order : re) {
                Request_obj request_obj = requestFacade.getRequest(order.getRequest_id());
                //int id = request_obj.getCarport().getId();
                if (request_obj.getUser().getId() == user_id) {
                    order.setCarport(request_obj.getCarport());
                    orderList.add(order);
                }
            }
            request.setAttribute("orderlist", orderList);
            return pageToShow;


        } catch (UserException e) {
            request.getSession().setAttribute("error", "database error: no requests found");
            return "index";
        }
    }
}
