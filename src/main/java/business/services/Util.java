package business.services;

import business.entities.Item;
import business.exceptions.UserException;
import business.persistence.Database;

import java.util.ArrayList;
import java.util.List;

public class Util {
    private ItemFacade itemFacade;
    private RequestFacade requestFacade;
    private CarportFacade carportFacade;
    private int start;

    public Util(Database database) {
        itemFacade = new ItemFacade(database);
        carportFacade = new CarportFacade(database);
        requestFacade = new RequestFacade(database);
        start = 2400;

    }


    public List<Item> CustomCarportRecipe(int length, int width, int shed_width, int shed_length) throws UserException {
        try {
            List<Item> listy = new ArrayList<>();
            listy.add(itemFacade.SelectItemFromDB("Spær", 4800));// always there
            listy.add(itemFacade.SelectItemFromDB("Spær", length));// always there1
            listy.add(itemFacade.SelectItemFromDB("Spær", length));// always there2
            for (int i = 0; i < 6; i++) {
                listy.add(itemFacade.SelectItemFromDB("Spær", length));//always there6
            }
            for (int i = 0; i < 4; i++) {
                listy.add(itemFacade.SelectItemFromDB("Stolpe", 3000));//always there4
            }

            for (int i = 2400; i < 7510; i += 2500) {
                if (width > i + 2500) {
                    for (int j = 0; j < 4; j++) {
                        listy.add(itemFacade.SelectItemFromDB("Stolpe", 3000));// 3 for each 250cm width added
                    }
                }
            }

            for (int i = 2400; i < 7510; i += 550) {
                if (width > (start + i)) {
                    start = start + i;
                    listy.add(itemFacade.SelectItemFromDB("Spær", length));// 1 for each 55cm length added
                }
            }
            if (length >= shed_length && width >= shed_width) {
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
            }
            return listy;
        } catch (UserException e) {
           e.printStackTrace();
        }
        return null;
    }
}

