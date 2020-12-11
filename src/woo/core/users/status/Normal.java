package woo.core.users.status;

import woo.core.transactions.Sale;
import woo.core.users.Client;

public class Normal extends Status{
    private static Normal _normal;
    private Normal(){}

    public static Normal getInstance(){
        if(_normal==null){
            _normal = new Normal();
        }
        return _normal;
    }
    public double p2(int price,int daysAfterDeadLine){
        return price;
    }

    public double p3(int price,int daysAfterDeadLine){
        return ( price * (1+0.05*daysAfterDeadLine) ) ;
    }

    public double p4(int price,int daysAfterDeadLine){
        return ( price * (1+0.10*daysAfterDeadLine) ) ;
    }

    public void demotion(Client client, int date, Sale sale) {}

    public String toString(){
        return "NORMAL";
    }



}
