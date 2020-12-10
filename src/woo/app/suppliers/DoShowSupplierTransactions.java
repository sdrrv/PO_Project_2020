package woo.app.suppliers;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.UnknownSupplierKeyException;
import woo.core.StoreManager;
import woo.core.exception.UnknownSupplierIdException;
import woo.core.transactions.Order;

/**
 * Show all transactions for specific supplier.
 */
public class DoShowSupplierTransactions extends Command<StoreManager> {

  Input<String> _supplierId;

  public DoShowSupplierTransactions(StoreManager receiver) {
    super(Label.SHOW_SUPPLIER_TRANSACTIONS, receiver);
    _supplierId=_form.addStringInput(Message.requestSupplierKey());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();
    try {
      for(Order order : _receiver.getSupplierOrders(_supplierId.value())){
        _display.addLine(order.toString());
      }
    }
    catch (UnknownSupplierIdException e){
      throw new UnknownSupplierKeyException(e.getId());
    }
  }
}
