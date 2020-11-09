package woo.core;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Collection;

import woo.core.exception.*;
import woo.core.users.Client;

import java.util.Set;
import java.util.SortedSet;

import java.util.List;
import java.util.ArrayList;
/**
 * StoreManager: façade for the core classes.
 */
public class StoreManager {

  /** Current filename. */
  private String _filename = "";

  /** The actual store. */
  private final Store _store = new Store();
//---------------------------------------------------------------------------------------------------------------------
  public void registerClient(String name, String address, String id) throws DuplicateClientIdException {
    if(_store.hasClient(id)){ throw new DuplicateClientIdException(id); }
    _store.registerClient(_store.createClient(name, address, id));
    //.
  }
  public String getClient(String id) throws UnknownClientIdException {
    if(!_store.hasClient(id)){
      throw new UnknownClientIdException(id);
    }
    return _store.getClient(id).toString();
  }
//---------------------------------------------------------------------------------------------------------------------
public void registerSupplier(String name, String address, String id) throws DuplicateSupplierIdException{
    if(_store.hasSupplier(id)){
      throw new DuplicateSupplierIdException(id);
    }
    _store.registerSupplier(_store.createSupplier(name, address, id));
    //.
}

public String getSupplier(String id) throws UnknownSupplierIdException{
    if(!_store.hasSupplier(id)){
      throw new UnknownSupplierIdException(id);
    }
    return _store.getSupplier(id).toString();
}

//---------------------------------------------------------------------------------------------------------------------

  public List<String> getAllClients(){
    SortedSet<Client> temp = _store.getAllClients();
    List<String> result = new ArrayList<String>();
    for(Client client: temp){
      result.add(client.toString());
    }
    return result;
  }

  public List<String> getAllProducts(){
    return null;
  }

  public List<String> getAllSuppliers(){
    return null;
  }

  public int showDate(){
    return _store.getDate();
  }

  public void increaseDate(int amount){
    _store.increaseDate(amount);
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
