package woo.core.products;

import woo.core.users.Supplier;

import java.io.Serializable;

public class Book extends Product implements Serializable {
    private  final String _title,_author,_isbn;

    public Book(int price, int valorCrit, String key, String title, String author, String isbn, Supplier supplier){
        super(price,valorCrit,key,supplier);
        _title= title;
        _author = author;
        _isbn = isbn;
    }

    public String getTitle(){
        return _title;
    }
    public String getAuthor(){
        return _author;
    }
    public String getIsbn(){
        return _isbn;
    }

    public String toString(){
        return "BOOK"+"|"+super.getKey()+"|"+super.getSupplier().getId()+"|"+super.getPrice()+"|"+super.getValCrit()+"|"
                +getValExist()+"|"+getTitle()+ "|"+getAuthor()+"|"+getIsbn();
    }
}
