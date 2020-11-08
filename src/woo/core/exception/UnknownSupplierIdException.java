package woo.core.exception;

public class UnknownSupplierIdException extends Exception{

    private String _id;

    public UnknownSupplierIdException(String id) {
        _id = id;
    }

    public String getId(){return _id;}

}