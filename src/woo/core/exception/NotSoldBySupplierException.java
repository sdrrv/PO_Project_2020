package woo.core.exception;

public class NotSoldBySupplierException extends Exception{
    private String _supplierId;
    private String _productId;

    public NotSoldBySupplierException(String supplierId,String productId){
        _supplierId=supplierId;
        _productId= productId;
    }

    public String getProductId() {
        return _productId;
    }

    public String getSupplierId() {
        return _supplierId;
    }
}
