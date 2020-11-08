package woo.core.exception;



public class UnknownClientKeyException extends Exception{


  /** Unknown key. */
  private String _id;

  public UnknownClientKeyException(String id) {
    _id = id;
  }

  public String getId(){return _id;}

}
