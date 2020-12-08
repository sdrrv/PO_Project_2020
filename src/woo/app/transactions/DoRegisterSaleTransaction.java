package woo.app.transactions;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.UnavailableProductException;
import woo.app.exception.UnknownClientKeyException;
import woo.app.exception.UnknownProductKeyException;
import woo.core.StoreManager;
import woo.core.exception.NotAvaliableProductException;
import woo.core.exception.UnknownClientIdException;
import woo.core.exception.UnknownProductIdException;
//FIXME import other classes

/**
 * Register sale.
 */
public class DoRegisterSaleTransaction extends Command<StoreManager> {

  private Input<String> _clientId,_productId;
  private Input<Integer> _dataLim,_quantity;

  public DoRegisterSaleTransaction(StoreManager receiver) {
    super(Label.REGISTER_SALE_TRANSACTION, receiver);
    _clientId = _form.addStringInput(Message.requestClientKey());
    _dataLim = _form.addIntegerInput(Message.requestPaymentDeadline());
    _productId = _form.addStringInput(Message.requestProductKey());
    _quantity = _form.addIntegerInput(Message.requestAmount());
  }
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try{
      _receiver.registerSale(_clientId.value(),_dataLim.value(),_productId.value(), _quantity.value());
    }
    catch (UnknownClientIdException e){
      throw new UnknownClientKeyException(e.getId());
    }
    catch (UnknownProductIdException j){
      throw new UnknownProductKeyException(j.getId());
    }
    catch (NotAvaliableProductException k){
      throw new UnavailableProductException(k.getKey(),k.getRequested(),k.getAvaliable());
    }

  }

}
