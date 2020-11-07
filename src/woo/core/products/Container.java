package woo.core.products;

public class Contentor extends Produto{
    enum TipoServico{
        C4,
        B4,
        C5,
        DL
    }
    enum QualServico{
        NORMAL,
        AEREO,
        EXPRESSO,
        MAO
    }

    private TipoServico _servico;
    private QualServico _qualServico;

    public Contentor(int preco,int valorCritico, int valorExistente, int key,TipoServico servico, QualServico qualServico){
        super(preco,valorCritico,valorExistente,key);
        _servico = servico;
        _qualServico = qualServico;
    }
}
