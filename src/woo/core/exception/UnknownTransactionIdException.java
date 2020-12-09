package woo.core.exception;

public class UnknownTransactionIdException extends UnknownIdException{
    private int _key;

    public  UnknownTransactionIdException(int key) {
        _key = key;
    }

    public int getKey(){
        return _key;
    }
}
