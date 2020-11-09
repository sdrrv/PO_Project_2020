package woo.app.main;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;
//FIXME import other classes

/**
 * Save current state to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<StoreManager> {
  private Input<String> _fileName;

  /** @param receiver */
  public DoSave(StoreManager receiver) {
    super(Label.SAVE, receiver);
    _fileName = _form.addStringInput(Message.newSaveAs());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    _form.parse();
    try{
      _receiver.saveAs(_fileName.value());
    }
    catch (Exception e){
      System.out.println("Save Error");
    }
  }
}
