package entities;

import java.time.LocalDateTime;

public class Cliente {

    String nome;
    String cpf;
    String dataNascimento; //TODO: pesquisar como fazer local datetime
    double salario;

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

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public boolean validarNome() {

        //TODO: VALIDAR SE O NOME TEM OS CARACTERES CERTOS E SE A PESSOA DIGITOU
        return false;
    }

    public boolean validarCpf() {
        //TODO: VALIDAR CPF
        return false;
    }
    public boolean validaDataDeNascimento() {
        //TODO: VALIDAR SE A DATA NASCIMENTO É UMA DATA
        return false;
    }
    public boolean validarSalario() {
        //TODO: validar se é um número (para ver se nao tem pontos, ou letras)
        return false;
    }
}
