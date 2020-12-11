package woo.app.transactions;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.UnknownTransactionKeyException;
import woo.core.StoreManager;
import woo.core.exception.UnknownTransactionIdException;
//FIXME import other classes

/**
 * Pay transaction (sale).
 */
public class DoPay extends Command<StoreManager> {

  private Input<Integer> _transactionId;

  public DoPay(StoreManager storefront) {
    super(Label.PAY, storefront);
    _transactionId = _form.addIntegerInput(Message.requestTransactionKey());
  }

  @Override
  public final void execute() throws DialogException {
    try {
      _receiver.pay(_transactionId.value());
    }
    catch (UnknownTransactionIdException e){
      throw new UnknownTransactionKeyException(e.getKey());
    }
  }
}
