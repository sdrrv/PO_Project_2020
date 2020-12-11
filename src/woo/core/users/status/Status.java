package woo.core.users.status;

import woo.core.transactions.Sale;
import woo.core.transactions.Transaction;
import woo.core.users.Client;

public abstract class Status {

    public double getToPay(Sale sale, int date){
        int daysAfterDeadLine = date-sale.getDateLim();
        int n = sale.getProduct().getN();

        if(-daysAfterDeadLine >= n){
            return p1(sale.getPrice());
        }
        else if ( 0<= -daysAfterDeadLine && -daysAfterDeadLine<n){
            return p2(sale.getPrice(),daysAfterDeadLine);
        }
        else if(0<daysAfterDeadLine && daysAfterDeadLine<= n){
            return p3(sale.getPrice(), daysAfterDeadLine);
        }
        else if (daysAfterDeadLine > n){
            return p4(sale.getPrice(),daysAfterDeadLine);
        }
        return 0;
    }

    public double p1(int price){
        return price * 0.90 ;
    }
    public abstract double p2(int price,int daysAfterDeadLine);
    public abstract double p3(int price,int daysAfterDeadLine);
    public abstract double p4(int price,int daysAfterDeadLine);

    public abstract void demotion(Client client, int date, Sale sale);
    public abstract String toString();
}
