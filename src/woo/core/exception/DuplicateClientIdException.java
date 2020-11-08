package woo.core.exception;

import woo.app.exception.Message;

/** Exception thrown when a client key is duplicated. */
public class DuplicateClientIdException extends Exception{
  /** Client key. */
  private String _key;

  /** @param key the duplicated key */
  public DuplicateClientIdException(String key) {
    _key = key;
  }

  public String getKey(){
    return _key;
  }

}
