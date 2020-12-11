package woo.core;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

import woo.core.exception.*;
import woo.core.notifications.Notification;
import woo.core.products.*;
import woo.core.transactions.Order;
import woo.core.transactions.ProductPlus;
import woo.core.transactions.Sale;
import woo.core.users.Client;
import woo.core.users.Supplier;

import java.io.*;

/**
 * StoreManager: façade for the core classes.
 */
public class StoreManager implements Serializable {

  /** Current filename. */
  private String _filename = "";

  /** The actual store. */
  private Store _store = new Store();

  public StoreManager(){
    _store.setStoreManager(this);
  }

//---------------------------------------------------------------------------------------------------------------------
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
  public List<String> getAllClients(){
    SortedSet<Client> temp = _store.getAllClients();
    List<String> result = new ArrayList<>();
    for(Client client: temp){
      result.add(client.toString());
    }
    return result;
  }

  public List<String> getClientNotifications(String clientId) throws UnknownClientIdException{
    if(!_store.hasClient(clientId)){
      throw new UnknownClientIdException(clientId);
    }
    List<String> result = new LinkedList<>();
    for (Notification notification : _store.getClient(clientId).getNotifications()){
      if(notification.getDeliMethod().equals("")){
        result.add(notification.toString());
      }
    }
    return result;
  }

  public List<Sale> showPaiedSales(String clientId) throws UnknownClientIdException{
    if(!_store.hasClient(clientId)){
      throw new UnknownClientIdException(clientId);
    }
    return _store.getClient(clientId).getPaiedSales();
  }

