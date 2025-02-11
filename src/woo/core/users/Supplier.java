package woo.core.users;

import woo.core.products.Product;
import woo.core.transactions.Order;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Supplier extends User implements Serializable {
    private boolean _isActive;
    private Map<String, Product> _products;
    private Map<Integer, Order> _orders;

    public Supplier(String name, String address, String id){
        super(name, address, id);
        _products = new HashMap<>();
        _orders = new HashMap<>();
        _isActive=true;
    }

    public boolean isActive(){
        return _isActive;
    }

    public boolean toogleActivation(){
        _isActive = !_isActive;
        return _isActive;
    }

    public void addToProducts(Product product){
        _products.put(product.getKey().toUpperCase(),product);
    }

    public boolean hasProduct(String id){
        return _products.containsKey(id.toUpperCase());
    }

    public void addToOrders(Order order){
        _orders.put(order.getKey(),order);
    }

    public Collection<Order> getOrders(){
        return _orders.values();
    }

    public String toString(){
        return (super.getId()+"|"+super.getName()+"|"+super.getAddress()+"|");
    }
}
