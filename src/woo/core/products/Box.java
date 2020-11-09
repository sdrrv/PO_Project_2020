package woo.core.products;

import java.io.Serializable;

public class Box extends Product implements Serializable {
    private ServiceType _serviceType;

    public Box(int price,int valorCrit,String key, ServiceType serviceType){
        super(price,valorCrit,key);
        _serviceType = serviceType;
    }
    public String getServiceType(){
        return _serviceType.toString();
    }

    public String toString(){
        return "BOX"+"|"+super.getKey()+"|"+super.getprice()+"|"+super.getValCrit()+"|"+getValExist()+"|"
                +getServiceType();
    }
}
