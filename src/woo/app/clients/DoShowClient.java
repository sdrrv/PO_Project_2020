package woo.app.clients;

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
      _display.popup(cl.toString());
    } catch(UnknownClientKeyException e){
        throw new UnknownClientKeyException(_clientKey.value());
    }
  }

}
