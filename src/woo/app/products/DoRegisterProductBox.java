package woo.app.products;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.DuplicateProductKeyException;
import woo.app.exception.UnknownServiceTypeException;
import woo.app.exception.UnknownSupplierKeyException;
import woo.core.StoreManager;
import woo.core.exception.DuplicateProductIdException;
import woo.core.exception.UnknownSupplierIdException;
import woo.core.exception.WrongServiceTypeException;
//FIXME import other classes

/**
 * Register box.
 */
public class DoRegisterProductBox extends Command<StoreManager> {
  private Input<String> _key;
  private Input<Integer> _price;
  private Input<Integer> _valCrit;
  private Input<String> _supplierKey;
  private Input<String> _serviceType;

  public DoRegisterProductBox(StoreManager receiver) {
    super(Label.REGISTER_BOX, receiver);
    _key= _form.addStringInput(Message.requestProductKey());
    _price = _form.addIntegerInput(Message.requestPrice());
    _valCrit = _form.addIntegerInput(Message.requestStockCriticalValue());
    _supplierKey= _form.addStringInput(Message.requestSupplierKey());
    _serviceType = _form.addStringInput(Message.requestServiceType());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try{
      _receiver.registerBox(_price.value(),_valCrit.value(),_key.value(),_serviceType.value(),_supplierKey.value());
    }
    catch (WrongServiceTypeException e1){
      throw new UnknownServiceTypeException(e1.getServiceType());
    }
    catch (UnknownSupplierIdException e2){
      throw new UnknownSupplierKeyException(e2.getId());
    }
    catch (DuplicateProductIdException e3){
      throw new DuplicateProductKeyException(e3.getKey());
    }

  }
}
