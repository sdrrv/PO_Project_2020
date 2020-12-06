package woo.core.transactions;

import woo.core.products.Product;
import woo.core.users.Client;

import java.security.PublicKey;

public class Sale extends Transaction{
    private int _dateLim;
    private boolean _isPaid;
    private double _finalPrice;

    private Client _client;
    private ProductPlus _productPlus;


    public Sale(int key,int dateLim, int price, Client client, Product product, int amount){
        super(key,price,TransType.Sale);
        _dateLim = dateLim;
        _client = client;
        _productPlus = new ProductPlus(amount,product);
    }

    public double getFinalPrice() {
        return _finalPrice;
    }
    public int getDateLim(){
        return _dateLim;
    }

    public void Pay(){
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


    private String toStringMixer(){
        return (super.getKey() +"|"+
                getProduct().getKey()+"|"+
                _productPlus.getAmount()+"|"+
                super.getPrice()+"|"+
                _finalPrice+"|"+
                _dateLim+"|");
    }


    public String toString() {
        if(_isPaid){
            return toStringMixer() + "|" + super.getPayDay();
        }
        return toStringMixer();
    }
}

