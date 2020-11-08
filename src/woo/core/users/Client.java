package woo.core.users;

import woo.core.users.ClientStatus;

public class Client extends User{
    private ClientStatus _status;

    public Client(String name, String address, String id){
        super(name, address, id);
        _status = ClientStatus.NORMAL;
    }

    public ClientStatus getStatus(){
        return _status;
    }

    public String toString(){
        return (super.getid()+"|"+super.getname()+"|");
    }

    

}
