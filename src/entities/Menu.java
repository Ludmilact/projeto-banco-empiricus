/**
 Classe desenvolvida por Emerson, Luiz Fernando e Ludmila.
 */
package entities;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    static ArrayList<Conta> contas = new ArrayList<>();

    public static void menuInicial() {

        while (true) {

            System.out.println("Bem-vindo ao Tá Rodando Bank \nFazendo seu dinheiro girar! \n\n [1] Entrar \n [2] Abrir conta \n [3] Sair ");

            Scanner entrada = new Scanner(System.in);
            int entrar = entrada.nextInt();

            if (entrar == 1) {
                menuBuscarConta();

            } else if (entrar == 2) {
                menuCriarConta();

            } else if (entrar == 3) {
                System.out.println("Até a proxima!");
                System.exit(0);
            } else {
                System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    public static void menuBuscarConta() {

        Scanner entrada = new Scanner(System.in);

        String numeroDocumento;
        String senhaUsuario;

        System.out.println("[1] Para informar o número do seu CPF: \n[2] Para informar o número do CNPJ da empresa: ");
        int numeroEscolhido;
        numeroEscolhido = entrada.nextInt();

        if (numeroEscolhido == 1) {
            System.out.println("Digite o CPF do titular da conta: ");
            numeroDocumento = entrada.next();
            boolean cpfValido = Cliente.validarCpf(numeroDocumento);
            if (cpfValido) {
                System.out.println("CPF encontrado!");

                System.out.println("Por favor, digite a sua senha de 5 dígitos: ");
                senhaUsuario = entrada.next();

                if (senhaUsuario.length() == 5) {
                    System.out.println("Senha correta!");
                    menuSelecionarContas();
                } else {
                    System.out.println("Senha incorreta!");
                    menuBuscarConta();
                }
            } else {
                System.out.println("CPF invalido!");
            }

        } else if (numeroEscolhido == 2) {

            System.out.println("Digite o CNPJ da empresa: ");
            numeroDocumento = entrada.next();
            boolean cnpjValido = Cliente.validarCNPJ(numeroDocumento);
            if (cnpjValido) {
                System.out.println("CNPJ encontrado!");
            } else {
                System.out.println("CNPJ inválido!");
                menuBuscarConta();
            }

            System.out.println("Por favor, digite a sua senha de 5 dígitos: ");
            senhaUsuario = entrada.next();

            if (senhaUsuario.length() == 5) {
                System.out.println("Senha correta!");
                menuSelecionarContas();
            } else {
                System.out.println("Senha incorreta!");
                menuBuscarConta();
            }

        } else {
            System.out.println("\033[31m\"Opção inválida!\" \033[m");
        }
    }

    public static void menuCriarConta() {
        Cliente cliente = menuDadosClientes();
        Conta conta = menuSelecionarContas();

        if (ContaPoupanca.class.isInstance(conta)) {
            ContaPoupanca.iniciarConta();
        } else if (ContaEstudantil.class.isInstance(conta)) {
            ContaEstudantil.startConta();
        } else if (ContaEmpresarial.class.isInstance(conta)) {
            ContaEmpresarial.operacoes();
        } else if (ContaCorrente.class.isInstance(conta)) {
            ContaCorrente.operacao();
        }

        conta.setCliente(cliente);
        contas.add(conta);
    }

    public static Cliente menuDadosClientes() {
        Scanner entrada = new Scanner(System.in);
        Cliente cliente = new Cliente(null, null, null, null);

        do {
            System.out.println("Digite o seu nome: ");
            cliente.setNome(entrada.nextLine());
        } while (Cliente.validarNome("Nome"));

        boolean cpfValido = true;
        boolean cnpjValido = true;
        int cpf = 1, cnpj = 2;
        System.out.println("[1] Conta Fisica: (CPF) \n[2] Conta Juridica (CNPJ)");
        int opcao = entrada.nextInt();

        if (opcao == cpf) {

            do {
                if (cpfValido == false) {
                    System.out.println("CPF inválido, por favor tente novamente!");
                }
                System.out.println("CPF (11 dígitos): ");
                cliente.setCpf(entrada.next());
                cpfValido = cliente.validarCpf();

            } while (cpfValido == false);
        } else if (opcao == cnpj) {
            do {
                if (cnpjValido == false) {
                    System.out.println("CNPJ inválido, por favor tente novamente!");
                }
                System.out.println("Cnpj (14 dígitos): ");
                cliente.setCnpj(entrada.next());
                cnpjValido = cliente.validarCNPJ(cliente.getCnpj());

            } while (cnpjValido == false);

        }
        System.out.println("Digite a data de nascimento: ");
        cliente.setDataNascimento(entrada.next());

        return cliente;
    }

    public static Conta menuSelecionarContas() {

        System.out.println("Escolha o tipo de conta que você deseja abrir: ");
        System.out.println("[1] Conta poupança");
        System.out.println("[2] Conta corrente");
        System.out.println("[3] Conta especial");
        System.out.println("[4] Conta empresarial");
        System.out.println("[5] Conta estudantil");

        Conta conta = null;

        Scanner entradaConta = new Scanner(System.in);
        int tipoConta = entradaConta.nextInt();

        if (tipoConta == 1) {
            conta = new ContaPoupanca(12, null, true);
            ContaPoupanca.iniciarConta();
        } else if (tipoConta == 2) {
            conta = new ContaCorrente(tipoConta, null, false);
            ContaCorrente.operacao();
        } else if (tipoConta == 3) {
            System.out.println("Conta especial não foi desenvolvida.");
        } else if (tipoConta == 4) {
            conta = new ContaEmpresarial(tipoConta, null, false);
            ContaEmpresarial.operacoes();
        } else if (tipoConta == 5) {
            conta = new ContaEstudantil(tipoConta, null, false);
            ContaEstudantil.startConta();
        }

        return conta;
    }
}
