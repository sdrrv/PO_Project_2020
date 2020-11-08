package woo.core.exception;

public class DuplicateSupplierIdException extends Exception{

    private String _key;

    public DuplicateSupplierIdException(String key) {
        _key = key;
    }

    public String getKey(){
        return _key;
    }

}
