/**
 * Classe desenvolvida por Luiz Fernando
 */
package entities;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ContaEmpresarial extends Conta {
    static DateTimeFormatter formData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    static double emprestimoEmpresa;

    static ArrayList<String> parcelas = new ArrayList<>();

    static ArrayList<Double> valorDevolver = new ArrayList<>();
    static ArrayList<Double> valorParcelas = new ArrayList<>();

    static ArrayList<Double> Debitos = new ArrayList<>();
    static ArrayList<Double> Creditos = new ArrayList<>();
    static ArrayList<Double> Emprestimos = new ArrayList<>();
    static ArrayList<String> DHDebitos = new ArrayList<>();
    static ArrayList<String> DHCreditos = new ArrayList<>();
    static ArrayList<String> DHEmprestimos = new ArrayList<>();
    private String cnpj;

    public ContaEmpresarial(int numero, String cnpj, boolean ativo) {

		super(numero, "0000", ativo);
        this.cnpj = cnpj;
        setAtivo(ativo);
        setEmprestimoEmpresa(10000);
        boasVindas();
	}

    public static double getEmprestimoEmpresa() {
        return emprestimoEmpresa;
    }

    public static void setEmprestimoEmpresa(double emprestimoEmpresa) {
        ContaEmpresarial.emprestimoEmpresa = emprestimoEmpresa;
    }

   public void boasVindas(){
        System.out.println("Você acessou a CONTA EMPRESARIAL, seu saldo atual é de " +
                            String.format("R$ %.2f", getSaldo()));
    }
	protected static void debitarValor(double valorDebitado) {
		if(valorDebitado > saldo) {
			System.out.println("\033[31mVocê não tem limite disponivel para transação\033[m");
		}
		else {
			saldo -= valorDebitado;
            System.out.println("\033[32mDébito efetuado com sucesso, saldo atual: R$" +
                                String.format("R$ %.2f", getSaldo()) + "\033[m");
            System.out.println(formData.format(LocalDateTime.now()));
            DHDebitos.add(formData.format(LocalDateTime.now()));
            Debitos.add(valorDebitado);
		}
	}

	private static void creditarValor(double valorCreditado) {
        saldo += valorCreditado;
        System.out.println("\033[32mCrédito efetuado com sucesso, saldo atual: " +
                            String.format("R$ %.2f", getSaldo()) + "\033[m");
        System.out.println(formData.format(LocalDateTime.now()));
        DHCreditos.add(formData.format(LocalDateTime.now()));
        Creditos.add(valorCreditado);
    }

    protected static void extratoDebito () {
        System.out.println("Débitos\n");
        if(Debitos.isEmpty() != true) {
            for(int i = 0; i < Debitos.size(); i++) {
                System.out.println("Data da Solicitação: " + DHDebitos.get(i));
                System.out.println("Valor do Débito R$" + Debitos.get(i));
                System.out.println();
            }
        }
        else {
            System.out.println("\033[31mVocê não possui transações de Débito!\033[m");
            System.out.println();
        }
    }
    protected static void extratoEmprestimo () {
        System.out.println("Empréstimos\n");
        if(Emprestimos.isEmpty() != true ) {
            for(int i = 0; i < Emprestimos.size(); i++) {
                System.out.println("Data da Solicitação: " + DHEmprestimos.get(i));
                System.out.println("Valor do Empréstimo: R$" + Emprestimos.get(i));
            }
        }
        else {
            System.out.println("\033[31mVocê não possui transações de Empréstimo!\033[m");
            System.out.println();
        }
    }
    protected static void extratoCredito () {
        System.out.println("Créditos\n");
        if(Creditos.isEmpty() != true) {
            for(int i = 0; i < Creditos.size(); i++) {
                System.out.println("Data da Solicitação: " + DHCreditos.get(i));
                System.out.println("Valor do Crédito: R$" + Creditos.get(i));
                System.out.println();
            }
        }
        else {
            System.out.println("\033[31mVocê não possui transações de Crédito!\033[m");
            System.out.println();
        }
    }
	
	
    private static void pedirEmprestimo(double valorEmprestimo){
        if(valorEmprestimo <= getEmprestimoEmpresa()){
            escolherParcelas(valorEmprestimo);
            setSaldo(getSaldo() + valorEmprestimo);
            setEmprestimoEmpresa(getEmprestimoEmpresa() - valorEmprestimo);
            System.out.println("\033[32mOperação realizada com sucesso!\033[m");
            System.out.println("Seu novo saldo é de: " + String.format("R$ %.2f", getSaldo()));
            DHEmprestimos.add(formData.format(LocalDateTime.now()));
            Emprestimos.add(valorEmprestimo);
        } else {
            System.out.println("\033[31mO empréstimo não pode ser realizado!\n\033[m" +
                               "O valor solicitado é maior que seu limite.");
        }
    }

    private static void escolherParcelas(double valorEmprestimo){
        int quantidadeParcelas;
        System.out.println("Deseja pagar seu empréstimo em quantas parcelas?");
        System.out.println("[1]- 1 parcela: " + String.format("R$ %.2f", valorEmprestimo * 1.2) +
                           "\n[2]- 2 parcelas:" + String.format("R$ %.2f", (valorEmprestimo * 1.4) / 2) +
                           "\n[3]- 3 parcelas:" + String.format("R$ %.2f", (valorEmprestimo * 1.5) / 3) +
                           "\n[4]- 4 parcelas:" + String.format("R$ %.2f", (valorEmprestimo * 1.6) / 4) +
                           "\n[5]- 5 parcelas:" + String.format("R$ %.2f", (valorEmprestimo * 1.7) / 5) +
                            "\n[0]- Cancelar operação.");
        Scanner sc = new Scanner(System.in);
        quantidadeParcelas = sc.nextInt();
        boolean valido = false;
        do{
            switch (quantidadeParcelas){
                case 1:
                    parcelas.add(String.format("R$ %.2f", valorEmprestimo * 1.2)
                                + " referente ao empréstimo de " + valorEmprestimo);
                    valorDevolver.add(valorEmprestimo);
                    valorParcelas.add(valorEmprestimo * 1.2);
                    valido = true;
                    break;

                case 2:
                    for(int i = 0; i < 2; i++){
                        parcelas.add(String.format("R$ %.2f", (valorEmprestimo * 1.4) / 2)
                                + String.format(" referente a %d", i+1)
                                + "º parcela do empréstimo de " + valorEmprestimo);
                        valorDevolver.add(valorEmprestimo / 2);
                        valorParcelas.add((valorEmprestimo * 1.4) / 2);
                    }
                    valido = true;
                    break;
                case 3:
                    for(int i = 0; i < 3; i++){
                        parcelas.add(String.format("R$ %.2f", (valorEmprestimo * 1.5) / 3)
                                + String.format(" referente a %d", i+1)
                                + "º parcela do empréstimo de " + valorEmprestimo);
                        valorDevolver.add(valorEmprestimo / 3);
                        valorParcelas.add((valorEmprestimo * 1.5) / 3);
                    }
                    valido = true;
                    break;
                case 4:
                    for(int i = 0; i < 4; i++){
                        parcelas.add(String.format("R$ %.2f", (valorEmprestimo * 1.6) / 4)
                                + String.format(" referente a %d", i+1)
                                + "º parcela do empréstimo de " + valorEmprestimo);
                        valorDevolver.add(valorEmprestimo / 4);
                        valorParcelas.add((valorEmprestimo * 1.6) / 4);
                    }
                    valido = true;
                    break;
                case 5:
                    for(int i = 0; i < 5; i++){
                        parcelas.add(String.format("R$ %.2f", (valorEmprestimo * 1.7) / 5)
                                + String.format(" referente a %d", i+1)
                                + "º parcela do empréstimo de " + valorEmprestimo);
                        valorDevolver.add(valorEmprestimo / 5);
                        valorParcelas.add((valorEmprestimo * 1.7) / 5);
                    }
                    valido = true;
                    break;
                case 0:
                    System.out.println("\033[31mOperação cancelada!\033[m");
                    operacoes();
                    break;
                default:
                    System.out.println("Escolha uma opção válida!");
            }
        } while(!valido);
    }

    protected static void extratoConta () {
        System.out.println("Olá" + "\nSeu Extrato:");
        System.out.println("====================================================");
        extratoCredito();
        System.out.println("----------------------------------------------------");
        extratoDebito();
        System.out.println("----------------------------------------------------");
        extratoEmprestimo();
        System.out.println("====================================================");

    }
    private static void pagarEmprestimos(){
        if(!parcelas.isEmpty()){
            System.out.println("Escolha qual parcela você deseja pagar.");
            for(int i = 0; i < parcelas.size(); i++){
                System.out.println(String.format("[%d]", i) + "- " + parcelas.get(i));
            }
            Scanner sc1 = new Scanner(System.in);
            int escolha;
            boolean valido = false;
            double valorPagar = 0;
            do{
                escolha = sc1.nextInt();
                try {
                    valorPagar = valorParcelas.get(escolha);
                    valido = false;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Escolha uma opção válida");
                    valido = true;
                }
            }while(valido);

            if(valorPagar != 0 && valorPagar <= getSaldo()){
                debitarValor(valorPagar);
                parcelas.remove(escolha);
                setEmprestimoEmpresa(getEmprestimoEmpresa() + valorDevolver.get(escolha));
                valorDevolver.remove(escolha);
                valorParcelas.remove(escolha);

            } else {
                System.out.println("\033[31mVocê não tem limite suficiente para realizar a operação\033[m");
                operacoes();
            }
        } else {
            System.out.println("\033[31mVocê ainda não tem empréstimos contratados.\033[m");
        }
    }

    public static void operacoes(){
        System.out.println("Qual operação você deseja realizar?");

        System.out.println("""
                [1] Débito
                [2] Crédito
                [3] Empréstimo
                [4] Saldo
                [5] Extrato das movimentações
                [6] Pagar empréstimos
                [7] Solicitar limite de empréstimo
                [8] Sair""");
        		
        Scanner scan1 = new Scanner(System.in);
        boolean valido = false;
        int operacaoEscolhida = scan1.nextInt();
        do{
            switch (operacaoEscolhida){
                case 1:
                    System.out.println("Digite o valor que você deseja debitar da sua conta");
                    double valorDebito = scan1.nextDouble();
                    debitarValor(valorDebito);
                    System.out.println("Seu saldo atual é de: " + String.format("R$ %.2f", getSaldo()));
                    continuarOperacoes();
                    break;
                case 2:
                    System.out.println("Digite o valor que você deseja creditar na sua conta");
                    double valorCredito = scan1.nextDouble();
                    creditarValor(valorCredito);
                    System.out.println("Seu novo saldo é de: " + String.format("R$ %.2f", getSaldo()));
                    continuarOperacoes();
                    break;
                case 3:
                    System.out.println("Seu limite de empréstimo é de " +
                                        String.format("R$ %.2f", getEmprestimoEmpresa()));
                    System.out.println("Digite o valor do empréstimo que você deseja solicitar");
                    double valorEmprestimo = scan1.nextDouble();
                    pedirEmprestimo(valorEmprestimo);
                    continuarOperacoes();
                    break;
                case 4:
                    System.out.println("Seu saldo atual é de: " + String.format("R$ %.2f", getSaldo()));
                    continuarOperacoes();
                    break;
                case 5:
                    extratoConta();
                    continuarOperacoes();
                    break;
                case 6:
                    pagarEmprestimos();
                    continuarOperacoes();
                    break;
                case 7:
                    System.out.println("Seu limite de empréstimo é de " +
                            String.format("R$ %.2f", getEmprestimoEmpresa()));
                    break;
                case 8:
                    Menu.menuInicial();
                    break;
                default:
                    System.out.println("Por favor, digite uma opção válida");
            }
        } while(valido);


    }

    private static void continuarOperacoes(){
        boolean valido = false;
        do {
            System.out.println("""
                    Deseja realizar mais alguma operação?
                    [1] Sim
                    [2] Não""");
            int escolha = (new Scanner(System.in).nextInt());
            switch (escolha){
                case 1:
                    operacoes();
                    break;
                case 2:
                    Menu.menuInicial();
                    break;
                default:
                    System.out.println("Escolha uma opção válida");

            }
        } while(!valido);
    }
}