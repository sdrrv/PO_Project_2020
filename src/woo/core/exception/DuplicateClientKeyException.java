package woo.core.exception;

import woo.app.exception.Message;

/** Exception thrown when a client key is duplicated. */
public class DuplicateClientKeyException {
  /** Client key. */
  private String _key;

  /** @param key the duplicated key */
  public DuplicateClientKeyException(String key) {
    _key = key;
  }

  public String getKey(){
    return _key;
  }

}
