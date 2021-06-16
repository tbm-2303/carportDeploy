package business.services;

import business.entities.Carport;
import business.exceptions.UserException;
import business.persistence.CarportMapper;
import business.persistence.Database;

import java.util.List;

public class CarportFacade {
    CarportMapper carportMapper;

    public CarportFacade(Database database) {
        carportMapper = new CarportMapper(database);
    }

    public List<Carport> getAllCarports() throws UserException {
        return carportMapper.getAllCarports();
    }

    public Carport createCarportCustom(Carport carport) throws UserException {
       return carportMapper.CreateCarportCustom(carport);
    }

    public Carport getCarport(int carport_id) throws UserException {
        return carportMapper.getCarport(carport_id);
    }

    public void updateCarportInfo(String info) {

    }

    public void updateCarportDimensions(int width, int length, int shed_length, int shed_width) {

    }


    public void deleteCarport(int carport_id) {

    }

    public void removeCarportFromDB(int carport_id) {
        carportMapper.removeCarportFromDB(carport_id);
    }

    public void updateCarport(int carport_id, Carport carport) {
        carportMapper.updateCarport(carport_id, carport);
    }

    public void updateCarportPrice(int carport_id, double price) {
        carportMapper.updateCarportPrice(carport_id, price);
    }

    public List<Carport> getAllStandardCarports(int status) {

        return carportMapper.getAllStandardCarports(status);
    }
}
