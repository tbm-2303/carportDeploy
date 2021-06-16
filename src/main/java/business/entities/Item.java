package business.entities;

public class Item {

   private String info;
    private String name;
    private double price;
    private int item_id;
    private int quantity;
    private int length;
    private int width;


    public Item(int id, String info, String name, double price, int length, int width) {
        this.item_id = id;
        this.name = name;
        this.price = price;
        this.width = width;
        this.length = length;
        this.info = info;

    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getItem_id() {
        return item_id;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }
}
