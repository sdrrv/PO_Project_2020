package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import woo.core.StoreManager;
import java.util.Collection;
//FIXME import other classes

/**
 * Show all clients.
 */
public class DoShowAllClients extends Command<StoreManager> {

  public DoShowAllClients(StoreManager storefront) {
    super(Label.SHOW_ALL_CLIENTS, storefront);
  }

  @Override
  public void execute() throws DialogException {
    /*Collection<String> clients = _receiver.getAllClients();
    for(String cl : clients) {_display.addLine(cl);}

    _display.display();*/
  }
}
