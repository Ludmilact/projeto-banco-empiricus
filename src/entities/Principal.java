package entities;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        menuInicial();

    }

    public static void menuInicial() {

        while(true) {

            System.out.println("Olá, você já possui uma conta conosco? Digite 1 para 'Sim' e 2 para 'Nao'");

            Scanner entrada = new Scanner(System.in);
            int possuiConta = entrada.nextInt();
            int numeroEscolhido; //TODO: RENOMEAR ISSO AQUI, SOCORRO DEUS
            String numeroDocumento;

            if (possuiConta == 1) {
                //TODO: buscar a conta atraves do cpf ou CNPJ
                System.out.println("Por favor, digite 1 para informar o número do seu CPF ou 2 para informar o número do CNPJ da empresa: ");
                numeroEscolhido = entrada.nextInt();
                if (numeroEscolhido == 1 ) {
                    System.out.println("Digite o CPF do titular da conta: ");
                    numeroDocumento = entrada.next();
                    Cliente.validarCpf(numeroDocumento);
                } else if (numeroEscolhido == 2) {
                    System.out.println("Digite o CNPJ da empresa: ");
                    numeroDocumento = entrada.nextLine();
                    Cliente.validarCNPJ(numeroDocumento);
                } else {
                    System.out.println("Número inválido.");
                }

                //TODO: Array de clientes e buscar se tem essa conta
            } else if (possuiConta == 2 ) {
                System.out.println("Você gostaria de abrir uma conta? Digite: 1 para continuar ou 0 para sair");
                int abrirConta = entrada.nextInt();

                if (abrirConta == 1) {
                    Cliente cliente = informarDadosClientes();
                    Conta conta = selecionarContas();

                    conta.setCliente(cliente);
                } else {
                    System.out.println("Sinto muito, se mudar de ideia estamos aqui para abrir sua conta!");
                    break;
                }
            } else {
                System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    public static Cliente informarDadosClientes() {

        Scanner entrada = new Scanner(System.in);
        Cliente cliente = new Cliente();

        do {
            System.out.println("Digite o seu nome: ");
            cliente.setNome(entrada.next());
        } while (cliente.validarNome());

        do {
            System.out.println("CPF: ");
            cliente.setCpf(entrada.next());
        } while (cliente.validarCpf());

        do {
            System.out.println("Digite a data de nascimento: ");
            cliente.setDataNascimento(entrada.next());
        } while (cliente.validaDataDeNascimento());

        return cliente;
    }

    public static Conta selecionarContas() {

        System.out.println("Escolha o tipo de conta que você deseja abrir: ");
        System.out.println("1. Conta poupança");
        System.out.println("2. Conta corrente");
        System.out.println("3. Conta especial");
        System.out.println("4. Conta empresa");
        System.out.println("5. Conta estudantil");

        Scanner entradaConta = new Scanner(System.in);
        int tipoConta = entradaConta.nextInt();
        Conta conta = null;

        switch (tipoConta) {

            case 1: // todo: informacoes especificas de cada conta
                conta = new ContaPoupanca(12, "2121");
                break;
            case 2: // todo: informacoes especificas de cada conta
                break;
            case 3:  // todo: informacoes especificas de cada conta
                break;
            case 4: // todo: informacoes especificas de cada conta
                break;
            case 5: // todo: informacoes especificas de cada conta
                break;
        }
        return conta;
    }
}
