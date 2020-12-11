package woo.core.users.status;

public class Elite implements Status{
    private static Elite _elite;
    private Elite(){}

    public static Elite getInstance(){
        if(_elite==null){
            _elite = new Elite();
        }
        return _elite;
    }

    @Override
    public double getToPay(){
        return 0;
    }
}
