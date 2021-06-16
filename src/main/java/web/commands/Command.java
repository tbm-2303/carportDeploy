package web.commands;

import business.exceptions.UserException;
import business.persistence.Database;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {
    //Return a token string from the execute method to make a client side redirect,
    // instead of a server side (forward) redirect
    public final static String REDIRECT_INDICATOR = "#*redirect*#_###_";
    public final static String WAS_NOT_FOUND_COMMAND = "404_NOT_FOUND";

    private static HashMap<String, Command> commands;
    public static Database database;

    private static void initCommands(Database database) {
        commands = new HashMap<>();
        commands.put("index", new CommandUnprotectedPage("index"));
        commands.put("loginpage", new CommandUnprotectedPage("loginpage"));
        commands.put("logincommand", new LoginCommand(""));
        commands.put("logoutcommand", new LogoutCommand(""));
        commands.put("registerpage", new CommandUnprotectedPage("registerpage"));
        commands.put("registercommand", new RegisterCommand(""));
        commands.put("customerpage", new CommandProtectedPage("customerpage", "customer"));
        commands.put("employeepage", new CommandProtectedPage("employeepage", "employee"));

        commands.put("sendrequestpage", new CommandUnprotectedPage("sendrequest"));
        commands.put("viewmyrequest", new ViewMyRequestPage("viewmyoffer", "customer"));//CUSTOMER: shows this customers request
        commands.put("sendRequestCommand", new SendRequest("index")); // send request
        commands.put("viewrequestspage", new Requestpage("viewrequests", "employee"));//ADMIN: shows all requests
        commands.put("createorder", new CreateOrderCommand("confirmationpage", "customer"));
        commands.put("vieworderspage", new OrderPage("vieworders", "employee"));
        commands.put("viewuserpage", new CustomerPage("viewcustomers", "employee"));
        commands.put("updateRequestCommand", new UpdateRequestCommand("viewrequests", "employee"));
        commands.put("standardcarportpage", new StandardCarportPage("standardCarport", "customer"));
        commands.put("ViewSketch", new ViewSketch("sketchpage", "customer"));
        commands.put("sendrequest_standard", new SendRequest_standard("index", "customer"));
        commands.put("getbasket", new GetBasket("basketpage","customer"));
        commands.put("createorder_standard", new CreateOrderStandard("index", "customer"));
        commands.put("removeoffer", new RemoveOffer("index", "customer"));


        //commands.put("showrequstpage", new ShowRequestpage("viewrequests", "employee"));
        //commands.put("updateCommand", new UpdateCommand("viewrequests", "employee"));


    }

    public static Command fromPath(
            HttpServletRequest request,
            Database db) {
        String action = request.getPathInfo().replaceAll("^/+", "");
        System.out.println("--> " + action);

        if (commands == null) {
            database = db;
            initCommands(database);
        }

        return commands.getOrDefault(action, new CommandUnknown());   // unknowncommand is default
    }

    public abstract String execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws UserException;

}
