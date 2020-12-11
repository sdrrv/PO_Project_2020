package woo.app.suppliers;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.DuplicateProductKeyException;
import woo.app.exception.DuplicateSupplierKeyException;
import woo.core.StoreManager;
import woo.core.exception.DuplicateSupplierIdException;

/**
 * Register supplier.
 */
public class DoRegisterSupplier extends Command<StoreManager> {

  private Input<String> _suppplierKey;
  private Input<String> _name;
  private Input<String> _address;

  public DoRegisterSupplier(StoreManager receiver) {
    super(Label.REGISTER_SUPPLIER, receiver);
    _suppplierKey= _form.addStringInput(Message.requestSupplierKey());
    _name= _form.addStringInput(Message.requestSupplierName());
    _address= _form.addStringInput(Message.requestSupplierAddress());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();
    try{
      _receiver.registerSupplier(_name.value(),_address.value(),_suppplierKey.value());
    }
    catch (DuplicateSupplierIdException e){
      throw new DuplicateSupplierKeyException(e.getKey());
    }
  }
}
