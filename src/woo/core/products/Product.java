package woo.core.products;

public abstract class Produto{
    private int _preco;
    private int _valCrit;
    private int _valExist;
    private int _key;

    protected Produto(int preco,int valorCritico, int valorExistente, int key){
        _preco = preco;
        _valCrit = valorCritico;
        _valExist = valorExistente;
        _key = key;
    }

    public int getPreco(){
        return _preco;
    }
    public int getValCrit(){
        return _valCrit;
    }
    public int getValExist(){
        return _valExist;
    }

    public void setPreco(int preco){
        _preco= preco;
    }

    public void decreaseValue(int value){
        _valExist -= value;
    }
    public void addValue(int value){
        _valExist += value;
    }

}