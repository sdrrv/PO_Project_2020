package woo.core.notifications;

import woo.core.products.Product;

public class Notification {
    private Product _product;
    private Description _desc;
    private String _deliMethod;

    public Notification(Product product, Description desc){
        _product=product;
        _desc = desc;
        _deliMethod = "";
    }
    public Notification(Product product, Description desc, String deliMethod){
        _product=product;
        _desc = desc;
        _deliMethod = deliMethod;
    }

    public Product getProduct() {
        return _product;
    }
    public Description getDesc() {
        return _desc;
    }
    public String getDeliMethod() {
        return _deliMethod;
    }

    public String toString(){
        return getDesc().toString()+"|"+getProduct().getKey()+"|"+getProduct().getPrice();
    }
}
