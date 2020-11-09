package woo.core.exception;

public class WrongServiceLevelException  extends Exception{
    private String _serviceLevel;
    public WrongServiceLevelException(String serviceLevel){
        _serviceLevel = serviceLevel;
    }

    public String getServiceLevel(){
        return  _serviceLevel;
    }

}
