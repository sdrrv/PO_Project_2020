package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;
import woo.app.exception.DuplicateClientKeyException;
import woo.core.exception.DuplicateClientIdException;

/**
 * Register new client.
 */
public class DoRegisterClient extends Command<StoreManager> {

  private Input<String> _clientKey;
  private Input<String> _name;
  private Input<String> _address;

  public DoRegisterClient(StoreManager storefront) {
    super(Label.REGISTER_CLIENT, storefront);
    _clientKey = _form.addStringInput(Message.requestClientKey());
    _name = _form.addStringInput(Message.requestClientName());
    _address = _form.addStringInput(Message.requestClientAddress());
  }
  
  @Override
  public void execute() throws DialogException { //
    _form.parse();
    try{
      _receiver.registerClient(_name.value(),_address.value(),_clientKey.value());
    }catch(DuplicateClientIdException e){
      throw new DuplicateClientKeyException(_clientKey.value());
    }
  }
}
