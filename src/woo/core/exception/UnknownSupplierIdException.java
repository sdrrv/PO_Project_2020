package woo.core.exception;

public class UnknownSupplierIdException extends UnknownIdException{

    private String _id;

    public UnknownSupplierIdException(String id) {
        _id = id;
    }

    public String getId(){return _id;}

}