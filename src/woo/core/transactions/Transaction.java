package woo.core.transactions;

public abstract class Transaction {
    private final int _key;
    private static int _staticKey;
    private final TransType _type;

    public Transaction(int key, int staticKey, TransType type){
        _key = key;
        _staticKey = staticKey;
        _type = type;
    }

    public int getKey(){
        return _key;
    }
    public int getStaticKey(){
        return _staticKey;
    }
    public String getType(){
        return _type.toString();
    }
}
