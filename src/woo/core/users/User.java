package woo.core.users;

import java.io.Serializable;

public abstract class User implements Serializable,Comparable {
    private String _name;
    private String _address;
    private String _id;

    protected User(String name, String address, String id){
        _name= name;
        _address= address;
        _id= id;
    }

    public String getName(){
        return _name;
    }
    public String getAddress(){
        return _address;
    }
    public String getId(){
        return _id;
    }


    public int compareTo(Object o) {
        User e;
        if (!(o instanceof User)) {
            return 0;
        }
        e = (User) o;
        return _id.compareTo(e.getId());

    }
}

