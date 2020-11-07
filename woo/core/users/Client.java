package app.clients;

import java.util.List;

import app.clients.ClientStatus;

import java.util.LinkedList;


public class Client extends User{
    private ClientStatus _status;

    public Client(String name, String address, String id){
        super(name, address, id);
        _status = ClientStatus.NORMAL;
    }

    public ClientStatus getStatus(){
        return _status;
    }

    

}
