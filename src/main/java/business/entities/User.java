package business.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    private int id;
    private String email;
    private String password;
    private String role;
    private String number;
    private String adress;
    private String name;

    private Carport carport;
    private List<Request_obj> request_objList;
    private Order order;


    public Carport getCarport() {
        return carport;
    }

    public void setCarport(Carport carport) {
        this.carport = carport;
    }

    public List<Request_obj> getRequest_objList() {
        return request_objList;
    }

    public void setRequest_objList(List<Request_obj> request_objList) {
        this.request_objList = request_objList;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}


