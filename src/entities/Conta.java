/**
 Classe desenvolvida por Emerson, Luiz Fernando e Ludmila.
 */
package entities;

public abstract class Conta {

    private int numero;
    private Cliente cliente;
    protected static double saldo;
    private static boolean ativo;
    private String senha = "0000";

    public Conta (int numero, String senha, boolean ativo) {
        super();
        this.numero = numero;
        Conta.ativo = false;
        Conta.saldo = 0;
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

    public static double getSaldo() {
        return saldo;
    }

    public static void setSaldo(double saldo) {
        Conta.saldo = saldo;
    }

    public static boolean isAtivo() {
        return ativo;
    }

    public static void setAtivo(boolean ativo) {
        Conta.ativo = ativo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
