package woo.core.exception;



public class UnknownClientIdException extends UnknownIdException{

  private String _id;

  public UnknownClientIdException(String id) {
    _id = id;
  }

  public String getId(){return _id;}

}
