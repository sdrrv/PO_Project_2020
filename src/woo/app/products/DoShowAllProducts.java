package woo.app.products;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;
//FIXME import other classes

/**
 * Show all products.
 */
public class DoShowAllProducts extends Command<StoreManager> {


  public DoShowAllProducts(StoreManager receiver) {
    super(Label.SHOW_ALL_PRODUCTS, receiver);
  }

  @Override
  public final void execute() throws DialogException {

    Collection<Product> products = _receiver.getProducts();
    for(Product pd : products)
      _display.addLine(pd.toString());

    _display.display();
  }
}
