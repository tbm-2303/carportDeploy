package business.services;

import business.entities.Item;
import business.entities.Order;
import business.entities.Request_obj;
import business.entities.User;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.OrderMapper;
import business.persistence.UserMapper;

import java.sql.Timestamp;
import java.util.List;

public class OrderFacade {
    OrderMapper orderMapper;

    public OrderFacade(Database database) {
        orderMapper = new OrderMapper(database);
    }


    public Order createOrder(Request_obj request_obj, Timestamp timestamp, List<Item> list, int carportID) throws UserException {
        return orderMapper.createOrder(request_obj,timestamp,list,carportID);

    }
    public Order createOrderStandard(Request_obj request_obj, Timestamp timestamp) throws UserException {
        return orderMapper.createOrderStandard(request_obj,timestamp);

    }
    public void deleteOrder(int order_id){

    }
    public List<Order> getAllOrders(String status) throws UserException {
        return orderMapper.getAllOrders(status);
    }
    public void updateOrderStatus(int order_id){

    }
    public void updateOrderDimensions(int width,int length, int shed_length, int shed_width){

    }
    public List<Order> getOrdersByUser(String username)throws UserException{
        return orderMapper.getOrderByUser(username);
    }

    public Order getOrder(int order_id) throws UserException {
        return orderMapper.getOrder(order_id);
    }

}
