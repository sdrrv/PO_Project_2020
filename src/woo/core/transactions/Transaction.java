package woo.core.transactions;

public abstract class Transaction {
    private final int _key;
    private final TransType _type;
    private int _payDay;
    private int _price;

    public Transaction(int key,int price, TransType type){
        _key = key;
        _type = type;
        _payDay = -1;
        _price = price;
    }

    public int getKey(){
        return _key;
    }
    public String getType(){
        return _type.toString();
    }
    public int getPayDay(){return _payDay;}
    public void setPayDay(int value){ _payDay = value;}
    public int getPrice() {
        return _price;
    }
    public void addToPrice(int amount){_price+=amount;}
    public abstract String toString(int date);



}
