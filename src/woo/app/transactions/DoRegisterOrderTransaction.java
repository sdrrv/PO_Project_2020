package woo.app.transactions;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.UnauthorizedSupplierException;
import woo.app.exception.UnknownProductKeyException;
import woo.app.exception.UnknownSupplierKeyException;
import woo.app.exception.WrongSupplierException;
import woo.core.StoreManager;
import woo.core.exception.NotSoldBySupplierException;
import woo.core.exception.UnableSupplierException;
import woo.core.exception.UnknownProductIdException;
import woo.core.exception.UnknownSupplierIdException;

import java.util.LinkedList;
import java.util.List;
//FIXME import other classes

/**
 * Register order.
 */
public class DoRegisterOrderTransaction extends Command<StoreManager> {

  private Input<String> _supplierId,_productId;
  private Input<Integer> _quantity;
  private Input<Boolean> _more;
  private List<String> _products;
  private List<Integer> _amounts;

  public DoRegisterOrderTransaction(StoreManager receiver) {
    super(Label.REGISTER_ORDER_TRANSACTION, receiver);
    _products = new LinkedList<>();
    _amounts = new LinkedList<>();
    }

  @Override
  public final void execute() throws DialogException {
    _form.clear();
    _supplierId = _form.addStringInput(Message.requestSupplierKey());
    _form.parse();
    _form.clear();
    //---------------------------------------------------------------------------
    _productId = _form.addStringInput(Message.requestProductKey());
    _quantity = _form.addIntegerInput(Message.requestAmount());
    _more = _form.addBooleanInput(Message.requestMore());
    //--------------------------------------------------------------------------
    _form.parse();
    _products.add(_productId.value());
    _amounts.add(_quantity.value());
    //---------------------------------------------------------------------------

    while(_more.value()){
      _form.parse();
      _products.add(_productId.value());
      _amounts.add(_quantity.value());
    }
    try{
      _receiver.registerOrder(_supplierId.value(),_products,_amounts);
    }
    catch (UnableSupplierException e){
      throw new UnauthorizedSupplierException(e.getId());
    }
    catch (NotSoldBySupplierException j){
      throw new WrongSupplierException(j.getSupplierId(),j.getProductId());
    }
    catch (UnknownSupplierIdException k){
      throw new UnknownSupplierKeyException(k.getId());
    }
    catch (UnknownProductIdException l){
      throw new UnknownProductKeyException(l.getId());
    }

  }

}