package woo.core.transactions;

import woo.core.products.Product;
import woo.core.users.Client;

import java.security.PublicKey;

public class Sale extends Transaction{
    private int _dateLim;
    private boolean _isPaid;
    private int _price;
    private double _FinalPrice;

    private Client _client;
    private ProductPlus _productPlus;


    public Sale(int key, int staticKey,int dateLim, int price, Client client, ProductPlus productPlus){
        super(key, staticKey,TransType.Sale);
        _dateLim = dateLim;
        _price = price;
        _client = client;
        _productPlus = productPlus;
    }

    public int getprice() {
        return _price;
    }
    public double getFinalPrice() {
        return _FinalPrice;
    }
    public int getDateLim(){
        return _dateLim;
    }

    public void pay(){
        _isPaid = true;
    }

    public Client getClient(){
        return _client;
    }

    public Product getProduct(){
       return _productPlus.getProduct();
    }
    public int getAmount(){
        return  _productPlus.getAmount();
    }
}
