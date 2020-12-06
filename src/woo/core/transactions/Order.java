package woo.core.transactions;

import woo.core.products.Product;
import woo.core.users.Supplier;

import java.util.List;

public class Order extends Transaction{
    private int _totalCost;

    private Supplier _supplier;

    private List<ProductPlus> _productsPlus;

    public Order(String key, int staticKey, int payDay, Supplier supplier){
        super(key,staticKey,payDay,TransType.Order);
        _totalCost=0;
        _supplier= supplier;
    }

    public void addProduct(Product product, int amount){
        product.decreaseValue(amount);
        _productsPlus.add(new ProductPlus(amount,product));
    }
}
