package woo.core.products;

import woo.core.users.Supplier;

import java.io.Serializable;

public class Box extends Product implements Serializable {
    private ServiceType _serviceType;

    public Box(int price, int valorCrit, String key, ServiceType serviceType, Supplier supplier){
        super(price,valorCrit,key,supplier);
        _serviceType = serviceType;
    }
    public String getServiceType(){
        return _serviceType.toString();
    }

    public String toString(){
        return "BOX"+"|"+super.getKey()+"|"+super.getSupplier().getId()+"|"+super.getprice()+"|"+super.getValCrit()+"|"
                +getValExist()+"|" +getServiceType();
    }
}
