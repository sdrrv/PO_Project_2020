package woo.app.products;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.DuplicateProductKeyException;
import woo.app.exception.UnknownSupplierKeyException;
import woo.core.StoreManager;
import woo.core.exception.DuplicateProductIdException;
import woo.core.exception.UnknownSupplierIdException;
//FIXME import other classes

/**
 * Register book.
 */
public class DoRegisterProductBook extends Command<StoreManager> {

  private Input<String> _key;
  private Input<Integer> _price;
  private Input<Integer> _valCrit;
  private Input<String> _supplierKey;
  private Input<String> _title;
  private Input<String> _author;
  private Input<String> _isbn;

  public DoRegisterProductBook(StoreManager receiver) {
    super(Label.REGISTER_BOOK, receiver);
    _key= _form.addStringInput(Message.requestProductKey());
    _title = _form.addStringInput(Message.requestBookTitle());
    _author = _form.addStringInput(Message.requestBookAuthor());
    _isbn = _form.addStringInput(Message.requestISBN());
    _price = _form.addIntegerInput(Message.requestPrice());
    _valCrit = _form.addIntegerInput(Message.requestStockCriticalValue());
    _supplierKey= _form.addStringInput(Message.requestSupplierKey());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try {
      _receiver.registerBook(_price.value(), _valCrit.value(),_key.value(),_title.value(), _author.value(), _isbn.value(), _supplierKey.value());
    }
    catch (UnknownSupplierIdException e1){
      throw new UnknownSupplierKeyException(e1.getId());
    }
    catch (DuplicateProductIdException e2){
      throw new DuplicateProductKeyException(e2.getKey());
    }
  }
}
