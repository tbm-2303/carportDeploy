package business.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {

    public User(String name, String email, String address, String telephone, String role, String password) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.telephone = telephone;
        this.role = role;
        this.password = password;
    }

    private int id;
    private String name;
    private String email;
    private String address;
    private String telephone;
    private String role;
    private String password;




    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}


