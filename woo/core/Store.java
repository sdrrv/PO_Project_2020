package woo.core;

//FIXME import classes (cannot import from pt.tecnico or woo.app)
import java.io.Serializable;

import java.io.IOException;

import woo.core.exception.BadEntryException;
import core.users.*;

import java.util.Map;
import java.util.HashMap;

import java.util.List;
import java.util.LinkedList;
/**
 * Class Store implements a store.
 */
public class Store implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009192006L;

<<<<<<< HEAD
  private Map<String,Client> _clients;
  private int _date;

  public Store(){
    _date=0;
    _clients = new HashMap<String, Client>();
  }

  public int getDate(){
    return _date;
  }
  public void increaseDate(int amount){
    _date += amount;
  }

  public Client getClient(String id){
    return _clients.get(id);
  }

  public LinkedList<Client> getAllClients(){
    List<Client> result = new LinkedList<Client>();
    result = (LinkedList<Client>) _clients.values();
    return result;
  }

=======
  private Map<Client>

  // FIXME define contructor(s)
  // FIXME define methods
>>>>>>> 548a09e401074d34597530ebc1965db737c003ac

  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */
  void importFile(String txtfile) throws IOException, BadEntryException /* FIXME maybe other exceptions */ {
    //FIXME implement method
  }
}
