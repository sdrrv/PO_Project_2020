package woo.core.transactions;

public abstract class Transaction {
    private final String _key;
    private static int _staticKey;
    private final TransType _type;
    private int _payDay;

    public Transaction(String key, int staticKey,int payDay, TransType type){
        _key = key;
        _staticKey = staticKey;
        _type = type;
        _payDay = payDay;
    }

    public String getKey(){
        return _key;
    }
    public int getStaticKey(){
        return _staticKey;
    }
    public String getType(){
        return _type.toString();
    }
    public int getPayDay(){return _payDay;}
}
