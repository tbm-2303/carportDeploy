package business.entities;

import java.sql.Time;
import java.sql.Timestamp;

public class Order {

    public Order(double price, int request_id, String status, String user_name) {
        this.price = price;
        this.request_id = request_id;
        this.status = status;
        this.user_name = user_name;
    }

    private int id;
    private Timestamp time;
    private double price;
    private int request_id;
    private String status;
    private String user_name;


    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}