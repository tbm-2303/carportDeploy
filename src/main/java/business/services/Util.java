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

    }


    public List<Item> CustomCarportRecipe(int length, int width, int shed_width, int shed_length) throws UserException {
        try {
            int buffer = 550;
            List<Item> listy = new ArrayList<>();
            //skruer og beslag
            Item bundskruer = (itemFacade.SelectItemFromDB("bundskruer", 0));
            bundskruer.setQuantity(3);
            listy.add(bundskruer);

            Item skruer = (itemFacade.SelectItemFromDB("Skruer", 50));
            skruer.setQuantity(2);
            listy.add(skruer);

            Item skruer1 = (itemFacade.SelectItemFromDB("Skruer", 70));
            skruer1.setQuantity(2);
            listy.add(skruer1);

            listy.add(itemFacade.SelectItemFromDB("Skruer", 60));
            listy.add(itemFacade.SelectItemFromDB("stalddørsgreb", 75));

            Item bræddebolt = (itemFacade.SelectItemFromDB("bræddebolt", 150));
            bræddebolt.setQuantity(18);
            listy.add(bræddebolt);

            Item firkantskiver = (itemFacade.SelectItemFromDB("firkantskiver", 40));
            firkantskiver.setQuantity(12);
            listy.add(firkantskiver);
            //brædder
            Item brædder = itemFacade.SelectItemFromDB("Brædt", 540);
            brædder.setQuantity(4);
            listy.add(brædder);
            Item brædder1 = itemFacade.SelectItemFromDB("Brædt", 550);
            brædder1.setQuantity(4);
            listy.add(brædder1);
            Item brædder2 = itemFacade.SelectItemFromDB("Brædt", 200);
            brædder2.setQuantity(6);
            listy.add(brædder2);

            //spær
            listy.add(itemFacade.SelectItemFromDB("Spær", 4800));// always there

            int count = (int) Math.floor((double) width / 300);
            Item spær = (itemFacade.SelectItemFromDB("Spær", length));
            spær.setQuantity(count);
            listy.add(spær);

            //stolper
            Item stolpe = (itemFacade.SelectItemFromDB("Stolpe", 3000));
            stolpe.setQuantity(4);

            if (width >= 4900) {
                int stolpe_count = (int) Math.floor((double) (width - 2500) / 2500);
                int s1 = stolpe.getQuantity() + (stolpe_count * 3);
                stolpe.setQuantity(s1);
            }
            if (shed_length > 0 || shed_width > 0) {
                int s2 = stolpe.getQuantity();
                stolpe.setQuantity(s2 + 3);

                if (shed_length > (length * 0.75)) {
                    int s3 = stolpe.getQuantity();
                    stolpe.setQuantity(s3 + 1);

                }
                if (shed_width > (width * 0.75)) {
                    int s4 = stolpe.getQuantity();
                    stolpe.setQuantity(s4 + 1);
                }


                Item brædder3 = itemFacade.SelectItemFromDB("Brædt", 210);
                brædder3.setQuantity(200);
                listy.add(brædder3);
                Item brædder4 = itemFacade.SelectItemFromDB("Brædt", 400);
                brædder4.setQuantity(4);
                listy.add(brædder4);
                Item brædder5 = itemFacade.SelectItemFromDB("Brædt", 350);
                brædder5.setQuantity(2);
                listy.add(brædder5);
            }
            listy.add(stolpe);

            return listy;
        } catch (UserException e) {
            e.printStackTrace();
        }
        return null;
    }
}