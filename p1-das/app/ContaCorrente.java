import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ContaCorrente {

    private static List<ContaCorrente> contasCadastradas = new LinkedList<ContaCorrente>();
    private int agencia;
    private int conta;
    private double saldo;
    private List<Receita> receitas = new LinkedList<Receita>();
    private List<app.Despesa> despesas = new LinkedList<app.Despesa>();

    private ContaCorrente(int agencia, int conta, double saldo) {
        this.agencia = agencia;
        this.conta = conta;
        this.saldo = saldo;
    }

    /**
     * Retorna uma nova conta corrente caso ela ainda nao esteja cadastrada no sistema ou
     * uma excecao ContaJaCadastradaException caso contrario
     * @param agencia numero da agencia
     * @param conta numero da conta
     * @param saldo saldo de abertura
     * @return objeto conta corrente recem criado
     */
    public static ContaCorrente obterContaCorrente(int agencia, int conta, double saldo) throws ContaJaCadastradaException{
        ContaCorrente c = pesquisarContaCorrente (agencia, conta);
        if (c == null) {
            c = new ContaCorrente(agencia, conta, saldo);
            contasCadastradas.add(c);
        }
        else
            throw new ContaJaCadastradaException();
        return c;
    }

    /**
     * metodo que pesquisa uma conta especifica em uma lista de contas cadastradas
     * @param agencia agencia da conta a ser pesquisada
     * @param conta numero da conta a ser pesquisada
     * @return objeto conta encontrado ou nulo
     */
    public static ContaCorrente pesquisarContaCorrente(int agencia, int conta) {
        ContaCorrente resposta = null;
        boolean contaEncontrada = false;
        Iterator<ContaCorrente> it = contasCadastradas.iterator();
        while (it.hasNext() && !contaEncontrada) {
            ContaCorrente c = it.next();
            if (c.getAgencia() == agencia && c.getConta() == conta) {
                resposta = c;
                contaEncontrada = true;
            }
        }
        return resposta;
    }

    public int getAgencia() {
        return agencia;
    }

    public int  getConta() {
        return conta;
    }

    public double getSaldo() {
        return saldo;
    }

    public double criarReceita(float valorTransacao) throws ValorEmBrancoException {
        if (valorTransacao == 0)
            throw new ValorEmBrancoException();

        Receita r = new Receita(valorTransacao);
        receitas.add(r);
        saldo += r.getValorTransacao();
        return saldo;
    }

    public double criarDespesa(float valorTransacao) throws ValorEmBrancoException {
        if (valorTransacao == 0)
            throw new ValorEmBrancoException();

        app.Despesa r = new app.Despesa(valorTransacao);
        despesas.add(r);
        saldo -= r.getValorTransacao();
        return saldo;
    }

}