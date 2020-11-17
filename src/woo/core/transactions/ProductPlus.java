package woo.core.transactions;

import woo.core.products.Product;

public class ProductPlus {
    private int _amount;
    private Product _product;

    public ProductPlus(int amount, Product product){
        _amount = amount;
        _product = product;
    }

    public Product getProduct() {
        return _product;
    }

    public int getAmount() {
        return _amount;
    }
}
