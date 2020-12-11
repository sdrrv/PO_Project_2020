package woo.core;

import woo.core.notifications.Description;
import woo.core.notifications.Notification;
import woo.core.notifications.NotificationsObserver;
import woo.core.products.Product;
import woo.core.users.Client;
import woo.core.users.User;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NotificationHandler implements Serializable {
    private static NotificationHandler _notificationHandler;

    private Map<NotificationsObserver, List<String>> _observers;   // Clients keys ---- String of Products keys not to be used
    private NotificationHandler(){
        _observers = new HashMap<>();
    }

    public static NotificationHandler getInstance(){
        if(_notificationHandler== null)
            _notificationHandler = new NotificationHandler();

        return _notificationHandler;
    }

    public void addObserver(NotificationsObserver observer){
        _observers.put(observer, new LinkedList<>());
    }
    public void removeObserver(NotificationsObserver observer){
        _observers.remove(observer);
    }
    public void addProductToTheRemovedList(NotificationsObserver observer,String productKey){
        _observers.get(observer).add(productKey);
    }

    public void addNotification(Product product, String description, String deliveryMethod){
        Notification notification = new Notification(product, Description.valueOf(description),deliveryMethod);
        for(NotificationsObserver observer: _observers.keySet()){
            if (!_observers.get(observer).contains(product.getKey())){
                observer.receiveNotification(notification);
            }
        }
    }
}
