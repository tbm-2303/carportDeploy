
package web.commands;


import business.entities.Request_obj;
import business.exceptions.UserException;
import business.services.CarportFacade;
import business.services.OrderFacade;
import business.services.RequestFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class RemoveFromBasket extends CommandProtectedPage {
    private RequestFacade requestFacade;
    private OrderFacade orderFacade;
    private CarportFacade carportFacade;

    public RemoveFromBasket(String pageToShow, String role) {
        super(pageToShow, role);
        this.requestFacade = new RequestFacade(database);
        this.orderFacade = new OrderFacade(database);
        this.carportFacade = new CarportFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        if (request.getParameter("remove") != null) {
            HttpSession session = request.getSession();
            //clear the sessionscope attributes
            session.setAttribute("Selected_Carport", null);
            session.setAttribute("Selected_Carport_itemlist", null);
        }
        return pageToShow;

    }
}


