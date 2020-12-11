package woo.core.notifications;

public class Notification {
    private String _productId;
    private Description _desc;
    private String _deliMethod;

    public Notification(String productId, Description desc, String deliMethod){
        _productId=productId;
        _desc = desc;
        _deliMethod = deliMethod;
    }

    public Notification(String productId, Description desc){
        _productId=productId;
        _desc = desc;
    }

    public String getProductid() {
        return _productId;
    }
    public Description getDesc() {
        return _desc;
    }
    public String getDeliMethod() {
        return _deliMethod;
    }
}
