package woo.core;

//FIXME import classes (cannot import from pt.tecnico or woo.app)
import java.io.Serializable;

import java.io.IOException;

import woo.core.exception.BadEntryException;
import core.users.*;

import java.util.Map;
import java.util.HashMap;

/**
 * Class Store implements a store.
 */
public class Store implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009192006L;

  private Map<String,Client> _clients;
  private int _date;

  public Store(){
    _date=0;
    _clients = new HashMap<String, Client>();
  }

  // FIXME define methods


  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */
  void importFile(String txtfile) throws IOException, BadEntryException /* FIXME maybe other exceptions */ {
    //FIXME implement method
  }

}
