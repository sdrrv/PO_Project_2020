package woo.core.transactions;

import woo.core.products.Product;
import woo.core.users.Client;

import java.security.PublicKey;

 public class Sale extends Transaction{
    private int _dateLim;
    private boolean _isPaid;

    private Client _client;
    private ProductPlus _productPlus;


    public Sale(int key,int dateLim, int price, Client client, Product product, int amount){
        super(key,price,TransType.Sale);
        _dateLim = dateLim;
        _client = client;
        _productPlus = new ProductPlus(amount,product);
    }

    public double getFinalPrice(int date) {
        return getClient().getState().getToPay(this,date);
    }
    public int getDateLim(){
        return _dateLim;
    }


    public void pay(int date){
        _isPaid = true;
        if( date> _dateLim)
            _client.demotion(date,this);
    }


    public boolean isPaid(){
        return _isPaid;
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


    private String toStringMixer(int date){
        return (super.getKey() +"|"+
                getProduct().getKey()+"|"+
                _productPlus.getAmount()+"|"+
                super.getPrice()+"|"+
                getFinalPrice(date)+"|"+
                _dateLim+"|");
    }


    public String toString(int date) {
        if(_isPaid){
            return toStringMixer(date) + "|" + super.getPayDay();
        }
        return toStringMixer(date);
    }
}

