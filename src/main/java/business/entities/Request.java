package business.entities;

public class Request {

    private int carport_id;
    private int user_id;
    private String status;
    private int request_id;

    public Request(int carport_id, int user_id, String status) {
        this.carport_id = carport_id;
        this.user_id = user_id;
        this.status = status;
    }

    public int getCarport_id() {
        return carport_id;
    }

    public void setCarport_id(int carport_id) {
        this.carport_id = carport_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
}
