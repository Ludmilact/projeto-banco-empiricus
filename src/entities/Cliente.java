/**
 Classe desenvolvida por Emerson, Luiz Fernando e Ludmila.
 */

package entities;

import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Cliente {

    String nome;
    String cpf;
    String dataNascimento;
    double salario;
    String cnpj;

    public Cliente(String nome, String cpf, String cnpj, String dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.dataNascimento = dataNascimento;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public static boolean validarNome(String nome) {
        if (!(nome == null) && !nome.isBlank() && !nome.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Método válidarCpf chama o outro método validarCpf, para validação interna.
     *
     * @return o método void de validarCpf + get cpf para pegar o último cpf informado.
     */
    public boolean validarCpf() {
        return validarCpf(getCpf());
    }

    /**
     * Método static para conseguir recuperar o cpf por parametro,
     * reduzindo a necessidade de instanciar diversas vezes um cliente.
     *
     * @param cpf
     * @return true se o cpf for válido.
     */
    public static boolean validarCpf(String cpf) {
        if (cpf.length() != 11) {
            return false;
        } else {
            return true;
        }

    }

    public static boolean validarData(String dataNascimento) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        try {
            df.parse(dataNascimento);

        } catch (ParseException ex) {
            return false;
        }
        return true;
    }

    public static boolean validarCNPJ(String cnpj) {
        if (cnpj.length() == 14) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validarSenha(String senha) {
        if (senha.length() == 5) {
        }
        return true;
    }
}
