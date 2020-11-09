package woo.core;

//FIXME import classes (cannot import from pt.tecnico or woo.app)
import java.io.Serializable;

import java.io.IOException;

import woo.core.exception.BadEntryException;

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

  private Map<String,Client> _clients;
  private int _date;
  private Map<String,Product> _products;
  private Map<String,Supplier> _suppliers;

  public Store(){
    _date=0;
    _clients = new HashMap<String,Client>();
    _suppliers = new HashMap<String,Supplier>();
    _products = new HashMap<String,Product>();
  }

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
  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */
  void importFile(String txtfile) throws IOException, BadEntryException /* FIXME maybe other exceptions */ {
    //FIXME implement metho
  }
}
