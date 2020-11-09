package woo.core.exception;

public class DuplicateProductIdException extends Exception{
    private String _key;

    public DuplicateProductIdException(String key) {
        _key = key;
    }

    public String getKey(){
        return _key;
    }
}
