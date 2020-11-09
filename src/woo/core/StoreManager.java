package woo.core;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Collection;

import woo.core.exception.*;
import woo.core.products.Product;
import woo.core.products.ServiceLevel;
import woo.core.products.ServiceType;
import woo.core.users.Client;
import woo.core.users.Supplier;

import java.util.Set;
import java.util.SortedSet;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
/**
 * StoreManager: façade for the core classes.
 */
public class StoreManager implements Serializable {

  /** Current filename. */
  private String _filename = "";

  /** The actual store. */
  private Store _store = new Store();
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
  public List<String> getAllClients(){
    SortedSet<Client> temp = _store.getAllClients();
    List<String> result = new ArrayList<>();
    for(Client client: temp){
      result.add(client.toString());
    }
    return result;
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

  public List<Supplier> getAllSuppliers(){
    return new ArrayList<Supplier>(_store.getAllSuppliers());
  }

  public boolean toggleSupplierActivation(String id)throws UnknownSupplierIdException{
    if(!_store.hasSupplier(id)){
      throw new UnknownSupplierIdException(id);
    }
      return _store.toggleSupplierActivation(id);
  }
  //---------------------------------------------------------------------------------------------------------------------
  public int showDate(){
    return _store.getDate();
  }

  public void increaseDate(int amount){
    _store.increaseDate(amount);
  }
//---------------------------------------------------------------------------------------------------------------------
public List<String> getAllProducts(){
  SortedSet<Product> temp = _store.getAllProducts();
  List<String> result = new ArrayList<>();
  for(Product product: temp){
    result.add(product.toString());
  }
  return result;
}
public void registerBox(int price,int valorCrit,String key, String serviceType) throws WrongServiceTypeException{
    try{
      ServiceType serv = ServiceType.valueOf(serviceType);
      _store.registerBox(_store.createBox(price,valorCrit,key,serv));
    }
    catch (IllegalArgumentException e){
      throw new WrongServiceTypeException(serviceType);
    }
}
public void registerContainer(int price, int valorCrit, String key, String serviceType, String serviceLevel)throws WrongServiceTypeException,WrongServiceLevelException{
  ServiceType serv;
  try{
     serv = ServiceType.valueOf(serviceType);
  }
  catch (IllegalArgumentException e){
    throw new WrongServiceTypeException(serviceType);
  }
  try{
    ServiceLevel level = ServiceLevel.valueOf(serviceLevel);
    _store.registerContainer(_store.createContainer(price,valorCrit,key,serv,level));
  }
  catch (IllegalArgumentException i){
    throw new WrongServiceLevelException(serviceLevel);
  }
}

public void resgisterBook(int price,int valorCrit, String key, String title, String author, String isbn){

}
  //---------------------------------------------------------------------------------------------------------------------
  /**
   * @throws IOException
   * @throws FileNotFoundException
   * @throws MissingFileAssociationException
   */
  public void save() throws IOException, FileNotFoundException, MissingFileAssociationException {
    if (_filename.equals(""))
      throw new MissingFileAssociationException();
    try (ObjectOutputStream obOut= new ObjectOutputStream(new FileOutputStream(_filename))){
      obOut.writeObject(_store);
    }
  }

  /**
   * @param filename
   * @throws MissingFileAssociationException
   * @throws IOException
   * @throws FileNotFoundException
   */
  public void saveAs(String fileName) throws MissingFileAssociationException, FileNotFoundException, IOException {
    _filename = fileName;
    save();
  }

  /**
   * @param filename
   * @throws UnavailableFileException
   */
  public void load(String fileName) throws UnavailableFileException,IOException,ClassNotFoundException {
      ObjectInputStream objIn= null;
      try {
        objIn= new ObjectInputStream(new FileInputStream(fileName));
        _store= (Store) objIn.readObject();
      }
      catch (IOException|ClassNotFoundException e){
        throw new UnavailableFileException(fileName);
      }
      finally {
        if (objIn!= null)
          objIn.close();
      }
    }

  /**
   * @param textfile
   * @throws ImportFileException
   */
  public void importFile(String textFile) throws ImportFileException {
    try {
      _store.importFile(textFile);
    } catch (IOException | BadEntryException /* FIXME maybe other exceptions */ e) {
      throw new ImportFileException(textFile);
    }
  }

}
