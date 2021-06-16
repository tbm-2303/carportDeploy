package web.commands;

import business.entities.Carport;
import business.entities.Request;
import business.entities.Request_obj;
import business.entities.User;
import business.exceptions.UserException;
import business.persistence.RequestMapper;
import business.services.CarportFacade;
import business.services.RequestFacade;
import business.services.UserFacade;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Requestpage extends CommandProtectedPage {
    RequestFacade requestFacade;
    CarportFacade carportFacade;
    UserFacade userFacade;

    public Requestpage(String pageToShow, String role) {
        super(pageToShow, role);
        requestFacade = new RequestFacade(database);
        carportFacade = new CarportFacade(database);
        userFacade = new UserFacade(database);

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            List<Request_obj> requestList = requestFacade.getAllRequest2("requested");

            session.setAttribute("requestList_admin", requestList);
            return pageToShow;

        } catch (UserException e) {
            request.getSession().setAttribute("error", "database error: no requests found");
            return "index";
        }
    }
}
