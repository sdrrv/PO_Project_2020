package woo.core.notifications;

public class Notification {
    private int _id;
    private Description _desc;
    private DeliveryMethod _deliMethod;

    public Notification(int id, Description desc, DeliveryMethod deliMethod){
        _id=id;
        _desc = desc;
        _deliMethod = deliMethod;
    }
    public int getId() {
        return _id;
    }
    public Description getDesc() {
        return _desc;
    }
    public DeliveryMethod getDeliMethod() {
        return _deliMethod;
    }
}
