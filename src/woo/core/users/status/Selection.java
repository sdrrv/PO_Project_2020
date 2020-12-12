package woo.core.users.status;

import woo.core.transactions.Sale;
import woo.core.users.Client;

import java.io.Serializable;

public class Selection extends Status implements Serializable {
    private static Selection _selection;

    private Selection() {
    }

    public static Selection getInstance() {
        if (_selection == null) {
            _selection = new Selection();
        }
        return _selection;
    }

    public double p2(int price, int daysAfterDeadLine) {

        if (-daysAfterDeadLine<=2)
            return price*0.95;
        return price;
    }

    public double p3(int price, int daysAfterDeadLine) {
        if (daysAfterDeadLine<=1)
            return price;
        return (price * (1 + 0.02 * daysAfterDeadLine));
    }

    public double p4(int price, int daysAfterDeadLine) {
        return (price * (1 + 0.05 * daysAfterDeadLine));
    }

    public void demotion(Client client, int date, Sale sale) {
        if(date-sale.getDateLim()>2){
            client.setStateNormal();
            client.removePoints( client.getPoints()*0.90 );
        }

    }

    public String toString(){
        return "SELECTION";
    }
}