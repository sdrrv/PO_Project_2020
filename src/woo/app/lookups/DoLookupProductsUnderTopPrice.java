package woo.app.lookups;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;

import java.util.List;
import woo.core.products.Product;

/**
 * Lookup products cheaper than a given price.
 */
public class DoLookupProductsUnderTopPrice extends Command<StoreManager> {

  Input<Integer> _amount;

  public DoLookupProductsUnderTopPrice(StoreManager storefront) {
    super(Label.PRODUCTS_UNDER_PRICE, storefront);
    _amount = _form.addIntegerInput(Message.requestPriceLimit());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();

    List<Product> productsBellowAmount= _receiver.getProductsBellowAmount(_amount.value());
    for (Product product: productsBellowAmount){
      _display.addLine(product.toString());
    }
    _display.display();
  }
}
