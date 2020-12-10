package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;
import woo.core.exception.UnknownClientIdException;
import woo.app.exception.UnknownClientKeyException;
import woo.core.exception.UnknownProductIdException;
import woo.app.exception.UnknownProductKeyException;

/**
 * Toggle product-related notifications.
 */
public class DoToggleProductNotifications extends Command<StoreManager> {

  private Input<String> _clientKey, _productKey;

  public DoToggleProductNotifications(StoreManager storefront) {
    super(Label.TOGGLE_PRODUCT_NOTIFICATIONS, storefront);
    _clientKey = _form.addStringInput(Message.requestClientKey());
    _productKey = _form.addStringInput(Message.requestProductKey());

  }

  @Override
  public void execute() throws DialogException {
    _form.parse();
    try{
      _receiver.activateNotification(_clientKey.value(),_productKey.value());
    }catch(UnknownClientIdException e){
      throw new UnknownClientKeyException(_clientKey.value());
    }catch(UnknownProductIdException e){
    throw new UnknownProductKeyException(_clientKey.value());
    }
  }
}
