package woo.core.exception;

public class NotAvaliableProductException extends Exception{
    private String _key;
    private int _requested;
    private int _avaliable;

    public NotAvaliableProductException(String key, int requested, int avaliable) {
        _key = key;
        _requested = requested;
        _avaliable = avaliable;
    }

    public String getKey(){
        return _key;
    }
    public int getRequested(){
        return _requested;
    }
    public int getAvaliable(){
        return _avaliable;
    }
}
