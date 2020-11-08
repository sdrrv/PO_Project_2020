package woo.core;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Collection;

import woo.core.exception.*;

/**
 * StoreManager: façade for the core classes.
 */
public class StoreManager {

  /** Current filename. */
  private String _filename = "";

  /** The actual store. */
  private final Store _store = new Store();

  public void registerClient(String name, String address, String id) throws DuplicateClientIdException {
    if(_store.hasClient(id)){ throw new DuplicateClientIdException(id); }
    _store.registerClient(_store.createClient(name, address, id));
  }
  public String getClient(String id) throws UnknownClientIdException {
    if(!_store.hasClient(id)){
      throw new UnknownClientIdException(id);
    }
    return _store.getClient(id).toString();
  }

  public Collection<String> getAllClients(){
    return null;
  }

  public int showDate(){
    return _store.getDate();
  }

  public void increaseDate(){
    _store.increaseDate(1);
  }

  /**
   * @throws IOException
   * @throws FileNotFoundException
   * @throws MissingFileAssociationException
   */
  public void save() throws IOException, FileNotFoundException, MissingFileAssociationException {
    //FIXME implement serialization method
  }

  /**
   * @param filename
   * @throws MissingFileAssociationException
   * @throws IOException
   * @throws FileNotFoundException
   */
  public void saveAs(String filename) throws MissingFileAssociationException, FileNotFoundException, IOException {
    _filename = filename;
    save();
  }

  /**
   * @param filename
   * @throws UnavailableFileException
   */
  public void load(String filename) throws UnavailableFileException {
    //FIXME implement serialization method
  }

  /**
   * @param textfile
   * @throws ImportFileException
   */
  public void importFile(String textfile) throws ImportFileException {
    try {
      _store.importFile(textfile);
    } catch (IOException | BadEntryException /* FIXME maybe other exceptions */ e) {
      throw new ImportFileException(textfile);
    }
  }

}
