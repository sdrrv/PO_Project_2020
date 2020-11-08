package woo.core.products;

import java.io.Serializable;

public abstract class Product implements Serializable {
    private int _price;
    private int _valCrit;
    private int _valExist;
    private int _key;

    protected Product(int price,int valCrit, int valExist, int key){
        _price = price;
        _valCrit = valCrit;
        _valExist = valExist;
        _key = key;
    }

    public int getprice(){
        return _price;
    }
    public int getValCrit(){
        return _valCrit;
    }
    public int getValExist(){
        return _valExist;
    }

    public void setprice(int price){
        _price= price;
    }

    public void decreaseValue(int value){
        _valExist -= value;
    }
    public void addValue(int value){
        _valExist += value;
    }

}