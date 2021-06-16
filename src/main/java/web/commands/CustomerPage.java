package web.commands;

import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;
import business.services.CarportFacade;
import business.services.OrderFacade;
import business.services.RequestFacade;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CustomerPage extends CommandProtectedPage {
    RequestFacade requestFacade;
    CarportFacade carportFacade;
    UserFacade userFacade;
    OrderFacade orderFacade;


    public CustomerPage(String pageToShow, String role) {
        super(pageToShow, role);
        this.carportFacade = new CarportFacade(database);
        this.requestFacade = new RequestFacade(database);
        this.userFacade = new UserFacade(database);
        this.orderFacade = new OrderFacade(database);
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        try {
            HttpSession session = request.getSession();
            List<User> userList = userFacade.getAllUsers();
            session.setAttribute("userList_admin", userList);
            return pageToShow;

        } catch (UserException e) {
            request.setAttribute("error", e.getMessage());
        }
        return null;
    }
}
