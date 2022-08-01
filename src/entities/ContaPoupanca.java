/**
 * Classe desenvolvida por Ludmila.
 */
package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ContaPoupanca extends Conta {

    static int diaAniversarioPoupanca;
    static Scanner scanner = new Scanner(System.in);
    static DateTimeFormatter formData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    static ArrayList<Double> debitos = new ArrayList<>();
    static ArrayList<Double> creditos = new ArrayList<>();
    static ArrayList<String> dhDebitos = new ArrayList<>();
    static ArrayList<String> dhCreditos = new ArrayList<>();

    /**
     * Método construtor que recebe como parametro o número da conta, senha e ativo
     */
    public ContaPoupanca(int numero, String senha, boolean ativo) {
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
    public static void iniciarConta() {
        gerarInformacoes();
        correcaoAniversario();
        movimentarConta();
    }

    /**
     * método que retorna data e saldo atuais
     */
    public static void gerarInformacoes() {
        System.out.println(formData.format(LocalDateTime.now()));

        System.out.println(String.format("Você acessou a CONTA POUPANÇA, seu saldo atual é de: R$ %.2f", getSaldo()));
    }

    /**
     * método que realiza a correção de aniversário
     */
    public static double correcaoAniversario() {
        System.out.println("Informe o dia atual: ");

        int dataAtual = scanner.nextInt();

        if (dataAtual == diaAniversarioPoupanca) {
            setSaldo((getSaldo() * 0.05f) + getSaldo());
            System.out.println("Feliz aniversário de conta! Receba sua correção de aniversário!");
            System.out.println(String.format("Seu saldo atual é de: R$ %.2f", getSaldo()));
        } else {
            setSaldo((getSaldo() * 0.04f) + getSaldo());
        }

        return getSaldo();
    }

    /**
     * método que realiza a movimentacao da conta
     */
    public static void movimentarConta() {
        do {
            System.out.println("Qual operação você deseja realizar?");
            System.out.println("[1] Débito");
            System.out.println("[2] Crédito");
            System.out.println("[3] Empréstimo");
            System.out.println("[4] Saldo");
            System.out.println("[5] Extrato das movimentações");
            System.out.println("[6] Voltar ao menu inicial");

            String operacao = scanner.next();

            if (operacao.equals("1")) {
                System.out.println("Digite o valor que deseja debitar: ");

                System.out.print("R$ ");
                double valor = scanner.nextDouble();

                debitarValor(valor);

            } else if (operacao.equals("2")) {
                System.out.println("Digite o valor que deseja depositar: ");

                System.out.print("R$ ");
                double valor = scanner.nextDouble();
                creditarValor(valor);

            } else if (operacao.equals("3")) {
                System.out.println("Você não pode realizar um empréstimo na conta poupança.");

            } else if (operacao.equals("4")) {
                System.out.println(String.format("Seu saldo atual é de: R$ %.2f", getSaldo()));

            } else if (operacao.equals("5")) {
                extratoConta();

                System.out.println("Deseja realizar mais alguma operação?");
                System.out.println("[1] Sim");
                System.out.println("[2] Não");

                String continuar = scanner.next();
                if (continuar.equals("2")) {
                    Menu.menuInicial();
                    break;
                }
            } else if (operacao.equals("6")) {
                Menu.menuInicial();
                break;
            } else {
                System.out.println("Opção selecionada inválida!");
            }
        } while (true);
    }

    /**
     * método para realização de saque ou para retornar ao menu inicial.
     */
    protected static void debitarValor(double valorDebitado) {
        if (valorDebitado <= 0 ){
            System.out.println("Você deve passar um valor positivo para o débito!\n");
            return;
        }

        if (valorDebitado > saldo) {
            System.out.println("Você não tem limite disponível para transação.");
            System.out.println(String.format("Saldo atual: R$ %.2f \n", getSaldo()));

        } else {
            saldo -= valorDebitado;

            System.out.println("Débito efetuado com sucesso!");
            System.out.println(String.format("Saldo atual: R$ %.2f\n", getSaldo()));

            dhDebitos.add(formData.format(LocalDateTime.now()));
            debitos.add(valorDebitado);
        }
    }

    /**
     * método para realização de depósito ou para retornar ao menu inicial.
     */
    public static void creditarValor(double valorCreditado) {
        if (valorCreditado <= 0 ){
            System.out.println("Você deve passar um valor positivo para o crédito\n");
            return;
        }
        saldo += valorCreditado;

        System.out.println("Crédito efetuado com sucesso!");
        System.out.println("Saldo atual: R$ " + getSaldo());

        dhCreditos.add(formData.format(LocalDateTime.now()));
        creditos.add(valorCreditado);
    }

    protected static void extratoCredito() {
        System.out.println("-------- Créditos -------- \n");

        if (creditos.isEmpty() != true) {
            for (int i = 0; i < creditos.size(); i++) {
                System.out.println("-------------------------------------------------------------");
                System.out.println("Data da Solicitação: " + dhCreditos.get(i));
                System.out.println("Valor do Crédito: " + String.format("R$ %.2f", creditos.get(i)));
                System.out.println("-------------------------------------------------------------");
            }
        } else {
            System.out.println("-------------------------------------------------------------");
            System.out.println("\033[31mVoce não possui transacões de Crédito!\033[m");
            System.out.println("-------------------------------------------------------------");
        }
    }

    protected static void extratoDebito() {
        System.out.println("-------- Débitos -------- \n");

        if (debitos.isEmpty() != true) {
            for (int i = 0; i < debitos.size(); i++) {
                System.out.println("-------------------------------------------------------------");
                System.out.println("Data da Solicitação: " + dhDebitos.get(i));
                System.out.println("Valor do Débito: " + String.format("R$ %.2f", debitos.get(i)));
                System.out.println("-------------------------------------------------------------");

            }
        } else {
            System.out.println("-------------------------------------------------------------");
            System.out.println("\033[31mVoce não possui transacões de Débito!\033[m\n");
            System.out.println("-------------------------------------------------------------");
        }
    }

    protected static void extratoConta() {
        System.out.println("-------- Extrato -------- \n");
        extratoCredito();
        extratoDebito();
    }

}



