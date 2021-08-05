package business.services;

import business.entities.Item;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.ItemMapper;

import java.util.List;

public class ItemFacade {
    ItemMapper itemMapper;

    public ItemFacade(Database database) {
        itemMapper = new ItemMapper(database);
    }

    public Item SelectItemFromDB(String name, int length) throws UserException {
        return itemMapper.SelectItemFromDB(name, length);
    }

    public void updateItem(String name, String info, double price, double profit) {
    }

    public void deleteItem(int item_id) {
    }

    public void insertNewItem(Item item) {
    }

    public List<Item> getItemList(int carport_id) throws UserException {
        return itemMapper.getItemList(carport_id);
    }
}
