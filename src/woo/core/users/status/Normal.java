package woo.core.users.status;

public class Normal implements Status{
    private static Normal _normal;
    private Normal(){}

    public static Normal getInstance(){
        if(_normal==null){
            _normal = new Normal();
        }
        return _normal;
    }

    public double getToPay(){
        return 0;
    }
}
