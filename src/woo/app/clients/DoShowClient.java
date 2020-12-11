package woo.app.clients;

import woo.core.exception.UnknownClientIdException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;
import woo.app.exception.UnknownClientKeyException;
/**
 * Show client.
 */
public class DoShowClient extends Command<StoreManager> {

  private Input<String> _clientKey;

  public DoShowClient(StoreManager storefront) {
    super(Label.SHOW_CLIENT, storefront);
    _clientKey = _form.addStringInput(Message.requestClientKey());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();

    try {
      String cl = _receiver.getClient(_clientKey.value());
      _display.addLine(cl);
      for(String notification: _receiver.getClientNotifications(_clientKey.value())){
        _display.addLine(notification);
      }
      _display.display();

    } catch(UnknownClientIdException e){
        throw new UnknownClientKeyException(_clientKey.value());
    }
  }

}
