package web.commands;

import business.entities.*;
import business.exceptions.UserException;
import business.services.ItemFacade;
import business.services.OrderFacade;
import business.services.RequestFacade;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;

public class CreateOrderStandard extends CommandProtectedPage {
    OrderFacade orderFacade;
    RequestFacade requestFacade;
    ItemFacade itemFacade;

    public CreateOrderStandard(String pageToShow, String role) {
        super(pageToShow, role);
        orderFacade = new OrderFacade(database);
        requestFacade = new RequestFacade(database);
        itemFacade = new ItemFacade(database);

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            Carport carport = (Carport) session.getAttribute("Selected_Carport");
            Request_obj request_obj = new Request_obj(user, carport, "ordered");
            requestFacade.CreateRequest_standard(request_obj);//create request in db.
            Date date = new Date();
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);
            Order order = orderFacade.createOrder(request_obj, ts);//create order in db.
            session.setAttribute("Selected_Carport", null);
            session.setAttribute("Selected_Carport_itemlist", null);

            request.setAttribute("confirmation_object_standard", order);
            request.setAttribute("confirmation_request_standard", request_obj);

            return "confirmationpage";//order confirmation_standard page
        } catch (UserException exception) {
            exception.printStackTrace();
            return "index";
        }

    }
}
