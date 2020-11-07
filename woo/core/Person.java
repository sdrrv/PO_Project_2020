public abstract class Pessoa {
    private String _nome;
    private String _morada;
    private int _key;

    protected Pessoa(String nome, String morada, int key){
        _nome= nome;
        _morada= morada;
        _key= key;
    }

    public String getNome(){
        return _nome;
    }
    public String getMorada(){
        return _morada;
    }
    public int getKey(){
        return _key;
    }
}
