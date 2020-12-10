package woo.app.products;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.UnknownProductKeyException;
import woo.core.StoreManager;
import woo.core.exception.UnknownProductIdException;
//FIXME import other classes

/**
 * Change product price.
 */
public class DoChangePrice extends Command<StoreManager> {

  Input<String> _productId;
  Input<Integer> _newPrice;

  public DoChangePrice(StoreManager receiver) {
    super(Label.CHANGE_PRICE, receiver);
    _productId = _form.addStringInput(Message.requestProductKey());
    _newPrice = _form.addIntegerInput(Message.requestPrice());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try{
      _receiver.changeProductPrice(_productId.value(),_newPrice.value());
    }
    catch (UnknownProductIdException e){
      throw new UnknownProductKeyException(e.getId());
    }
  }
}
