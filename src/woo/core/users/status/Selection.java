package woo.core.users.status;

public class Selection implements Status{
    private static Selection _selection;
    private Selection(){}

    public static Selection getInstance(){
        if (_selection == null){
            _selection = new Selection();
        }
        return _selection;
    }

    @Override
    public double getToPay() {
        return 0;
    }
}
