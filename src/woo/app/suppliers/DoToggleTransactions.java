package woo.app.suppliers;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.UnknownSupplierKeyException;
import woo.core.StoreManager;
import woo.core.exception.UnknownSupplierIdException;

public class DoToggleTransactions extends Command<StoreManager> {

  private Input<String> _supplierKey;
  public DoToggleTransactions(StoreManager receiver) {
    super(Label.TOGGLE_TRANSACTIONS, receiver);
    _supplierKey = _form.addStringInput(Message.requestSupplierKey());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();
    try {
      if (_receiver.toggleSupplierActivation(_supplierKey.value())) {
        _display.popup(Message.transactionsOn(_supplierKey.value()));
      } else {
        _display.popup(Message.transactionsOff(_supplierKey.value()));
      }
    }
    catch (UnknownSupplierIdException e){
      throw new UnknownSupplierKeyException(e.getId());
    }
  }

}
