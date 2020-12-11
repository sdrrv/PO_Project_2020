package woo.core.users;

import woo.core.notifications.Notification;
import woo.core.transactions.Sale;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import woo.core.users.status.*;

import javax.swing.plaf.nimbus.State;

public class Client extends User implements Serializable {
    private int _points;
    private ClientStatus _status;
    private Status _state;
    private List<Sale> _transactions;
    private List<Notification> _notifications;

    public Client(String name, String address, String id){
        super(name, address, id);
        _points = 0;
        _status = ClientStatus.NORMAL;
        _state = Normal.getInstance();
        _transactions = new LinkedList<>();
        _notifications = new LinkedList<>();
    }

    public ClientStatus getStatus(){
        return _status;
    }

    public int getPoints(){
        return _points;
    }

    private void statusCheck(){
        if(_points<2000){
            _status = ClientStatus.NORMAL;
            _state = Normal.getInstance();
        }
        else if (( _points>=2000 ) && ( _points<=25000) ){
            _status = ClientStatus.SELECTION;
            _state = Selection.getInstance();
        }
        else{
            _status = ClientStatus.ELITE;
            _state = Elite.getInstance();
        }
    }

    public void addPoints(int value){
        _points += value;
        statusCheck();
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

    public String toString(){
        return (super.getId()+"|"+super.getName()+"|"+super.getAddress()+"|"+_status.toString()+"|"+"0"+"|"+"0");
    }

    

}
