package woo.core.exception;

public class UnknownProductIdException extends UnknownIdException{
    private String _id;

    public UnknownProductIdException(String id) {
        _id = id;
    }

    public String getId(){return _id;}
}
