package web.commands;

import business.entities.Carport;
import business.entities.Item;
import business.entities.Request_obj;
import business.entities.User;
import business.exceptions.UserException;
import business.services.CarportFacade;
import business.services.RequestFacade;
import business.services.UserFacade;
import business.services.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ManageOffer extends CommandProtectedPage {
    RequestFacade requestFacade;
    CarportFacade carportFacade;
    UserFacade userFacade;
    Util util;

    public ManageOffer(String pageToShow, String role) {
        super(pageToShow, role);
        requestFacade = new RequestFacade(database);
        carportFacade = new CarportFacade(database);
        userFacade = new UserFacade(database);
        util = new Util(database);

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            int requestid = Integer.parseInt(request.getParameter("accept"));
            Request_obj request1 = requestFacade.getRequest(requestid);
            List<Item> itemlist = new ArrayList<>();
            Carport carport = carportFacade.getCarport(request1.getCarport().getId());
            itemlist = util.CustomCarportRecipe(carport.getLength(), carport.getWidth(), carport.getShed_width(), carport.getShed_length());
            request.setAttribute("request_object", request1);
            request.setAttribute("itemlist", itemlist);
            request.setAttribute("test", "test");

            return pageToShow;

        } catch (UserException e) {
            request.getSession().setAttribute("error", "database error: no requests found");
            return "index";
        }

    }
}
