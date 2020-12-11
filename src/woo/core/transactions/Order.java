package woo.core.transactions;

import woo.core.products.Product;
import woo.core.users.Supplier;

import java.util.LinkedList;
import java.util.List;

public class Order extends Transaction{
    private final Supplier _supplier;

    private List<ProductPlus> _productsPlus;

    public Order(int key, Supplier supplier){
        super(key,0,TransType.Order);
        _supplier= supplier;
        _productsPlus = new LinkedList<>();

    }

    public void addProduct(ProductPlus product){
        _productsPlus.add(product);
        super.addToPrice(product.getAmount()*product.getProduct().getPrice());
    }

    public String toString(int date){
        String result = super.getKey()+"|"+
                _supplier.getId()+"|"+
                super.getPrice()+"|"+
                super.getPayDay()+"\n";

        for (ProductPlus i: _productsPlus){
            result = result + i.getProduct().getKey()+"|"+i.getAmount()+"\n";
        }
        return result;
    }
}
