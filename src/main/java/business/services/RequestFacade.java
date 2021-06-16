package business.services;

import business.entities.Request;
import business.entities.Request_obj;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.RequestMapper;

import java.sql.SQLException;
import java.util.List;

public class RequestFacade {
    RequestMapper requestMapper;

    public RequestFacade(Database database) {
        requestMapper = new RequestMapper(database);

    }

    public Request_obj createRequest(Request_obj request1) throws UserException {
        return requestMapper.CreateRequest(request1);
    }

    public List<Request_obj> getAllRequest2(String status) throws UserException {
        return requestMapper.getAllRequest2(status);
    }
    public List<Request_obj> getAllRequest3(int user_id, String status) throws UserException {
        return requestMapper.getAllRequest3(user_id,status);
    }


    public void markAsFailed(Request_obj request_obj) {
        requestMapper.markAsFailed(request_obj);
    }

    public Request_obj getRequest(int request_id) throws UserException {
       return requestMapper.getRequest(request_id);
    }

    public void updateRequestStatus(int request_id, String status) {
        requestMapper.updateRequestStatus(request_id,status);
    }
    public List<Request_obj> getAllRequest1() throws UserException {
        return requestMapper.getAllRequest1();
    }

    public void removeRequestFromDB(int request_id) {
        requestMapper.removeRequestFromDB(request_id);
    }


    public Request_obj CreateRequest_standard(Request_obj request) throws UserException {
        return requestMapper.CreateRequest_standard(request);
    }
}
