package web.commands.test;

import business.entities.*;
import business.exceptions.UserException;
import business.services.CarportFacade;
import business.services.OrderFacade;
import web.commands.CommandProtectedPage;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ConfirmOrder extends CommandProtectedPage {
    CarportFacade carportFacade;
    OrderFacade orderFacade;


    public ConfirmOrder(String pageToShow, String role) {
        super(pageToShow, role);
    }
/*

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        HttpSession session = request.getSession();
        ServletContext servletContext = request.getServletContext();

        User tempUser = (User) session.getAttribute("user");


        //create order in db
        //create carport in db
        //remove requesty from offerlist and requestlist in session and application scope.
        List<Requesty> offerlist = (List<Requesty>) request.getSession().getAttribute("offerList");
        User user = (User) session.getAttribute("user");
        Requesty requesty = null;

        for (Requesty request2 : offerlist) {
            if (request2.getUser().getId() == user.getId()) {
                requesty = request2;
            }
        }
        offerlist.remove(requesty);
        Carport carport = new Carport(requesty.getPrice(), requesty.getWidth(), requesty.getLength(), requesty.getWidth(),
                requesty.getShed_length(), "flat", "info");
        Order order = new Order(requesty.getWidth(), requesty.getLength(), requesty.getShed_length(), requesty.getShed_width(),
                requesty.getPrice(), (((requesty.getPrice()) * requesty.getPrice()) - requesty.getPrice()), requesty.getUser().getId(), "info", "requested");

        orderFacade.createOrder(order);
        carportFacade.createCarportCustom(carport);

        String pageToShow2 = "orderconfirmation";
        return pageToShow2;
    }

 */
}
