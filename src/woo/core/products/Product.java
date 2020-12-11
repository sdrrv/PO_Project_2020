package woo.core.products;

import woo.core.users.Client;
import woo.core.users.Supplier;
import woo.core.users.User;

import java.io.Serializable;
import java.util.List;

public abstract class Product implements Serializable,Comparable {
    private final int _n;
    private int _price;
    private int _valCrit;
    private int _valExist;
    private final String _key;
    private final Supplier _supplier;

    protected Product(int price, int valCrit, String key, Supplier supplier,int n) {
        _price = price;
        _valCrit = valCrit;
        _valExist = 0;
        _key = key;
        _supplier=supplier;
        _n=n;
    }

    public int getPrice() {
        return _price;
    }

    public int getValCrit() {
        return _valCrit;
    }

    public int getValExist() {
        return _valExist;
    }

    public String getKey() {
        return _key;
    }

    public Supplier getSupplier(){
        return _supplier;
    }

    public void setPrice(int price) { _price = price; } //Set the price to the price given -- Used int the DoChange Price

    public void decreaseValue(int value) {
        _valExist -= value;
    }

    public void increaseValue(int value) {
        _valExist += value;
    }

    public void setValue(int value){_valExist = value;}

    public abstract String toString();

    public int compareTo(Object o) {
        Product e;
        if (!(o instanceof Product)) {
            return 0;
        }
        e = (Product) o;
        return _key.compareTo(e.getKey().toUpperCase());
    }

    public int getN(){
        return _n;
    }
}