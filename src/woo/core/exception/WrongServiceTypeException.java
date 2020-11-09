package woo.core.exception;

public class WrongServiceTypeException extends Exception{
    private String _serviceType;
    public WrongServiceTypeException(String serviceType){
        _serviceType = serviceType;
    }

    public String getServiceType(){
        return  _serviceType;
    }

}
