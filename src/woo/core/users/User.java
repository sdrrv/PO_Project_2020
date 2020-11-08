package woo.core.users;

import java.io.Serializable;

public abstract class User implements Serializable {
    private String _name;
    private String _address;
    private String _id;

    protected User(String name, String address, String id){
        _name= name;
        _address= address;
        _id= id;
    }

    public String getname(){
        return _name;
    }
    public String getaddress(){
        return _address;
    }
    public String getid(){
        return _id;
    }
}

