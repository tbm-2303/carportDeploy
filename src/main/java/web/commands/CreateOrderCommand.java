
package web.commands;


import business.entities.*;
import business.exceptions.UserException;
import business.services.OrderFacade;
import business.services.RequestFacade;

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

    public CreateOrderCommand(String pageToShow, String role) {
        super(pageToShow, role);
        this.requestFacade = new RequestFacade(database);
        this.orderFacade = new OrderFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {


        try {


            if (request.getParameter("accept") != null) {
                Request_obj request_obj = requestFacade.getRequest(Integer.parseInt(request.getParameter("accept")));
                Date date = new Date();
                long time = date.getTime();
                Timestamp ts = new Timestamp(time);
                requestFacade.updateRequestStatus(request_obj.getRequest_id(), "ordered");
                request_obj.setStatus("ordered");
                Order order = orderFacade.createOrder(request_obj, ts);
                request.setAttribute("comfirmation_object", order);
                request.setAttribute("confirmation_request", request_obj);
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


