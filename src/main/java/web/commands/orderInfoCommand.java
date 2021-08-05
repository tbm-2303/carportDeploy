package web.commands;

import business.entities.Carport;
import business.entities.Item;
import business.entities.Order;
import business.entities.Request_obj;
import business.exceptions.UserException;
import business.services.CarportFacade;
import business.services.OrderFacade;
import business.services.RequestFacade;
import business.services.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class orderInfoCommand extends CommandProtectedPage {
    private final CarportFacade carportFacade;
    private OrderFacade orderFacade;
    private RequestFacade requestFacade;
    Util util;


    public orderInfoCommand(String pageToShow, String role) {
        super(pageToShow, role);
        this.carportFacade = new CarportFacade(database);
        this.orderFacade = new OrderFacade(database);
        this.requestFacade = new RequestFacade(database);
        util = new Util(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        try {
            int order_id = Integer.parseInt(request.getParameter("info"));
            Order order = orderFacade.getOrder(order_id);
            // request.setAttribute("order",order);

            int request_id = order.getRequest_id();
            Request_obj request_obj = requestFacade.getRequest(request_id);

            Carport carport = request_obj.getCarport();
            //request.setAttribute("carport",carport);
            List<Item> list = util.CustomCarportRecipe(carport.getLength(), carport.getWidth(), carport.getShed_width(), carport.getShed_length());


            request.setAttribute("request_object", request_obj);
            request.setAttribute("itemlist", list); //carport type
            request.setAttribute("test", null);

            return pageToShow;

            //carport mål
            //carport pris
            //carport billede evt.
            //
            //itemlist
            //
            //ordre tidspunkt
            //ordre nummer
            //
            //tegning


        }
        catch (UserException e){
            request.getSession().setAttribute("error", e.getMessage());
            return "index";
        }


    }
}
