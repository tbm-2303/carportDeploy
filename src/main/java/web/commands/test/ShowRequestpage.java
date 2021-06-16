package web.commands.test;

import business.entities.*;
import business.exceptions.UserException;
import business.services.CarportFacade;
import business.services.RequestFacade;
import business.services.UserFacade;
import org.omg.CosNaming.NamingContextPackage.NotEmpty;
import web.commands.CommandProtectedPage;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShowRequestpage extends CommandProtectedPage {
    RequestFacade requestFacade;
    CarportFacade carportFacade;
    UserFacade userFacade;

    public ShowRequestpage(String pageToShow, String role) {
        super(pageToShow, role);
        requestFacade = new RequestFacade(database);
        carportFacade = new CarportFacade(database);
        userFacade = new UserFacade(database);
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        try {
            HttpSession session = request.getSession();
            ServletContext servletContext = request.getServletContext();

            //User user = (User) session.getAttribute("user");
            List<Request> list = (List<Request>) servletContext.getAttribute("requestList");

            String string = "requested";
            //List<Request> list = requestFacade.getAllRequest(string);// fetch all request(c_id,u_id,status) from db where status=requested
            List<Request_obj> list2 = new ArrayList<>();
            if (list == null || list.isEmpty()){
                request.setAttribute("error", "list is empty");
                return "index";
            }

            for (Request item : list) {
                Carport c = carportFacade.getCarport(item.getCarport_id());
                User u = userFacade.getUser(item.getUser_id());
                Request_obj request_obj = new Request_obj(u, c, string);
                list2.add(request_obj);
            }
            session.setAttribute("requestList22", list2);


            return pageToShow;
        } catch (UserException exception) {
            exception.printStackTrace();
        }

        request.setAttribute("error", "could not create list of request: no request in database");
        return "index";
    }
}
