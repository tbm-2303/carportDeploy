
package web.commands;


import business.entities.*;
import business.exceptions.UserException;
import business.services.ItemFacade;
import business.services.OrderFacade;
import business.services.RequestFacade;
import business.services.Util;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateOrderCommand extends CommandProtectedPage {
    private RequestFacade requestFacade;
    private OrderFacade orderFacade;
    Util util;
    ItemFacade itemFacade;

    public CreateOrderCommand(String pageToShow, String role) {
        super(pageToShow, role);
        this.requestFacade = new RequestFacade(database);
        this.orderFacade = new OrderFacade(database);
        this.util = new Util(database);
        itemFacade = new ItemFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {


        try {

            if (request.getParameter("accept") != null) {
                Request_obj request_obj = requestFacade.getRequest(Integer.parseInt(request.getParameter("accept")));
                Date date = new Date();
                long time = date.getTime();
                Timestamp ts = new Timestamp(time);
                requestFacade.updateRequestStatus(request_obj.getRequest_id(), "ordered");//updates the request status in db.
                request_obj.setStatus("ordered");
                List<Item> itemlist = util.CustomCarportRecipe(request_obj.getCarport().getLength(),
                        request_obj.getCarport().getWidth(),request_obj.getCarport().getShed_width(),request_obj.getCarport().getShed_width());
                int carportID = request_obj.getCarport().getId();
                Order order = orderFacade.createOrder(request_obj, ts, itemlist, carportID);// creating an order in db.
                request.setAttribute("confirmation_object", order);
                request.setAttribute("confirmation_request", request_obj);


                //lav en itemlist vha. util metoden.
                //brug mapper metoder til at udfylde link tabellen. (carport id, item id, item quantity)

            }
            return pageToShow ;//order confirmation page
        } catch (UserException ex) {
            request.setAttribute("error", ex.getMessage());
            return "index";
        }
    }

    /*

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ServletContext servletContext = request.getServletContext();
        List<Requesty> RL1 = (List<Requesty>) servletContext.getAttribute("requestList");
        String[] pricelist = request.getParameterValues("price");
        List<Double> pricelistInt = new ArrayList<>();

        for (String price : pricelist) {
            pricelistInt.add(Double.parseDouble(price));
        }
        for (int i = 0; i < RL1.size(); ++i) {
            RL1.get(i).setPrice(pricelistInt.get(i));
        }
        String remove = request.getParameter("remove");
        if (remove != null) {
            RL1.remove(Integer.parseInt(remove));
        }
        String confirm = request.getParameter("confirm");
        if (confirm != null) {
            //Requesty request2 = RL1.get(Integer.parseInt(confirm));
            //List<Requesty> offerlist = (List<Requesty>) request.getServletContext().getAttribute("offerList");
           //if (offerlist == null) {
             //   offerlist = new ArrayList<>();
            //}
           // offerlist.add(request2);
           // servletContext.setAttribute("offerList", offerlist);
            //RL1.remove(Integer.parseInt(confirm));
        }
        request.getServletContext().setAttribute("requestList", RL1);
        return pageToShow;
    }

     */

}


