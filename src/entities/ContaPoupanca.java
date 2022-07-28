package entities;

import java.time.LocalDateTime;

public class ContaPoupanca extends Conta {

    String diaAniversarioPoupanca;

    public ContaPoupanca(int numero, String diaAniversarioPoupanca) {
        super(numero);
        this.diaAniversarioPoupanca = diaAniversarioPoupanca;
    }
    public String getDiaAniversarioPoupanca() {
        return diaAniversarioPoupanca;
    }

    public void setDiaAniversarioPoupanca(String diaAniversarioPoupanca) {
        this.diaAniversarioPoupanca = diaAniversarioPoupanca;
    }
    public float correcaoAniversario() {
        if (diaAniversarioPoupanca.equals(LocalDateTime.now())) {
            this.setSaldo((this.getSaldo() * 0.05f) + this.getSaldo());
        } else {
            this.setSaldo((this.getSaldo() * 0.04f) + this.getSaldo());
        }
        return getSaldo();
    }
}