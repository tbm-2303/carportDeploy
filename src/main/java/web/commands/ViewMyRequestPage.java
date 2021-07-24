package web.commands;

import business.entities.*;
import business.exceptions.UserException;
import business.services.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ViewMyRequestPage extends CommandProtectedPage {
    RequestFacade requestFacade;
    CarportFacade carportFacade;
    UserFacade userFacade;
    Util util;


    public ViewMyRequestPage(String pageToShow, String role) {
        super(pageToShow, role);
        requestFacade = new RequestFacade(database);
        //carportFacade = new CarportFacade(database);
        //userFacade = new UserFacade(database);
        util = new Util(database);

    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        try {

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            List<Request_obj> requestList = requestFacade.getAllRequest3(user.getId(), "requested");
            List<Request_obj> requestList2 = new ArrayList<>();
            List<Item> itemlist = new ArrayList<>();


            if (!requestList.isEmpty()) {// hvis den er forskellig fra null så løber vi listen igennem og sætter boolean.
                for (Request_obj requestObj : requestList) {
                    Carport carport = requestObj.getCarport();
                    if (carport.getShed_width() > 0 && carport.getShed_length() > 0) {
                        carport.setHasShed(true);
                    }
                    itemlist = util.CustomCarportRecipe(carport.getLength(), carport.getWidth(), carport.getShed_width(), carport.getShed_length());
                    requestObj.setItemList(itemlist);
                    requestList2.add(requestObj); // sætter request_obj itemlist og boolean. derefter ligges den ind i listen.
                }
            }

            request.setAttribute("requestList_customer", requestList2);
            request.setAttribute("itemlist_customer", itemlist);
            return pageToShow;

        } catch (UserException e) {
            request.setAttribute("error", "database error: no request found");
            return "index";
        }
    }
}
