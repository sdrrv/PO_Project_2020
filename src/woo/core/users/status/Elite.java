package woo.core.users.status;

import woo.core.transactions.Sale;
import woo.core.transactions.Transaction;
import woo.core.users.Client;

import java.io.Serializable;

public class Elite extends Status implements Serializable {
    private static Elite _elite;
    private Elite(){}

    public static Elite getInstance(){
        if(_elite==null){
            _elite = new Elite();
        }
        return _elite;
    }
    public double p2(int price,int daysAfterDeadLine){
        return price*0.90;
    }

    public double p3(int price,int daysAfterDeadLine){
        return price*0.95;
    }

    public double p4(int price,int daysAfterDeadLine){
        return price;
    }

    public void demotion(Client client, int date, Sale sale) {
        if(date-sale.getDateLim()>15){
            client.setStateSelection();
            client.removePoints( client.getPoints()*0.75 );
        }

    }

    public String toString(){
        return "ELITE";
    }
}
