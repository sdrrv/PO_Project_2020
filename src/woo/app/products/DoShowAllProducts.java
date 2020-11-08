package woo.app.products;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;
import java.util.Collection;
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

    Collection<String> products = _receiver.getAllProducts();
    for(String pd : products)
      _display.addLine(pd);

    _display.display();
  }
}
