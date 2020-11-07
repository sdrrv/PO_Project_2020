package core.users;

public class Supplier extends User{
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
}
