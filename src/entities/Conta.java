package entities;

public abstract class Conta {

    private int numero;

    private Cliente cliente; // TODO: TIREI CPF PQ UMA CONTA NAO TEM UM CPF
    protected float saldo;
    private boolean ativo;

    public Conta (int numero) {
        super();
        this.numero = numero;
        this.ativo = false;
        this.saldo = 0;
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

    private static void debito(double valor) {
        System.out.println("Débito: " + valor);

    }
}
