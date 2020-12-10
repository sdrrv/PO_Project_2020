package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.UnknownClientKeyException;
import woo.app.lookups.Message;
import woo.core.StoreManager;
import woo.core.exception.UnknownClientIdException;
import woo.core.transactions.Sale;

import java.util.List;
//FIXME import other classes

/**
 * Show all transactions for a specific client.
 */
public class DoShowClientTransactions extends Command<StoreManager> {

  Input<String> _clientId;

  public DoShowClientTransactions(StoreManager storefront) {
    super(Label.SHOW_CLIENT_TRANSACTIONS, storefront);
    _clientId = _form.addStringInput(Message.requestClientKey());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();
    try {
      List<Sale> clientSales = _receiver.getClientSales(_clientId.value());

      for (Sale sale : clientSales) {
        _display.addLine(sale.toString());
      }
      _display.display();
    } catch (UnknownClientIdException e) {
        throw new UnknownClientKeyException(_clientId.value());
    }
  }

}
