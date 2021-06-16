package web.commands;

import business.entities.Carport;
import business.entities.User;
import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetBasket extends CommandProtectedPage {

    public GetBasket(String pageToShow, String role) {
        super(pageToShow, role);

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();

        if (session.getAttribute("Selected_Carport") == null) {
            request.setAttribute("error", "You currently have no carports in your basket.");
            return "index";
        }
        return "basketpage";
    }
}
