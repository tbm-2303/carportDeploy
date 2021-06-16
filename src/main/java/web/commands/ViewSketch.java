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

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Request_obj> requestList = requestFacade.getAllRequest3(user.getId(), "requested");

        if (requestList != null) {
            for (Request_obj r : requestList) {
                if (r.getUser().getId() == user.getId()) {
                    Carport carport = r.getCarport();
                    SVGMaker svg_maker = new SVGMaker(carport);
                    svg_maker.initialSVGStuff();
                    String skecth = svg_maker.giveMeSketch();
                    request.setAttribute("skecth", skecth);
                    return pageToShow;
                }
            }
        }
        request.setAttribute("error", "You need to make a request before u can see an sketch");
        return "index";
    }
}
