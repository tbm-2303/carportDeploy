package business.entities;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Request_obj {
    private User user;
    private Carport carport;
    private String status;

    private int request_id;
    public List<Item> itemList;




    public Request_obj(User user, Carport carport, String status) {
        this.user = user;
        this.carport = carport;
        this.status = status;
    }



    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Carport getCarport() {
        return carport;
    }

    public void setCarport(Carport carport) {
        this.carport = carport;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public String getName() {
        return getUser().getName();
    }
}
