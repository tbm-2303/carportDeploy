package web.commands;

import business.entities.Carport;
import business.exceptions.UserException;
import business.services.CarportFacade;
import business.services.ItemFacade;
import business.services.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SendRequest_standard extends CommandProtectedPage {
    CarportFacade carportFacade;
    ItemFacade itemFacade;
    Util util;

    public SendRequest_standard(String pageToShow, String role) {
        super(pageToShow, role);
        carportFacade = new CarportFacade(database);
        itemFacade = new ItemFacade(database);
        util = new Util(database);


    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();

        if (request.getParameter("getCarportID") != null) {
            if (session.getAttribute("Selected_Carport") == null){
                int carport_id = Integer.parseInt(request.getParameter("getCarportID"));
                Carport carport = carportFacade.getCarport(carport_id);
                session.setAttribute("Selected_Carport_itemlist", util.CustomCarportRecipe(carport.getLength(),carport.getWidth(),carport.getShed_width(),carport.getShed_length()));
                session.setAttribute("Selected_Carport", carport);
                return "index";
            }
            request.setAttribute("error", "You already have an carport in your basket. Please order/remove the carport from your basket " +
                    "before adding new ones to your basket.");
            return "index";

        }
        request.setAttribute("error", "hmmm: dunno how u did this?!");
        return "index";
    }
}
