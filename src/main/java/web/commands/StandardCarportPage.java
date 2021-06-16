package web.commands;

import business.entities.Carport;
import business.entities.User;
import business.exceptions.UserException;
import business.services.CarportFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class StandardCarportPage extends CommandProtectedPage {
    private final CarportFacade carportFacade;


    public StandardCarportPage(String pageToShow, String role) {
        super(pageToShow, role);
        this.carportFacade = new CarportFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        List<Carport> carportList = carportFacade.getAllStandardCarports(2);
        for (Carport c : carportList) {
            c.setHasShed(c.getShed_length() > 0 && c.getShed_width() > 0);
        }
        request.setAttribute("carportList_standard", carportList);
        return pageToShow;
    }
}
