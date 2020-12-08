package woo.app.transactions;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;
//FIXME import other classes

/**
 * Register order.
 */
public class DoRegisterOrderTransaction extends Command<StoreManager> {

  private Input<String> _supplierId,_productId;
  private Input<Integer> _quantity;
  private Input<Boolean> _more;

  public DoRegisterOrderTransaction(StoreManager receiver) {
    super(Label.REGISTER_ORDER_TRANSACTION, receiver);
    _supplierId = _form.addStringInput(Message.requestClientKey());

    while(_more.value() == true){ //not correct
      _productId = _form.addStringInput(Message.requestProductKey());
      _quantity = _form.addIntegerInput(Message.requestAmount());
      _more = _form.addBooleanInput(Message.requestMore());
    }
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try{
      _receiver.registerOrder(_supplierId.value(),_productId.value(), _quantity.value());
    }
    catch (UnknownSupplierIdException e){
      throw new UnknownSupplierKeyException(e.getId());
    }

  }

}