  public List<Sale> getClientSales(String clientId) throws UnknownClientIdException{
    if(!_store.hasClient(clientId)){
      throw new UnknownClientIdException(clientId);
    }
    return _store.getClient(clientId).getSales();
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

  public Supplier getHasSupplier(String id) throws UnknownSupplierIdException{
    if(!_store.hasSupplier(id)){
      throw new UnknownSupplierIdException(id);
    }
    return _store.getSupplier(id);
  }

  public Collection<Order> getSupplierOrders(String supplierId) throws UnknownSupplierIdException{
    if(!_store.hasSupplier(supplierId)){
      throw new UnknownSupplierIdException(supplierId);
    }
    return _store.getSupplierOrders(supplierId);
  }

  //---------------------------------------------------------------------------------------------------------------------
  public int showDate(){
    return _store.getDate();
  }

  public void increaseDate(int amount){
    _store.increaseDate(amount);
  }
//----------------------------------------------------------------------------------------------------------------------



public List<String> getAllProducts(){
  SortedSet<Product> temp = _store.getAllProducts();
  List<String> result = new ArrayList<>();
  for(Product product: temp){
    result.add(product.toString());
  }
  return result;
}
public Box registerBox(int price, int valorCrit, String key, String serviceType, String supplierKey) throws WrongServiceTypeException,UnknownSupplierIdException,DuplicateProductIdException{
    Supplier sup = getHasSupplier(supplierKey);
    Box box;
    if(_store.hasProduct(key)){
      throw new DuplicateProductIdException(key);
    }
    try{
      ServiceType serv = ServiceType.valueOf(serviceType);
      box =_store.createBox(price,valorCrit,key,serv,sup);
      _store.registerBox(box);
    }
    catch (IllegalArgumentException e){
      throw new WrongServiceTypeException(serviceType);
    }
    return box;
}
public Container registerContainer(int price, int valorCrit, String key, String serviceType, String serviceLevel, String supplierKey)throws WrongServiceTypeException,WrongServiceLevelException,UnknownSupplierIdException,DuplicateProductIdException{
  ServiceType serv;
  Supplier sup = getHasSupplier(supplierKey);
  Container container;
  if(_store.hasProduct(key)){
    throw new DuplicateProductIdException(key);
  }
  try{
     serv = ServiceType.valueOf(serviceType);
  }
  catch (IllegalArgumentException e){
    throw new WrongServiceTypeException(serviceType);
  }
  try{
    ServiceLevel level = ServiceLevel.valueOf(serviceLevel);
    container = _store.createContainer(price,valorCrit,key,serv,level,sup);
    _store.registerContainer(container);
  }
  catch (IllegalArgumentException i){
    throw new WrongServiceLevelException(serviceLevel);
  }
  return container;
}

public Book registerBook(int price,int valorCrit, String key, String title, String author, String isbn, String supplierKey) throws UnknownSupplierIdException,DuplicateProductIdException{
  if(_store.hasProduct(key)){
    throw new DuplicateProductIdException(key);
  }
  Supplier sup = getHasSupplier(supplierKey);
  Book book = _store.createBook(price,valorCrit,key,title,author,isbn,sup);
  _store.registerBook(book);
  return book;
}

public void changeProductPrice(String productId,int newPrice) throws UnknownProductIdException{
  if(!_store.hasProduct(productId))
    throw new UnknownProductIdException(productId);
  _store.getProduct(productId).setPrice(newPrice);
}
  //---------------------------------------------------------------------------------------------------------------------
  public String showTransaction(int key) throws UnknownTransactionIdException{
    if(!_store.hasTransaction(key)){
      throw new UnknownTransactionIdException(key);
    }
    return _store.getTransaction(key).toString(_store.getDate());
  }

  public void registerSale(String clientId,int dateLim,String productId, int quantity) throws UnknownClientIdException,UnknownProductIdException, NotAvaliableProductException{
    if(!_store.hasClient(clientId))
      throw new UnknownClientIdException(clientId);
    if(!_store.hasProduct(productId))
      throw new UnknownProductIdException(productId);
    Product product = _store.getProduct(productId);
    if((product.getValExist()-quantity < 0)){
      throw new NotAvaliableProductException(productId,quantity,product.getValExist());
    }
    _store.registerSale(_store.createSale(_store.getClient(clientId),dateLim,_store.getProduct(productId),quantity));
  }

  public void registerOrder(String supplierId, List<String> products, List<Integer> amounts) throws UnableSupplierException,NotSoldBySupplierException,UnknownProductIdException,UnknownSupplierIdException{
    if(!_store.hasSupplier(supplierId))
      throw new UnknownSupplierIdException(supplierId);
    Supplier supplier = _store.getSupplier(supplierId);
    if(!supplier.isActive())
      throw new UnableSupplierException(supplierId);
    List<ProductPlus> result = new LinkedList<>();

    for(int i=0; i<products.size();i++){
      String productId = products.get(i);
      int amount = amounts.get(i);

      if(!_store.hasProduct(productId))
        throw new UnknownProductIdException(productId);
      if(!supplier.hasProduct(productId))
        throw new NotSoldBySupplierException(supplierId,productId);

      result.add(_store.createProductPlus(_store.getProduct(productId),amount));
    }

    _store.registerOrder(_store.createOrder(supplier,result));
  }

  public void pay(int saleKey) throws UnknownTransactionIdException{
    if(!_store.hasSale(saleKey))
      throw new UnknownTransactionIdException(saleKey);
    _store.pay(_store.getSale(saleKey));
  }

  public double getBalance(){
    return _store.getBalance();
  }
  public double getAccontingBalance(){
    return _store.getAccontingBalance();
  }
  //---------------------------------------------------------------------------------------------------------------------
  public void activateNotification(String clientId,String productId) throws UnknownClientIdException, UnknownProductIdException{
    if(!_store.hasClient(clientId))
      throw new UnknownClientIdException(clientId);
    if(!_store.hasProduct(productId))
      throw new UnknownProductIdException(productId);
    // _store.createNotification(_store.getClient(clientId),_store.getProduct(productId));

  }
  //---------------------------------------------------------------------------------------------------------------------
  public List<Product> getProductsBellowAmount(int amount){
    return _store.getProductsBellowAmount(amount);
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

   * @throws MissingFileAssociationException
   * @throws IOException
   * @throws FileNotFoundException
   */
  public void saveAs(String fileName) throws MissingFileAssociationException, FileNotFoundException, IOException {
    _filename = fileName;
    save();
  }

  /**

   * @throws UnavailableFileException
   */
  public void load(String fileName) throws UnavailableFileException,IOException,ClassNotFoundException {
      try (ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(fileName))){
        _store= (Store) objIn.readObject();
      }
      catch (IOException|ClassNotFoundException e){
        throw new UnavailableFileException(fileName);
      }
    }

  /**

   */
  public void importFile(String textFile) throws ImportFileException {
    try {
      _store.importFile(textFile);
    } catch (IOException | BadEntryException e) {
      throw new ImportFileException(textFile);
    }
  }

}
