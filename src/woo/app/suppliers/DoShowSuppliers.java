package woo.app.suppliers;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;
import woo.core.users.Supplier;

import java.util.Collection;
import java.util.List;

/**
 * Show all suppliers.
 */
public class DoShowSuppliers extends Command<StoreManager> {


  public DoShowSuppliers(StoreManager receiver) {
    super(Label.SHOW_ALL_SUPPLIERS, receiver);
  }
  public String showMessageYesNo(Boolean b){
    return b? Message.yes() : Message.no();
  }

  @Override
  public void execute() throws DialogException {
    List<Supplier> temp = _receiver.getAllSuppliers();
    for (Supplier supplier: temp){
      _display.addLine(supplier.toString()+showMessageYesNo(supplier.isActive()));
    }
    _display.display();
  }
  }
