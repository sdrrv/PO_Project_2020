package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;
//FIXME import other classes

/**
 * Register new client.
 */
public class DoRegisterClient extends Command<StoreManager> {

  //FIXME add input fields
  private Input<String> _clientKey;
  private Input<String> _nome;
  private Input<String> _endereco;

  public DoRegisterClient(StoreManager storefront) {
    super(Label.REGISTER_CLIENT, storefront);
    _clientKey = _form.addStringInput(Message.requestClientKey());
    _nome = _form.addStringInput(Message.requestClientName());
    _endereco = _form.addStringInput(Message.requestClientAddress());
  }

  @Override
  public void execute() throws DialogException { //
    try{
      DoRegisterClient(storefront);
    }
  }

}
