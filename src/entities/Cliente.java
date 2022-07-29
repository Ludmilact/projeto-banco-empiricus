package entities;

import java.time.LocalDateTime;

public class Cliente {

    String nome;
    String cpf;
    String dataNascimento; //TODO: pesquisar como fazer local datetime
    double salario;
    String cnpj;

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

    public boolean validarNome() {

        //TODO: VALIDAR SE O NOME TEM OS CARACTERES CERTOS E SE A PESSOA DIGITOU
        return false;
    }

    /**
     * Método válidarCpf chama o outro método validarCpf, para validação interna.
     * @return o método void de validarCpf + get cpf para pegar o último cpf informado.
     */
    public boolean validarCpf() {
        return validarCpf(getCpf());
    }

    /**
     * Método static para conseguir recuperar o cpf por parametro,
     * reduzindo a necessidade de instanciar diversas vezes um cliente.
     * @param cpf
     * @return true se o cpf for válido.
     */
    public static boolean validarCpf(String cpf) {
        if (cpf.length() != 11) {
            return false;
        } else {
            return true;
        } //TODO: É TUDO NÚMERO?
    }

    public boolean validaDataDeNascimento() {
        //TODO: VALIDAR SE A DATA NASCIMENTO É UMA DATA
        return false;
    }
    public static boolean validarCNPJ(String cnpj) {
        if (cnpj.length() != 14) {
            return false;
        } else {
            return true;
        }
    }
    public static boolean validarSenha (String senha) {
        if(senha.length() == 5) {
        }
        return true;
    }
}
