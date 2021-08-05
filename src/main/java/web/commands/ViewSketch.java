package web.commands;

import business.entities.Carport;
import business.entities.Request_obj;
import business.entities.User;
import business.exceptions.UserException;
import business.persistence.RequestMapper;
import business.services.SVGMaker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ViewSketch extends CommandProtectedPage {
    RequestMapper requestFacade;

    public ViewSketch(String pageToShow, String role) {
        super(pageToShow, role);
        requestFacade = new RequestMapper(database);

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        try {
            int request_id = Integer.parseInt(request.getParameter("sketch"));
            Request_obj request_obj = requestFacade.getRequest(request_id);
            Carport carport = request_obj.getCarport();
            SVGMaker svgMaker = new SVGMaker(carport);
            svgMaker.initialSVGStuff();
            String sketch = svgMaker.giveMeSketch();
            request.setAttribute("sketch", sketch);
            return pageToShow;

        } catch (UserException e) {
            request.setAttribute("error", e.getMessage());
            return "index";
        }
    }
}
