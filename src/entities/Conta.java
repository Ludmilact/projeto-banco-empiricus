package entities;

public abstract class Conta {

    private int numero;
    private Cliente cliente; // TODO: TIREI CPF PQ UMA CONTA NAO TEM UM CPF
    protected float saldo;
    private boolean ativo;
    private String senha;

    public Conta (int numero, String senha) {
        super();
        this.numero = numero;
        this.ativo = false;
        this.saldo = 0;
        this.senha = senha;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    abstract void debitar(double valor); // cada classe tem sua implementaçao de debito

    public void imprirmirExtradoDeMovimentacoes() {
        // TODO - tudo que a pessoa fez de movimentacao com valores
    }

    public void tranferir() {
        // TODO - usar o método debitar para tirar de uma conta e passa para outra
    }
}
