package woo.app.main;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.FileOpenFailedException;
import woo.core.StoreManager;
import woo.core.exception.UnavailableFileException;

import java.io.IOException;
//FIXME import other classes

/**
 * Open existing saved state.
 */
public class DoOpen extends Command<StoreManager> {
  private Input<String> fileName;

  /** @param receiver */
  public DoOpen(StoreManager receiver) {
    super(Label.OPEN, receiver);
    fileName = _form.addStringInput(Message.openFile());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException, FileOpenFailedException {
  _form.parse();
    try {
      _receiver.load(fileName.value());
    } catch (UnavailableFileException| IOException | ClassNotFoundException ufe) {
        throw new FileOpenFailedException(fileName.value());
    }

  }

}
