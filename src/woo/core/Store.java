package woo.core;

//FIXME import classes (cannot import from pt.tecnico or woo.app)
import java.beans.Transient;
import java.io.Serializable;

import java.io.IOException;

import woo.core.exception.BadEntryException;

import woo.core.exception.ImportFileException;
import woo.core.transactions.Order;
import woo.core.transactions.ProductPlus;
import woo.core.transactions.Sale;
import woo.core.transactions.Transaction;
import woo.core.notifications.Notification;
import woo.core.users.*;
import woo.core.products.*;

import java.util.Map;
import java.util.TreeSet;


import java.util.*;

/**
 * Class Store implements a store.
 */
public class Store implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009192006L;

  private int _balance;

  private Map<String,Client> _clients;
  private int _date;
  private Map<String,Product> _products;
  private Map<String,Supplier> _suppliers;
  private StoreManager _storeManager;
  private Map<Integer,Sale> _sales;
  private Map<Integer,Order> _orders;
  private Map<Integer,Notification> _notifications;
  

  private int _numberOfTransactions;
  private int _numberOfNotifications;

  public void setStoreManager(StoreManager storeManager){
    _storeManager = storeManager;
  }
  public Store(){
    _balance = 0;
    _numberOfTransactions = -1;
    _date=0;
    _clients = new HashMap<>();
    _suppliers = new HashMap<>();
    _products = new HashMap<>();
    _sales= new HashMap<>();
    _orders = new HashMap<>();

  }
  //-----------------------------------------------------------------------------------
  public int getDate(){
    return _date;
  }
  public void increaseDate(int amount){
    _date += amount;
  }
  //-----------------------------------------------------------------------------------
  public boolean hasClient(String id){
    return _clients.containsKey(id);
  }
  public Client createClient(String name, String address, String id){
    return new Client(name,address,id);
  }
  public void registerClient(Client client){
    _clients.put(client.getId(),client);
  }
  public Client getClient(String id){
    return _clients.get(id);
  }

  public TreeSet<Client> getAllClients(){
    return new TreeSet<Client>(_clients.values());
  }
  public TreeSet<Product>getAllProducts(){
    return new TreeSet<Product>(_products.values());
  }
  public TreeSet<Supplier> getAllSuppliers(){
    return new TreeSet<Supplier>(_suppliers.values());
  }
  //-----------------------------------------------------------------------------------
  public boolean hasSupplier(String id){
    return _suppliers.containsKey(id);
  }
  public Supplier createSupplier(String name, String address, String id){
    return new Supplier(name,address,id);
  }
  public void registerSupplier(Supplier supplier){
    _suppliers.put(supplier.getId(),supplier);
  }
  public Supplier getSupplier(String id){
    return _suppliers.get(id);
  }
  public boolean toggleSupplierActivation(String id){
    return getSupplier(id).toogleActivation();
  }
  //-----------------------------------------------------------------------------------
  public Product getProduct(String id){return _products.get(id);}

  public Box createBox(int price,int valorCrit,String key,ServiceType serviceType, Supplier supplier){
    return new Box(price, valorCrit, key, serviceType,supplier);
  }
  public void registerBox(Box box){
    _products.put(box.getKey(),box);
    box.getSupplier().addToProducts(box);
  }

  public Container createContainer(int price,int valorCrit,String key, ServiceType serviceType,ServiceLevel serviceLevel, Supplier supplier){
    return new Container(price, valorCrit, key, serviceType, serviceLevel,supplier);
  }
  public void registerContainer(Container container){
    _products.put(container.getKey(),container);
    container.getSupplier().addToProducts(container);
  }

  public Book createBook(int price,int valorCrit, String key, String title, String author, String isbn, Supplier supplier){
    return new Book(price, valorCrit, key, title, author, isbn, supplier);
  }
  public void registerBook(Book book){
    _products.put(book.getKey(),book);
    book.getSupplier().addToProducts(book);
  }

  public boolean hasProduct(String id){
    return _products.containsKey(id);
  }

  public ProductPlus createProductPlus(Product product, int amount){
    return new ProductPlus(amount,product);
  }

  public void setProductPrice(String id, int price){ // sets the price
    _products.get(id).setPrice(price);
  }
  //-----------------------------------------------------------------------------------
  public boolean hasSale(int key){
    return _sales.containsKey(key);
  }
  public boolean hasOrder(int key){
    return _orders.containsKey(key);
  }

  public Transaction getTransaction(int key){
    if(hasSale(key)){
      return _sales.get(key);
    }
    return _orders.get(key);
  }

  public Sale getSale(int key){
    return _sales.get(key);
  }

  public boolean hasTransaction(int key){
    return (hasSale(key)||hasOrder(key));
  }


  public void registerSale(Sale sale){
    _sales.put(_numberOfTransactions,sale);
    sale.getClient().addSale(sale); // adds the sale to the list in the respective client
  }

  public Sale createSale(Client client,int dateLim,Product product,int amount){
    Sale result = new Sale(++_numberOfTransactions,dateLim,product.getPrice(),client,product,amount);
    product.decreaseValue(amount);
    return result;
  }

  public void registerOrder(Order order){
    _orders.put(_numberOfTransactions,order);
    _balance -= order.getPrice();
  }

  public Order createOrder(Supplier supplier, List<ProductPlus> products){
    Order order = new Order(++_numberOfTransactions,supplier);
    for (ProductPlus i: products){
      order.addProduct(i);
      i.getProduct().increaseValue(i.getAmount());
    }
    return order;
  }

  public void pay(Sale sale){
    sale.Pay();
  }
  //-----------------------------------------------------------------------------------
  public void registerSale(Sale sale){
    _sales.put(_numberOfTransactions,sale);
    sale.getClient().addSale(sale); // adds the sale to the list in the respective client
  }

  public Sale createSale(Client client,int dateLim,Product product,int amount){
    Sale result = new Sale(++_numberOfTransactions,dateLim,product.getPrice(),client,product,amount);
    product.decreaseValue(amount);
    return result;
  }

  public void registerNotification(Notification notification){
    _notifications.put(_numberOfNotifications,notification);
    // add notification to client
  }
  public Notification createNotification(Client client, Product product){
    Notification notification = new Notification(++_numberOfNotifications,___,___);
    _notifications.put(++_numberOfNotifications,notification);
    return notification;
  }

  //-----------------------------------------------------------------------------------
  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */
  void importFile(String txtfile) throws IOException, BadEntryException {
    Parser parser = new Parser(_storeManager);
    parser.parseFile(txtfile);
  }
}
