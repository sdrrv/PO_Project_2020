package woo.core.products;

import woo.core.users.Supplier;

import java.io.Serializable;

public class Container extends Product implements Serializable {
    private ServiceType _serviceType;
    public ServiceLevel _serviceLevel;
    public Container(int price, int valorCrit, String key, ServiceType serviceType, ServiceLevel serviceLevel, Supplier supplier){
        super(price,valorCrit,key,supplier,8);
        _serviceType = serviceType;
        _serviceLevel = serviceLevel;
    }
    public String getServiceType(){
        return _serviceType.toString();
    }
    public String getServiceLevel(){
        return _serviceLevel.toString();
    }

    public String toString(){
        return "CONTAINER"+"|"+super.getKey()+"|"+super.getSupplier().getId()+"|"+super.getPrice()+"|"+super.getValCrit()+"|"
                +getValExist()+"|" +getServiceType()+"|"+getServiceLevel();
    }
}
