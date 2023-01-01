/**
 * Classe OperacaoInvalida relacionada com todas as operacoes que nao sao
 * bem sucedidas. Operacoes que dao erro, sao assinaladas com esta excepcao.
 *
 * @author PO
 * @version 1.0
 **/
public class OperacaoInvalida extends Exception {

    /**
    * Construtor da classe OperacaoInvalida.
    **/
    public OperacaoInvalida() {
    }

    /**
    * Construtor da classe OperacaoInvalida.
    *
    * @param msg mensagem referente 'a excepcao que ocorreu.
    **/
    public OperacaoInvalida(String msg) {
        super(msg);
    }
}
