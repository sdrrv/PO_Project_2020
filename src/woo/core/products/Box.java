package woo.core.products;

public class Caixa extends Produto {

    enum TipoServico{
        C4,
        B4,
        C5,
        DL
    }

    private TipoServico _servico;

    public Caixa(int preco,int valorCritico, int valorExistente, int key, TipoServico servico){
        super(preco,valorCritico,valorExistente,key);
        _servico = servico;
    }

    public TipoServico getServico(){
        return _servico;
    }
}
