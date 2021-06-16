package web.commands;

import business.entities.Carport;
import business.entities.Request;
import business.entities.Request_obj;
import business.entities.User;
import business.services.CarportFacade;
import business.services.RequestFacade;
import business.services.UserFacade;
import business.exceptions.UserException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoginCommand extends CommandUnprotectedPage {
    private final UserFacade userFacade;
    private final RequestFacade requestFacade;
    private final CarportFacade carportFacade;

    public LoginCommand(String pageToShow) {
        super(pageToShow);
        userFacade = new UserFacade(database);
        requestFacade = new RequestFacade(database);
        carportFacade = new CarportFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            HttpSession session = request.getSession();

            User user = userFacade.login(email, password);
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());
            session.setAttribute("email", email);
           // List<Request_obj> requestList = requestFacade.getAllRequest3(user.getId(), "requested");

            return "index";


        } catch (UserException ex) {
            request.setAttribute("error", "Wrong username or password!");
            return "loginpage";
        }

    }
}
