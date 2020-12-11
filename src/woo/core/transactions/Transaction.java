package woo.core.transactions;

import woo.core.users.User;

import java.io.Serializable;

public abstract class Transaction implements Serializable,Comparable {
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

    public int compareTo(Object o) {
        Transaction e;
        if (!(o instanceof Transaction)) {
            return 0;
        }
        e = (Transaction) o;
        return _key-e.getKey();

    }



}
