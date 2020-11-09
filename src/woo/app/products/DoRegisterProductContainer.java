package woo.app.products;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.DuplicateProductKeyException;
import woo.app.exception.UnknownServiceLevelException;
import woo.app.exception.UnknownServiceTypeException;
import woo.app.exception.UnknownSupplierKeyException;
import woo.core.StoreManager;
import woo.core.exception.DuplicateProductIdException;
import woo.core.exception.UnknownSupplierIdException;
import woo.core.exception.WrongServiceLevelException;
import woo.core.exception.WrongServiceTypeException;
//FIXME import other classes

/**
 * Register container.
 */
public class DoRegisterProductContainer extends Command<StoreManager> {
  private Input<String> _key;
  private Input<Integer> _price;
  private Input<Integer> _valCrit;
  private Input<String> _supplierKey;
  private Input<String> _serviceType;
  private Input <String> _serviceLevel;

  public DoRegisterProductContainer(StoreManager receiver) {
    super(Label.REGISTER_CONTAINER, receiver);
    _key= _form.addStringInput(Message.requestProductKey());
    _price = _form.addIntegerInput(Message.requestPrice());
    _valCrit = _form.addIntegerInput(Message.requestStockCriticalValue());
    _supplierKey= _form.addStringInput(Message.requestSupplierKey());
    _serviceType = _form.addStringInput(Message.requestServiceType());
    _serviceLevel = _form.addStringInput(Message.requestServiceLevel());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try{
      _receiver.registerContainer(_price.value(),_valCrit.value(),_key.value(),_serviceType.value(),_serviceLevel.value(),_supplierKey.value());
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
    catch (WrongServiceLevelException e4){
      throw new UnknownServiceLevelException(e4.getServiceLevel());
    }
  }
}
