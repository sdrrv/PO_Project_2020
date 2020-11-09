package woo.core.products;

import java.io.Serializable;

public class Container extends Product implements Serializable {
    private ServiceType _serviceType;
    public ServiceLevel _serviceLevel;
    public Container(int price,int valorCrit,String key, ServiceType serviceType,ServiceLevel serviceLevel){
        super(price,valorCrit,key);
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
        return "BOX"+"|"+super.getKey()+"|"+super.getprice()+"|"+super.getValCrit()+"|"+getValExist()+"|"
                +getServiceType()+"|"+getServiceLevel();
    }
}
