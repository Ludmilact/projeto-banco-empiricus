/**
 * Classe desenvolvida por Sávia.
 */
package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ContaCorrente extends Conta{

static int contadorTalao;


static ArrayList<Double> Debitos = new ArrayList<>();
static ArrayList<Double> Creditos = new ArrayList<>();
static ArrayList<String> DHDebitos = new ArrayList<>();
static ArrayList<String> DHCreditos = new ArrayList<>();
static ArrayList<String> DHTaloesContratados  = new ArrayList<>();
static ArrayList<Integer> TaloesContratados = new ArrayList<>();
	
	public ContaCorrente(int numero, String senha, boolean ativo) {
		super(numero, senha, ativo);
		setContadorTalao(3);
		mensagemInicial();
	}
	static DateTimeFormatter formData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	//getters e setters
	public int getContadorTalao() {
		return contadorTalao;
	}

	public void setContadorTalao(int contadorTalao) {
		this.contadorTalao = contadorTalao;
	}
	
	public void mensagemInicial() {
        System.out.println("Você acessou a CONTA CORRENTE.");
        informaSaldo();
    }
	
	//método que deposita valores na conta
	public static void creditarValor() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Informe valor a ser creditado: ");
		double valorCreditado = sc.nextDouble();
		
		while (valorCreditado < 0) {
			System.out.println("Valor inválido. Tente novamente.\nInforme valor a ser creditado: ");
			valorCreditado = sc.nextDouble();
		}	
		saldo = saldo + valorCreditado;
		System.out.println();
		System.out.println("Crédito adicionado.");
		informaSaldo();
        DHCreditos.add(formData.format(LocalDateTime.now()));
		Creditos.add(valorCreditado);
	}

	
	//método que debita valores da conta e verifica se foi feito o débito
	public static boolean debitou() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Informe valor a ser debitado: ");
		
		double valorDebitado = sc.nextDouble(); 
		
		while (valorDebitado < 0) {
			System.out.println("Valor inválido. Tente novamente.\nInforme valor a ser debitado: ");
			valorDebitado = sc.nextDouble();
		}	
		
		if (saldo >= valorDebitado) {
			saldo = saldo - valorDebitado;
			System.out.println("Débito realizado.");
			informaSaldo();
            DHDebitos.add(formData.format(LocalDateTime.now()));
			Debitos.add(valorDebitado);
			return true;
		
		}else {
			System.out.println("Saldo insuficiente.\n");
			return false;
		}	
	}
	
	
	//método das operações de movimentação da conta
	public static void operacao() {
		
		int contador = 0;
		int opcao;
		Scanner entrada = new Scanner(System.in);
		do {
			//menu de operações dentro da conta corrente
			System.out.println("\nSelecione a operação desejada:");
			System.out.println("[1] - CONSULTAR SALDO");
			System.out.println("[2] - CRÉDITO EM CONTA");
			System.out.println("[3] - DÉBITO EM CONTA");
			System.out.println("[4] - EXTRATO DA CONTA");
			System.out.println("[5] - SAIR");
			
			opcao = entrada.nextInt();

			if (opcao == 1){ 
				informaSaldo();
				System.out.println();
				
			} else if (opcao == 2) {
				System.out.println();
				creditarValor();
				System.out.println();
				contador++;
				
			} else if (opcao == 3) {
				
				if(debitou())
					contador++;
				System.out.println();
				
			}else if (opcao == 4) {
				 extratoConta();
				 continuarOperacao();
				 break;
			}
			else if(opcao == 5){
				System.out.println();
				//método para fazer oferta de cheque ao sair da aplicação
				pedirTalao();
		        break;
			} else {
				System.out.println();
				System.out.println("Opção inválida");
				
			} if(contador == 10) {
				//método para fazer oferta de cheque ao	completar 10 movimentos
				pedirTalao();
		        break;
			}
			
		}
		while (true);		
		
		
	}		
	
	//método que pergunta se o cliente deseja continuar movimentando a conta
	public static void continuarOperacao() {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\nDeseja realizar outra operação? \n[1] - SIM \n[2] - NAO");
			
			int entrada = sc.nextInt();
			
			if (entrada == 1) {
				operacao();
				break;
			} else if (entrada == 2) {
				Menu.menuInicial(); //retornar para o menu de escolha de conta
				break;
			}else {
				System.out.println("Opção inválida. Tente novamente.");	
				System.out.println();
				
			}
		}while (true);
		
	}
	
	
	//método que possibilita o cliente contratar talao de cheque
	public static void pedirTalao() {
		
		double taxa = 30;
		double totalTaxa;
		Scanner sc = new Scanner(System.in);

		System.out.println("DESEJA SOLICITAR CHEQUE? ");
		quantidadeTaloes();
		System.out.println("*Serviço sujeito a cobrança de taxa.");
		System.out.println("[1] - SIM \n[2] - NÃO");

		int aceitaContratar = sc.nextInt();
		
		while (aceitaContratar < 0 || aceitaContratar > 2) {
				System.out.println("Opção inválida. Tente novamente.\nDeseja solicitar cheque? ");
				quantidadeTaloes();
				System.out.println("*Serviço sujeito a cobrança de taxa.");
				System.out.println("[1] - SIM \n[2] - NÃO");
				aceitaContratar = sc.nextInt();
		}
		
		if(contadorTalao == 0) {
			System.out.println("Não há mais cheques disponpíveis para a conta.");
			continuarOperacao();
		}
		if (aceitaContratar == 1) {
			if (saldo == 0) {
				informaSaldo();
				System.out.println("Saldo insuficiente.");
				continuarOperacao();
			} else {
				System.out.println("Quantos talões deseja solicitar? ");
				quantidadeTaloes();
				int taloes = sc.nextInt();

				while (taloes <= 0 || taloes > contadorTalao) {
					System.out.println("Quantidade inválida. Tente novamente.\nQuantos talões deseja solicitar: ");
					quantidadeTaloes();
					taloes = sc.nextInt();
				}

				totalTaxa = taloes * taxa;

				if (totalTaxa > saldo) {
					informaSaldo();
					System.out.println("Saldo insuficiente.");
					continuarOperacao();

				} else if (taloes <= contadorTalao) {

					System.out.printf("Valor da Taxa de Contratação: R$ %.2f%n%n", totalTaxa);
					System.out.println("Deseja prosseguir? \n[1] - SIM \n[2] - NÃO ");
					int prossegue = sc.nextInt();

					while (prossegue <= 0) {
						if (contadorTalao == 0) {
							System.out.println("Não há mais cheques disponpíveis para a conta.");
							continuarOperacao();
						}
						System.out.println(
								"Opção inválida. Tente novamente.\nDeseja prosseguir? \\n[1] - SIM \\n[2] - NÃO ");
						prossegue = sc.nextInt();
					}

					if (prossegue == 1) {
						System.out.println("Solicitação concluída.");
						saldo = saldo - totalTaxa;
						DHDebitos.add(formData.format(LocalDateTime.now()));
						Debitos.add(totalTaxa);
						DHTaloesContratados.add(formData.format(LocalDateTime.now()));
						TaloesContratados.add(taloes);
						informaSaldo();
						atualizaTaloes(taloes);
						quantidadeTaloes();
						continuarOperacao();

					} else if (prossegue == 2) {
						System.out.println("Operação cancelada.");
						System.out.println();
						continuarOperacao();
					}
				
				} else {
					System.out.println("Não há mais cheques disponpíveis para a conta.");
					continuarOperacao();
				}
			}
		}else if (aceitaContratar == 2) {
			System.out.println("Operação cancelada.");
			continuarOperacao();
		}
			
		
	}
		
	// método que mostra o saldo atual da conta
	public static void informaSaldo() {
		System.out.printf("Saldo atual: R$ %.2f%n", getSaldo());
		System.out.println(formData.format(LocalDateTime.now()));
	}
			
	
	//método que mostra a quantidade de talões que pode contratar
	public static void quantidadeTaloes(){
		System.out.println("Talões disponíveis para contratacao: " + contadorTalao);
	}
	
	//método que atualiza a vvariável contadorTaloes
	public static int atualizaTaloes(int taloes) {
		contadorTalao = contadorTalao - taloes;
		return contadorTalao;
	}
	
	protected static void extratoTaloes () {
		System.out.println("Taloes contradatos: \n");
		if(TaloesContratados.isEmpty() != true ) {
				for(int i = 0; i < TaloesContratados.size(); i++) {
				System.out.println("Data da Solicitação: " + DHTaloesContratados.get(i));
				System.out.println("Taloes Contratados: " + TaloesContratados.get(i));
				System.out.println("");
			}
		}
		else {
			System.out.println("\033[31mVocê não possui taloes contratados!\033[m");
			System.out.println();
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
	
	protected static void extratoConta () {
		System.out.println("\nExtrato das Movimentações:");
		System.out.println("====================================================");
		extratoCredito();
		System.out.println("----------------------------------------------------");
		extratoDebito();
		System.out.println("----------------------------------------------------");
		extratoTaloes();
		System.out.println("----------------------------------------------------");
	
}


}
