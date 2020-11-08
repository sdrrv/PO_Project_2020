package woo.app.suppliers;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;
import java.util.Collection;

/**
 * Show all suppliers.
 */
public class DoShowSuppliers extends Command<StoreManager> {

  //FIXME add input fields

  public DoShowSuppliers(StoreManager receiver) {
    super(Label.SHOW_ALL_SUPPLIERS, receiver);
    //FIXME init input fields
  }

  @Override
  public void execute() throws DialogException {
    Collection<String> suppliers = _receiver.getAllSuppliers();
    for(String sp : suppliers)
      _display.addLine(sp);

    _display.display();
  }
  }
