package woo.core.users.status;

import woo.core.transactions.Sale;

public class Elite extends Status{
    private static Elite _elite;
    private Elite(){}

    public static Elite getInstance(){
        if(_elite==null){
            _elite = new Elite();
        }
        return _elite;
    }
    public double p2(int price,int daysAfterDeadLine){
        return price;
    }

    public double p3(int price,int daysAfterDeadLine){
        return ( price * (1+0.05*daysAfterDeadLine) ) ;
    }

    public double p4(int price,int daysAfterDeadLine){
        return ( price * (1+0.10*daysAfterDeadLine) ) ;
    }
}
