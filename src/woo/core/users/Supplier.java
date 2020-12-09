package woo.core.users;

import woo.core.products.Product;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Supplier extends User implements Serializable {
    private boolean _isActive;
    private Map<String, Product> _products;

    public Supplier(String name, String address, String id){
        super(name, address, id);
        _products = new HashMap<>();
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
        _products.put(product.getKey(),product);
    }

    public boolean hasProduct(String id){
        return _products.containsKey(id);
    }

    public String toString(){
        return (super.getId()+"|"+super.getName()+"|"+super.getAddress()+"|");
    }
}
