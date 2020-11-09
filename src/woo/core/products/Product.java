package woo.core.products;

import woo.core.users.Supplier;
import woo.core.users.User;

import java.io.Serializable;

public abstract class Product implements Serializable,Comparable {
    private int _price;
    private int _valCrit;
    private int _valExist;
    private String _key;
    private Supplier _supplier;

    protected Product(int price, int valCrit, String key, Supplier supplier) {
        _price = price;
        _valCrit = valCrit;
        _valExist = 0;
        _key = key;
        _supplier=supplier;
    }

    public int getprice() {
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

    public void setprice(int price) {
        _price = price;
    }

    public void decreaseValue(int value) {
        _valExist -= value;
    }

    public void addValue(int value) {
        _valExist += value;
    }

    public abstract String toString();

    public int compareTo(Object o) {
        Product e;
        if (!(o instanceof Product)) {
            return 0;
        }
        e = (Product) o;
        return _key.compareTo(e.getKey());
    }
}