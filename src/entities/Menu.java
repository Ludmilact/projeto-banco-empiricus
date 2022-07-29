package entities;

import java.util.Scanner;

public class Menu {
    public static void menuInicial() {

        while(true) {

            System.out.println("Bem-vindo ao DevBank! \n [1]-Entrar \n [2]-Abrir conta \n [3]-Sair");

            Scanner entrada = new Scanner(System.in);
            int entrar = entrada.nextInt();


            if (entrar == 1) {
                menuBuscarConta();

                //TODO: Luiz Array de clientes e buscar se tem essa conta
            } else if (entrar == 2 ) {
                if (menuCriarConta() == false) {
                    break;
                }
            } else if (entrar == 3){
                System.out.println("Encerrando programa!");
                break;
            } else {
                System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    public static void menuBuscarConta(){
        Scanner entrada = new Scanner(System.in);

        String numeroDocumento;
        String senhaUsuario;

        //TODO: buscar a conta atraves do cpf ou CNPJ
        System.out.println("[1]-Para informar o número do seu CPF \n[2]-Para informar o número do CNPJ da empresa: ");
        int numeroEscolhido;
        numeroEscolhido = entrada.nextInt();

        if (numeroEscolhido == 1 ) {
            System.out.println("Digite o CPF do titular da conta (somente números): ");
            numeroDocumento = entrada.next();

            boolean cpfValido = Cliente.validarCpf(numeroDocumento);
            if (cpfValido) {
                System.out.println("CPF encontrado!"); //TODO: Vale a pena continuar avisando que o cpf foi encontrado?

                //TODO: PROCURAR O CLIENTE NA LISTA
                //TODO: IMPRIMIR A CONTA DA PESSOA

                System.out.println("Por favor, digite a sua senha de 5 dígitos: ");
                senhaUsuario = entrada.nextLine();
                boolean senhaValida = Cliente.validarSenha(senhaUsuario);

                if (senhaValida) {
                    System.out.println("Senha correta!");
                } else {
                    System.out.println("Senha inválida. Você possui mais 2 tentativas.");
                }
            } else {
                System.out.println("CPF inválido.");
            }

        } else if (numeroEscolhido == 2) {

            System.out.println("Digite o CNPJ da empresa (somento números) : ");
            numeroDocumento = entrada.nextLine();
            boolean validarCNPJ = Cliente.validarCNPJ(numeroDocumento);
            if (validarCNPJ) {
                System.out.println("CNPJ encontrado!");
                //TODO: DIGITAR E VÁLIDAR SENHA
                //TODO: LUIZ PROCURAR O CLIENTE NA LISTA
                //TODO: IMPRIMIR A CONTA DA PESSOA
            } else {
                System.out.println("CNPJ inválido.");
            }

        } else {
            System.out.println("\033[31m\"Opção inválida!\" \033[m");
        }
    }

    public static boolean menuCriarConta() {

        Scanner entrada = new Scanner(System.in);

        System.out.println("[1]- Para continuar \n[0]- Para sair");
        int abrirConta = entrada.nextInt();

        if (abrirConta == 1) {

            Conta conta = menuSelecionarContas();
            Cliente cliente = menuDadosClientes();
            //TODO : AQUI CHAMAR OS DADOS ESPECIFICOS DA CONTA
            conta.setCliente(cliente);
        } else {
            System.out.println("Sinto muito, se mudar de ideia estamos aqui para abrir sua conta!");
            return false;
        }
        return true;
    }
    public static Cliente menuDadosClientes() {

        Scanner entrada = new Scanner(System.in);
        Cliente cliente = new Cliente();

        do {
            System.out.println("Digite o seu nome: ");
            cliente.setNome(entrada.next());
        } while (cliente.validarNome());

        do {
            System.out.println("CPF: ");
            cliente.setCpf(entrada.next());
        } while (cliente.validarCpf() == false);

        do {
            System.out.println("Digite a data de nascimento: ");
            cliente.setDataNascimento(entrada.next());
        } while (cliente.validaDataDeNascimento());

        return cliente;
    }

    public static Conta menuSelecionarContas() {

        System.out.println("Escolha o tipo de conta que você deseja abrir: ");
        System.out.println("[1]-Conta poupança");
        System.out.println("[2]-Conta corrente");
        System.out.println("[3]-Conta especial");
        System.out.println("[4]-Conta empresarial");
        System.out.println("[5]-Conta estudantil");

        Scanner entradaConta = new Scanner(System.in);
        int tipoConta = entradaConta.nextInt();
        Conta conta = null;

        //TODO: Emerson vai fazer o gerador de números.
        switch (tipoConta) {

            case 1: // todo: informacoes especificas de cada conta
                conta = new ContaPoupanca(12, "2121", "212121");
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
