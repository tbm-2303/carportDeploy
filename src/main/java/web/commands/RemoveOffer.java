
package web.commands;


import business.entities.Request_obj;
import business.exceptions.UserException;
import business.services.CarportFacade;
import business.services.OrderFacade;
import business.services.RequestFacade;
import org.omg.CosNaming.NamingContextPackage.NotEmpty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RemoveOffer extends CommandProtectedPage {
    private RequestFacade requestFacade;
    private OrderFacade orderFacade;
    private CarportFacade carportFacade;

    public RemoveOffer(String pageToShow, String role) {
        super(pageToShow, role);
        this.requestFacade = new RequestFacade(database);
        this.orderFacade = new OrderFacade(database);
        this.carportFacade = new CarportFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        try {
            if (request.getParameter("remove") != null) {

                //remove request

                Request_obj request_obj = requestFacade.getRequest(Integer.parseInt(request.getParameter("remove")));
                int requestID = request_obj.getRequest_id();
                requestFacade.removeRequestFromDB(requestID);

                //remove carport
                carportFacade.deleteCarport(request_obj.getCarport().getId());

            }
            return pageToShow;

        } catch (UserException ex) {
            request.setAttribute("error", "ex.getMessage()");
            return "viewmyoffer";
        }
    }
}


