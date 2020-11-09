package woo.app.suppliers;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;
import java.util.Collection;
import java.util.List;

/**
 * Show all suppliers.
 */
public class DoShowSuppliers extends Command<StoreManager> {


  public DoShowSuppliers(StoreManager receiver) {
    super(Label.SHOW_ALL_SUPPLIERS, receiver);
  }

  @Override
  public void execute() throws DialogException {
    List<String> suppliers = _receiver.getAllSuppliers();
    List<Boolean> supplierStates= _receiver.getAllSupliersStates();
    for(int i=0; i<suppliers.size();i++ ){
        if(supplierStates.get(i))
          _display.addLine(suppliers.get(i)+Message.yes());

        else
          _display.addLine(suppliers.get(i)+Message.no());
    }

    _display.display();
  }
  }
