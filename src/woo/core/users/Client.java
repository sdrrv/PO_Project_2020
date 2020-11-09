package woo.core.users;

import woo.core.users.ClientStatus;

import java.io.Serializable;

public class Client extends User implements Serializable {
    private ClientStatus _status;

    public Client(String name, String address, String id){
        super(name, address, id);
        _status = ClientStatus.NORMAL;
    }

    public ClientStatus getStatus(){
        return _status;
    }

    public String toString(){
        return (super.getId()+"|"+super.getName()+"|"+super.getAddress()+"|"+_status.toString()+"|"+"0"+"|"+"0");
    }

    

}
