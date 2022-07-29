package entities;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ContaPoupanca extends Conta {

    int diaAniversarioPoupanca;
    Scanner entrada = new Scanner(System.in);

    /**
     * Método construtor que recebe como parametro o número da conta, o dia do aniversário da conta e a senha)
     *
     * @param numero
     * @param diaAniversarioPoupanca
     * @param senha
     */
    public ContaPoupanca(int numero, String diaAniversarioPoupanca, String senha, boolean ativo) {
        super(numero, senha, ativo);
        this.setDiaAniversarioPoupanca(LocalDateTime.now().getDayOfMonth());
    }

    /**
     * Getters e Setters
     */
    public int getDiaAniversarioPoupanca() {
        return diaAniversarioPoupanca;
    }

    public void setDiaAniversarioPoupanca(int diaAniversarioPoupanca) {
        this.diaAniversarioPoupanca = diaAniversarioPoupanca;
    }

    /**
     * método de inicio da conta
     */
    public void iniciarConta() {
        System.out.println("Você acessou a CONTA POUPANÇA, seu saldo atual é de R$" + getSaldo());
    }

    /**
     * método que realiza a correção de aniversário
     */
    public float correcaoAniversario() {

        System.out.println("Informe o dia e o mês atual (apenas números): ");
        int dataAtual = entrada.nextInt();

        if (diaAniversarioPoupanca == dataAtual) {
            this.setSaldo((this.getSaldo() * 0.05f) + this.getSaldo());
            System.out.println("Feliz aniversário de conta! Receba sua correção de aniversário!");
        } else {
            this.setSaldo((this.getSaldo() * 0.04f) + this.getSaldo());
        }
        return getSaldo();
    }

    /**
     * método que realiza a movimentacao da conta
     */
    public void movimentarConta(Scanner entrada, ContaPoupanca conta) {

        if (!conta.isAtivo()) {
            System.out.println("Você gostaria de ativar sua conta poupança? [1]-Sim \n[2]-Retornar ao menu inicial: ");
            String ativarConta = entrada.nextLine();
            if (ativarConta == "1") {
                conta.setAtivo(true);
            } else if (ativarConta == "2") {
                conta.setAtivo(false);
                Menu.menuInicial();
            } else {
                System.out.println("Operação inválida!");
            }

        } else if (conta.isAtivo()) {
            do {
                conta.correcaoAniversario();
                System.out.println("Digite [1]-Para sacar \n[2]-Para depósitar.");
                String operacao = entrada.nextLine();

                if (operacao == "1") {
                    debitar();
                } else if (operacao == "2") {
                    depositar();
                    System.out.println("Deseja realizar outra operação? [1]-Sim \n[2]-Não");
                    String continuar = entrada.nextLine();
                    if (continuar == "2") break;
                }

            } while (true);
        }
    }

    /**
     * método para realização de saque ou para retornar ao menu inicial.
     */
    void debitar() {

        System.out.println("Por favor, informe o valor que você gostaria de sacar: ");
        double debito = entrada.nextDouble();
        System.out.println("Você deseja confirmar a operação débito no valor de: " + debito + "[1]-SIM [2]-NÃO");
        String confirmacaoOperacao = entrada.nextLine();

        if (confirmacaoOperacao == "1") {
            System.out.println("Operação realizada com sucesso. O seu novo saldo é de: R$" + (getSaldo() - debito));
        } else if (confirmacaoOperacao == "2") {
            System.out.println("Operação cancelada!");
            Menu.menuInicial();
        }
    }

    /**
     * método para realização de depósito ou para retornar ao menu inicial.
     */
    public void depositar() {

        System.out.println("Por favor, informe o valor você gostaria de depositar: ");
        double credito = entrada.nextDouble();
        System.out.println("Você deseja confirmar a operação crédito no valor de: " + credito + "[1]-SIM \n[2]-NÃO");
        String confirmacaoOperacao = entrada.nextLine();

        if (confirmacaoOperacao == "1") {
            System.out.println("Operação realizada com sucesso. O seu novo saldo é de: R$" + (getSaldo() + credito));
        } else if (confirmacaoOperacao == "2") {
            System.out.println("Operação cancelada!");
            Menu.menuInicial();
        }
    }
}
