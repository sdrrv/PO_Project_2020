package woo.core.transactions;

import woo.core.products.Product;
import woo.core.users.Client;

import java.security.PublicKey;

 public class Sale extends Transaction{
    private int _dateLim;
    private boolean _isPaid;

    private Client _client;
    private ProductPlus _productPlus;
    private double _valuePaid;


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
        super.setPayDay(date);
        if( date> _dateLim)
            _client.demotion(date,this);
        else{
            _client.addPoints(super.getPrice()*10);
        }
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
                getClient().getId()+"|"+
                getProduct().getKey()+"|"+
                _productPlus.getAmount()+"|"+
                super.getPrice()+"|"+
                (int)getFinalPrice(date)+"|"+
                _dateLim);
    }

     public double getValuePaid(){
         return _valuePaid;
     }

     public void setValuePaid(double valuePaid){
         _valuePaid = valuePaid;
     }

    public String toString(int date) {
        if(_isPaid){
            return toStringMixer(date) + "|" + super.getPayDay();
        }
        return toStringMixer(date);
    }

}

