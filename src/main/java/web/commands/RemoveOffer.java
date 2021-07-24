
package web.commands;


import business.entities.Request_obj;
import business.exceptions.UserException;
import business.services.CarportFacade;
import business.services.OrderFacade;
import business.services.RequestFacade;
import org.omg.CosNaming.NamingContextPackage.NotEmpty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.InputMismatchException;


public class RemoveOffer extends CommandProtectedPage {
    private final RequestFacade requestFacade;
    private final OrderFacade orderFacade;
    private final CarportFacade carportFacade;

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
                int requestID = Integer.parseInt(request.getParameter("remove"));
                requestFacade.updateRequestStatus(requestID, "declined");
                return pageToShow;
            }
        } catch (InputMismatchException e) {
            request.setAttribute("error", e.getMessage());
            return "index";
        }
        return null;
    }
}


