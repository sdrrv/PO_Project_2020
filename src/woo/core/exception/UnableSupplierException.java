package woo.core.exception;

public class UnableSupplierException extends Exception{
    private String _id;

    public UnableSupplierException(String id) {
        _id = id;
    }

    public String getId(){return _id;}
}
