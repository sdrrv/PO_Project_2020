package woo.core.products;

public class Livro extends Produto {
    private String _titulo;
    private String _autor;
    private String _isbn;

    public Livro(int preco,int valorCritico, int valorExistente, int key, String titulo, String autor, String isbn){
        super(preco,valorCritico,valorExistente,key);
        _titulo= titulo;
        _autor = autor;
        _isbn = isbn;
    }

    public String getTitulo(){
        return _titulo;
    }
    public String getAutor(){
        return _autor;
    }
    public String getIsbn(){
        return _isbn;
    }
}
