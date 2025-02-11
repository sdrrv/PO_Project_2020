package woo.core.users;

import woo.core.notifications.Notification;
import woo.core.notifications.NotificationsObserver;
import woo.core.transactions.Sale;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import woo.core.users.status.*;

import javax.swing.plaf.nimbus.State;

public class Client extends User implements Serializable, NotificationsObserver {
    private double _points;
    private Status _state;
    private List<Sale> _transactions;
    private List<Notification> _notifications;

    public Client(String name, String address, String id){
        super(name, address, id);
        _points = 0;
        _state = Normal.getInstance();
        _transactions = new LinkedList<>();
        _notifications = new LinkedList<>();
    }


    public double getPoints(){
        return _points;
    }

    private void statusCheck(){
        if(_points<2000){
            _state = Normal.getInstance();
        }
        else if (( _points>=2000 ) && ( _points<=25000) ){
            _state = Selection.getInstance();
        }
        else{
            _state = Elite.getInstance();
        }
    }

    public void addPoints(int value){
        _points += value;
        statusCheck();
    }

    public void removePoints(double value){
        _points -= value;
    }

    public List<Notification> getNotifications(){
        return _notifications;
    }

    public void clearNotifications(){
        _notifications.clear();
    }

    public void addSale(Sale sale){
        _transactions.add(sale);
    }

    public List<Sale> getSales(){
        return _transactions;
    }

    public List<Sale> getPaiedSales(){
        List<Sale> result = new LinkedList<>();
        for(Sale sale : _transactions){
            if(sale.isPaid()){
                result.add(sale);
            }
        }
        return result;
    }

    public Status getState(){
        return _state;
    }

    public void setStateNormal(){
        _state = Normal.getInstance();
    }

    public void setStateSelection(){
        _state = Selection.getInstance();
    }
    public void setStateElite(){
        _state = Elite.getInstance();
    }

    public void demotion(int date,Sale sale){
        _state.demotion(this,date,sale);
    }

    public void receiveNotification(Notification notification){
        _notifications.add(notification);
    }

    public int getSalesValue(){
        double result =0;
        for (Sale sale: _transactions){
            result += sale.getPrice();
        }
        return (int) result;
    }

    public int getPaidSalesValue(){
        double result = 0;
        for (Sale sale : _transactions){
            if(sale.isPaid()){
                result += sale.getValuePaid();
            }
        }
        return (int)result;
    }

    public String toString(){
        return (super.getId()+"|"+super.getName()+"|"+super.getAddress()+"|"+_state.toString()+"|"+getSalesValue()+"|"+getPaidSalesValue());
    }

    

}
