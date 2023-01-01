/**
 * Implementa a interface IConfiguracaoAplicacaoBancaria.
 *
 * @author PO
 * @version 1.0
 **/
public class ConfiguracaoAplicacaoBancaria implements IConfiguracaoAplicacaoBancaria {

    /**
    * Cria uma conta
    *
    * @param valor saldo inicial da conta a ser criada.
    * @param titulares[] titulares da conta.
    * @param banco banco onde a conta deve ser criada.
    *
    * @return a conta criada.
    **/
    public IConta criaConta(float valor, ITitular titulares[], IBanco banco) {
        return new Conta(valor, titulares, banco);
    }

    /**
    * Cria um titular
    *
    * @param nome nome proprio do titular a criar.
    * @param apelido apelido do titular a criar.
    * @param BI numero do BI do titular a criar.
    * @param banco banco onde o titular deve ser criado.
    *
    * @return a conta criada.
    **/
    public ITitular criaTitular(String nome, String apelido, int BI, IBanco banco) {
        return new Titular(nome, apelido, BI, banco);
    }

    /**
    * Cria o menu para manipular titulares
    *
    * @param titular titular que se pretende manipular.
    *
    * @return o menu com os comandos para manipular o titular.
    **/
    public Menu criaMenuTitular(ITitular titular) {
        Comando c[] = { new ComandosTitular.AlterarNome(titular), new ComandosTitular.AlterarApelido(titular), new ComandosTitular.MostrarContasTitular(titular), new ComandosTitular.RemoverTitularBanco(titular) };
        return new Menu(c);
    }

    /**
    * Cria o menu para manipular contas.
    *
    * @param conta conta que se pretende manipular.
    *
    * @return o menu com os comandos para manipular a conta.
    **/
    public Menu criaMenuConta(IConta conta) {
        Comando c[] = { new ComandosConta.Depositar(conta), new ComandosConta.Levantar(conta), new ComandosConta.MostrarSaldo(conta, false), new ComandosConta.MostrarSaldo(conta, true), new ComandosConta.MostrarTitularesConta(conta), new ComandosConta.CriarDepositoPrazo(conta), new ComandosConta.MostrarDepositosConta(conta), new ComandosConta.MostrarDepositoConta(conta), new ComandosConta.RemoverConta(conta) };
        return new Menu(c);
    }

    /**
    * Cria o menu para aceder aos bancos.
    *
    * @param banco banco que se pretende manipular.
    *
    * @return o menu com os comandos para manipular o banco.
    **/
    public Menu criaMenuBanco(IBanco banco) {
        Comando c[] = { new ComandosBanco.CriarTitular(banco), new ComandosBanco.CriarConta(banco), new ComandosBanco.MostrarTitulares(banco), new ComandosBanco.MostrarContas(banco), new ComandosBanco.MostrarTitular(banco), new ComandosBanco.MostrarConta(banco), new ComandosBanco.AlterarData(banco), new ComandosBanco.ColocarData() };
        return new Menu(c);
    }

    /**
    * Cria o menu para manipular depositos a prazo.
    *
    * @param deposito deposito a prazo a manipular.
    *
    * @return o menu com os comandos para manipular o deposito a prazo.
    **/
    public final Menu criaMenuDepositoPrazo(IDepositoPrazo deposito) {
        Comando c[] = { new ComandosDepositoPrazo.MostrarSaldo(deposito), new ComandosDepositoPrazo.VerDias(deposito), new ComandosDepositoPrazo.Terminar(deposito) };
        return new Menu(c);
    }
}
