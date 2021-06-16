package web.commands;

import business.entities.Carport;
import business.entities.Item;
import business.entities.Request_obj;
import business.entities.User;
import business.exceptions.UserException;
import business.services.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UpdateRequestCommand extends CommandProtectedPage {
    private ItemFacade itemFacade;
    private RequestFacade requestFacade;
    private CarportFacade carportFacade;
    private UserFacade userFacade;
    Util util;

    public UpdateRequestCommand(String pageToShow, String role) {
        super(pageToShow, role);
        itemFacade = new ItemFacade(database);
        carportFacade = new CarportFacade(database);
        requestFacade = new RequestFacade(database);
        userFacade = new UserFacade(database);
        util = new Util(database);
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        List<Request_obj> requestList_admin = (List<Request_obj>) session.getAttribute("requestList_admin");

        int request_id = Integer.parseInt(request.getParameter("update"));//request_id ->carport_id[102,103,104,105]

        if (request.getParameterValues("new_price") != null) {
            String[] newPrice_strings = request.getParameterValues("new_price");//newPrice_strings[null,null,null,null]
            int index = 0;
            for (int i = 0; i < requestList_admin.size(); i++) {
                if (requestList_admin.get(i).getRequest_id() == request_id) {
                    index = i;
                }
            }
            if(!newPrice_strings[index].isEmpty()) {
                String price_string = newPrice_strings[index];
                double price = Double.parseDouble(price_string);
                requestList_admin.get(index).getCarport().setPrice(price);
                Request_obj request_obj = requestFacade.getRequest(request_id);
                carportFacade.updateCarportPrice(request_obj.getCarport().getId(), price);
                session.setAttribute("requestList_admin", requestList_admin);
                return pageToShow;
            }
            return pageToShow;
        }
        request.setAttribute("error", "somehow we cant update the price. ");
        return "index";
    }
}
