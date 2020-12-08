package woo.core.exception;

public class UnknownTransactionIdException extends Exception{
    private int _key;

    public  UnknownTransactionIdException(int key) {
        _key = key;
    }

    public int getKey(){
        return _key;
    }
}
