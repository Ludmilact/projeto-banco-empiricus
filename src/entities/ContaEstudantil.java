/**
 * Classe desenvolvida por Emerson.
 */
package entities;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ContaEstudantil extends Conta {

    public ContaEstudantil(int numero,String senha, boolean ativo) {
        super(numero, senha, ativo);
    }
 
    
	
	private static double saldo;
	private static double emprestimoEstudantil = 5000;
	private static String nomeTitular = "Emerson Mendes";
	private String cpfTitular = "12345678900";

	static DateTimeFormatter formData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	static ArrayList<Double> Debitos = new ArrayList<>();
	static ArrayList<Double> Creditos = new ArrayList<>();
	static ArrayList<Double> Emprestimos = new ArrayList<>();
	static ArrayList<Double> JurosEmprestimos = new ArrayList<>();
	static ArrayList<String> DHDebitos = new ArrayList<>();
	static ArrayList<String> DHCreditos = new ArrayList<>();
	static ArrayList<String> DHEmprestimos = new ArrayList<>();

	
	static Scanner entrada = new Scanner(System.in);

	public static void startConta() {
		
for(int i = 0;;) {
	int opcao01, sair = 0, continuar = 1, credito = 1, debito = 2, emprestimo = 3, extrato = 4;
	while(i == 10) {
		System.out.println("Gostaria de fazer um Emprestimo estudantil? \nDigite[1] para continuar: \nDigite[0] para sair: ");
		opcao01 = entrada.nextInt();
		if(opcao01 == continuar) {
			emprestimo();
			}
		else if(opcao01 == sair) {
			break;
		}
		else if(opcao01 != continuar && opcao01 != sair) {
			System.out.println("\033[31mOpção invalida, Operação cancelada!\033[m");
			continue;
		}
		i++;
	}
			
	System.out.println();
	System.out.println("Ola " + getNomeTitular() + " [Conta Estudantil]");
	System.out.println("Seu saldo atual é: " + String.format("R$ %.2f", saldo));
	System.out.println("Que operacão o Sr(a) gostaria de fazer ? \nDigite[1] para Credito \nDigite[2] para Debito \nDigite[3] para Emprestimo Estudantil \nDigite[4] para extrato \nDigite[0] para sair");
	opcao01 = entrada.nextInt();
			

	if(opcao01 == sair) {
	System.out.println("Ola " + getNomeTitular());
	System.out.println("Seu saldo atual é: " + String.format("R$ %.2f", saldo));
	System.out.println("Gostaria de fazer um Emprestimo estudantil? \nDigite[1] para continuar: \nDigite[0] para sair: ");
	int opcaoEmprestimo = entrada.nextInt();
				
		if(opcaoEmprestimo == continuar) {
			emprestimo();
		}
		else if(opcaoEmprestimo != continuar && opcaoEmprestimo != sair) {
			System.out.println("\033[31mOpção invalida, Operação cancelada!\033[m");
		}
		else if(opcaoEmprestimo == sair) {
			Menu.menuInicial();
		}
	}	
	else if(opcao01 == credito) {
		credito();
	}
	else if(opcao01 == debito) {
		debito();
	}
	else if(opcao01 == emprestimo) {
		emprestimo();
	}
	else if(opcao01 == extrato) {
		extratoConta();
	}		
	else {
		System.out.println();
		System.out.println("\033[31mEscolha uma opção válida!\033[m");
		System.out.println();
		continue;
	}
		i++;
}
	}
	
	
	public static void credito() {
		for(int i = 0;;) {
			int sair = 0, continuar = 1;
		System.out.println("Seu saldo atual é: " +  String.format("R$ %.2f", saldo));
		System.out.println("Informe o valor que deseja Depositar: R$");
		
		double valorCredito = entrada.nextDouble();
		
		
		System.out.println("Operação: Credito \nSaldo Atual: " + String.format("R$ %.2f", saldo) + "\nValor a ser Depositado: " + String.format("R$ %.2f", valorCredito)  + "\nDeseja prosseguir? \nDigite[1] para continuar: \nDigite[0] para cancelar:\n");
		
		int aceiteOperacao = entrada.nextInt();
			if(aceiteOperacao == continuar) {
				creditarValor(valorCredito);
			}
			else if(aceiteOperacao != continuar && aceiteOperacao != sair) {
				System.out.println("\033[31mOpção invalida, Operação cancelada!\033[m");
				continue;
			}
			else if(aceiteOperacao == sair) {
				continue;
			}
			break;
		}
		
	}
	
	public static void debito() {
		for(int i =0;;) {
			int sair = 0, continuar = 1;
		System.out.println("Seu saldo atual é: R$" + saldo);
		System.out.println("Informe o valor que deseja Sacar: R$");
		
		double valorDebito = entrada.nextDouble();
		
		System.out.println("Operação: Debito \nSaldo Atual: R$" + saldo + "\nValor a ser Sacado: R$" + valorDebito + "\nDeseja prosseguir? \nDigite[1] para continuar: \nDigite[0] para cancelar:\n");
		
		int aceiteOperacao = entrada.nextInt();
			if(aceiteOperacao == continuar) {
				debitarValor(valorDebito);
			}
			else if(aceiteOperacao != continuar && aceiteOperacao != sair) {
				System.out.println("\033[31mOpção invalida, Operação cancelada!\033[m");
				continue;
			}
			else if(aceiteOperacao == sair) {
				continue;
			}
			break;
		}
	}

	public static void emprestimo() {
		for(int i = 0;;) {
			int sair = 0, continuar = 1;
			System.out.println("Seu saldo atual é: " + String.format("R$ %.2f", saldo));
			System.out.println("Valor disponivel para emprestimo é: " + String.format("R$ %.2f", emprestimoEstudantil));
			System.out.println("Digite o valor que deseja de Emprestimo: R$");
			
			double valorEmprestimo = entrada.nextDouble();
			double jurosEmprestimo = valorEmprestimo + valorEmprestimo *13/100;
			double valorJuros = valorEmprestimo *13/100;
				
			System.out.println("Operação: Emprestimo \nSaldo Atual: " + String.format("R$ %.2f", saldo) + "\nValor do Emprestimo: " + String.format("R$ %.2f", valorEmprestimo)  + "\nValor de juros: " + String.format("R$ %.2f", valorJuros) + "\nValor a pagar sobre o Emprestimo: " + String.format("R$ %.2f", jurosEmprestimo) + "\nDeseja prosseguir? \nDigite[1] para continuar: \nDigite[0] para cancelar:\n");
				
			int aceiteOperacao = entrada.nextInt();
				if(aceiteOperacao == continuar) {
					usarEmprestimo(valorEmprestimo);
				}
				else if(aceiteOperacao != continuar && aceiteOperacao != sair) {
					System.out.println("\033[31mOpção invalida, Operação cancelada!\033[m");
					continue;
				}
				else if(aceiteOperacao == sair) {
					break;
				}
				break;
			}
	}
		
	public static void creditarValor(double valorCreditado) {
	saldo += valorCreditado;
	System.out.println("\033[32mDeposito efetuado com sucesso\033[m" + "\n\nSaldo atual: " + saldo); 
	System.out.println(formData.format(LocalDateTime.now()));
	DHCreditos.add(formData.format(LocalDateTime.now()));
	Creditos.add(valorCreditado);
	}

	protected static void debitarValor(double valorDebitado) {
		if(valorDebitado > saldo) {
			System.out.println("\033[31mVoce não tem limite disponivel para transação. Operação cancelada!\033[m");
		}
		else {
			saldo -= valorDebitado;
		System.out.println("\033[32mSaque efetuado com sucesso\033[m" + "\n\nSaldo atual: " + saldo); 
		System.out.println(formData.format(LocalDateTime.now()));
		DHDebitos.add(formData.format(LocalDateTime.now()));
		Debitos.add(valorDebitado);	
		}
	}

	private static void usarEmprestimo(double valorEmprestimo) {
	if(valorEmprestimo > emprestimoEstudantil) {
		System.out.println("\033[31mValor de emprestimo não disponivel no momento. Operação cancelada!\033[m");
	}
	else {
			saldo += valorEmprestimo;
	emprestimoEstudantil -= valorEmprestimo;
	System.out.println("\033[32mEmprestimo efetuado com sucesso\033[m" + "\n\nSaldo atual: " + saldo); 
	System.out.println(formData.format(LocalDateTime.now()));
	DHEmprestimos.add(formData.format(LocalDateTime.now()));
	Emprestimos.add(valorEmprestimo);
	JurosEmprestimos.add(valorEmprestimo * 13 /100);
	}
	}
	
	protected static void extratoCredito () {
	System.out.println("Creditos\n");
	if(Creditos.isEmpty() != true) {
		for(int i = 0; i < Creditos.size(); i++) {
			System.out.println("Data da Solicitacao: " + DHCreditos.get(i));
			System.out.println("Valor do Credito: " + String.format("R$ %.2f", Creditos.get(i)));
			System.out.println();
			}
	}
	else {
		System.out.println("\033[31mVoce não possui transacões de Credito!\033[m");
		System.out.println();
	}
	}

	protected static void extratoDebito () {
	System.out.println("Debitos\n");
	if(Debitos.isEmpty() != true) {
		for(int i = 0; i < Debitos.size(); i++) {
			System.out.println("Data da Solicitacao: " + DHDebitos.get(i));
			System.out.println("Valor do Debito " + String.format("R$ %.2f", Debitos.get(i)));
			System.out.println();
		}
	}
	else {
		System.out.println("\033[31mVoce não possui transacões de Debito!\033[m");
		System.out.println();
	}
	}

	protected static void extratoEmprestimo () {
	System.out.println("Emprestimos\n");
	if(Emprestimos.isEmpty() != true ) {
			for(int i = 0; i < Emprestimos.size(); i++) {
			System.out.println("Data da Solicitacao: " + DHEmprestimos.get(i));
			System.out.println("Valor do Emprestimo: " + String.format("R$ %.2f", Emprestimos.get(i)) + "\nJuros sobre o Emprestimo: " + String.format("R$ %.2f",JurosEmprestimos.get(i)) + "\nValor total a pagar: " + String.format("R$ %.2f",Emprestimos.get(i)+JurosEmprestimos.get(i)));
			System.out.println();
		}
	}
	else {
		System.out.println("\033[31mVoce não possui transacões de Emprestimo!\033[m");
		System.out.println();
		
	}
	}
	
	protected static void extratoConta () {
	System.out.println("Seu Extrato");
	extratoCredito();
	extratoDebito();
	extratoEmprestimo();
	}
	
	public static String getNomeTitular() {
		return nomeTitular;
	}

	public void setNomeTitular(String nomeTitular) {
		ContaEstudantil.nomeTitular = nomeTitular;
	}


	public String getCpfTitular() {
		return cpfTitular;
	}


	public void setCpfTitular(String cpfTitular) {
		this.cpfTitular = cpfTitular;
	}

		
	}

