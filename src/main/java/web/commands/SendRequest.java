package web.commands;

import business.entities.*;
import business.exceptions.UserException;
import business.services.CarportFacade;
import business.services.ItemFacade;
import business.services.RequestFacade;
import business.services.Util;
import org.omg.CosNaming.NamingContextPackage.NotEmpty;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import static java.lang.Math.floor;

public class SendRequest extends CommandUnprotectedPage {
    private ItemFacade itemFacade;
    private RequestFacade requestFacade;
    private CarportFacade carportFacade;
    private Util util;


    public SendRequest(String pageToShow) {
        super(pageToShow);
        itemFacade = new ItemFacade(database);
        carportFacade = new CarportFacade(database);
        requestFacade = new RequestFacade(database);
        util = new Util(database);
    }

    private List<Item> CustomCarportRecipe(int width, int length, int shed_length, int shed_width) throws UserException {
        try {
            int buffer = 550;
            List<Item> listy = new ArrayList<>();
            //skruer og beslag
            listy.add(itemFacade.SelectItemFromDB("bundskruer", 0));
            listy.add(itemFacade.SelectItemFromDB("bundskruer", 0));
            listy.add(itemFacade.SelectItemFromDB("bundskruer", 0));

            listy.add(itemFacade.SelectItemFromDB("Skruer", 50));
            listy.add(itemFacade.SelectItemFromDB("Skruer", 50));
            listy.add(itemFacade.SelectItemFromDB("Skruer", 70));
            listy.add(itemFacade.SelectItemFromDB("Skruer", 70));
            listy.add(itemFacade.SelectItemFromDB("Skruer", 60));

            listy.add(itemFacade.SelectItemFromDB("stalddørsgreb", 75));

            for (int i = 0; i < 18; i++) {
                listy.add(itemFacade.SelectItemFromDB("bræddebolt", 150));
            }
            for (int i = 0; i < 12; i++) {
                listy.add(itemFacade.SelectItemFromDB("firkantskiver", 40));
            }

            //spær
            listy.add(itemFacade.SelectItemFromDB("Spær", 4800));// always there
            listy.add(itemFacade.SelectItemFromDB("Spær", length));// always there1
            listy.add(itemFacade.SelectItemFromDB("Spær", length));// always there2
            for (int i = 0; i < 6; i++) {
                listy.add(itemFacade.SelectItemFromDB("Spær", length));//always there6
            }

            double count = Math.floor((double) width /550);
            for (int i = 0; i < count-1; i++) {
                listy.add(itemFacade.SelectItemFromDB("Spær", length));// 1 for each 550mm length added
            }



            for (int i = 0; i < 4; i++) {
                listy.add(itemFacade.SelectItemFromDB("Stolpe", 3000));//always there4
            }

            for (int i = 2400; i < 7510; i += 2500) {
                if (width > i + 2500) {
                    for (int j = 0; j < 4; j++) {
                        listy.add(itemFacade.SelectItemFromDB("Stolpe", 3000));// 3 for each 2500mm width added
                    }
                }
            }


            if (shed_length > 0 || shed_width > 0) {
                for (int i = 0; i < 4; i++) {
                    listy.add(itemFacade.SelectItemFromDB("Stolpe", 3000));//3 if shed is added
                }
                if (shed_length > (length * 0.75)) {
                    listy.add(itemFacade.SelectItemFromDB("Stolpe", 3000));// 1 additional if shed length is over threshold
                }
                if (shed_width > (width * 0.75)) {
                    listy.add(itemFacade.SelectItemFromDB("Stolpe", 3000));// 1 additional if shed width is over threshold
                }
            }

            return listy;
        } catch (UserException e) {
            throw new UserException(e.getMessage());
        }
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        try {

            int length = Integer.parseInt(request.getParameter("length"));
            int width = Integer.parseInt(request.getParameter("width"));
            int shed_length = Integer.parseInt(request.getParameter("shed_length"));
            int shed_width = Integer.parseInt(request.getParameter("shed_width"));
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            List<Request_obj> requestList = requestFacade.getAllRequest3(user.getId(), "requested");


            if (shed_width > width || shed_length > length) {
                request.setAttribute("error", "You cant select a shed width or shed width greater then the length or width of the carport.");
                return "sendrequest";
            }


            if (requestList.size() < 10) {

                //itemlist
                List<Item> listy = util.CustomCarportRecipe(length,width,shed_width,shed_length);
                //price
                double price = 0;
                for (Item item : listy) {
                    double itemprice = item.getPrice();
                    price += itemprice;
                }
                if (length >= 6000){
                    price += 4000;
                }
                else if (length >= 4500){
                    price += 2000;
                }
                else if (length >= 2400){
                    price += 500;
                }

                if (width >= 6000){
                    price += 4000;
                }
                else if (width >= 4500){
                    price += 2000;
                }
                else if (width >= 2400){
                    price += 500;
                }


                //carport DB
                Carport carport = carportFacade.createCarportCustom(new Carport(price, length, width, shed_length, shed_width, "flat", "info"));
                //request DB
                requestFacade.createRequest(new Request_obj(user, carport, "requested"));
                return pageToShow;
            }
            else {
                request.setAttribute("error", "Max number of request is 10. Please resolve some of your current" +
                        "requests before creating a new.");
                return "index";
            }


        } catch (UserException e) {
            request.setAttribute("error", e.getMessage());
            return pageToShow;
        }
    }
}