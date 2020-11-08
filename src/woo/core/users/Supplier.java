package woo.core.users;

import java.io.Serializable;

public class Supplier extends User implements Serializable {
    private boolean _isActive;

    public Supplier(String name, String address, String id){
        super(name, address, id);
        _isActive=true;
    }

    public boolean isActive(){
        return _isActive;
    }

    public boolean toogleActivation(){
        _isActive = !_isActive;
        return _isActive;
    }

    public String toString(){
        return (super.getid()+"|"+super.getname()+"|"+super.getaddress()+"|"+String.valueOf(isActive()));
    }
}
