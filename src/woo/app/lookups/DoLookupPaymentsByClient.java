package woo.app.lookups;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.UnknownClientKeyException;
import woo.core.StoreManager;
import woo.core.exception.UnknownClientIdException;
import java.util.List;
import woo.core.transactions.Sale;
//FIXME import other classes

/**
 * Lookup payments by given client.
 */
public class DoLookupPaymentsByClient extends Command<StoreManager> {

  Input<String> _clientId;

  public DoLookupPaymentsByClient(StoreManager storefront) {
    super(Label.PAID_BY_CLIENT, storefront);
    _clientId = _form.addStringInput(Message.requestClientKey());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();
    try {
      List<Sale> paidSales = _receiver.showPaiedSales(_clientId.value());
      for (Sale sale : paidSales) {
        _display.addLine(sale.toString());
      }
      _display.display();
    } catch (UnknownClientIdException e) {
        throw new UnknownClientKeyException(_clientId.value());
    }

  }
}
