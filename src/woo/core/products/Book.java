package woo.core.products;

import java.io.Serializable;

public class Book extends Product implements Serializable {
    private String _title;
    private String _author;
    private String _isbn;

    public Book(int price,int valorCrit, int valorExist, int key, String title, String author, String isbn){
        super(price,valorCrit,valorExist,key);
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
}
