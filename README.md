# Ta Rodando Bank G3.


Projeto na linguagem Java, desenvolvido como critério de avaliação para o programa de formação {U.Code} financiado pela **Empiricus**  em parceria com **a Digital House.**

O projeto consiste no desenvolvimento de um sistema bancário, focado nos conceitos trabalhados em aula.

## Requisitos
Criaçao de uma classe Conta como uma classe abstrata mãe, e a implementação de  suas classes estendidas com as seguintes regras.

> Classe Conta Poupança

Comparar se a data informada pelo usuario é a mesma data do aniversário, se for corrigir o valor em 0,05%  saldo = (saldo * 0.05)+saldo> Classe Conta Corrente.

> Classe Conta Corrente

Solicitar ao final dos 10 movimentos ou ao pedido de sair se o mesmo deseja solicitar cheque, limitando em apenas 3 cheques. cada talão debita a conta em R$ 30.00.

> Classe Conta Especial

Usar o limite do saldo se no momento do movimento o saldo fica negativo, ajusta no saldo e diminuir no limite.Limite de R$ 1.000.

> Classe Conta Empresarial

Solicitar a qualquer tempo ou após os 10 movimentos o uso do emprestimo (limitado a R$ 10.000) levando o valor para o saldo.

> Classe Conta Estudantil

Solicitar a qualquer tempo ou após os 10 movimentos o uso do emprestimo estudantil (limitado a R$ 5.000) levando o valor para o saldo.

## Desenvolvimento

Há 7 classes no projeto, sendo elas: classe Conta, classe Menu, classe Principal, classe Cliente, classe Conta Poupança, classe Conta Corrente, classe Conta Empresarial, classe Conta Estudantil.

### Principal

A classe principal foi desenvolvida para chamada de métodos inicias e ser o ponto de entrada do projeto.

### Conta

A classe abstrata conta possuí os atributos número, saldo, ativo, e senha. O método construtor com os parametros numero, senha e ativo bem como o gets e sets de todos os atributos.

### Conta Poupança - Ludmila

A classe `ContaPoupanca` possui 7 métodos, e também é uma herança da classe `Conta`,  o método especifico e requisito da classe é o `correcaoAniversario` , o qual realiza o calculo de bônus no caso do dia atual bater com o dia de abertura da conta.
A classe ainda possui o método construtor, o qual recebe numero, senha e ativo como parâmetro.

#### inicarConta
Na sequencia podemos encontrar os métodos Getters e Setters e o método `iniciarConta` o qual faz a chamada de outros métodos da classe em uma ordem especifica para o seu funcionamento.

#### gerarInformacoes
As informações de saldo, data e hora estão encapsuladas no método `gerarInformacoes` para melhor organização do código na classe.

#### correçãoAniversario
Após podemos observar o método `correcaoAniversario`, o qual realiza uma correção de 0.05% caso o dia de criação da conta seja o mesmo do dia atual, caso contrario a correção será de 0.04% com base no saldo atual.

#### movimentarConta
No método `movimentarConta` é exibido um menu com as possíveis operações, sendo elas: débito, crédito, empréstimo, saldo, extrato de movimentações e voltar ao menu inicial. No referido método é possível observar o uso da estrutura `if/else` aninhada a uma estrutura `do/while` para realização de diversas operações em sequencia.
#### debitarValor
No método `debitarValor` uma estrutura if seguida de um `if/else` recebe o valor do débito, certificando que o mesmo não é negativo e faz a transação para a conta atual, ao final retornando uma confirmação de sucesso da operação, bem como, a data e a hora da operação, o valor que foi  debitada e o saldo atual.
####creditarValor
No método `creditarValor` uma estrutura if certifica que nenhum valor nativo será creditado, ao final retornando uma confirmação de sucesso da operação, bem como, a data e a hora da operação, o valor que foi creditado e o saldo atual.

#### extratoCredito, extratoDebito e extratoConta.
Os três ultimos métodos referentes ao extrato foram desenvolvidos pelo aluno Emerson Mendes e disponibilizado para todos os membros do grupo, métodos `extratoCredito`, `extratoDebito` e `extratoConta`.

### Conta Corrente - Sávia

A classe ContaCorrente consiste em uma classe filha que estende atributos e métodos da classe Conta.

Um dos principais métodos é operacao() que possibilita realizar movimentações na conta.

Ela apresenta as opções:

1-Consultar saldo chama o método informaSaldo().
2-Crédito em Conta chama o método creditarValor().
3-Débito em Conta chama o método debitou().
4-Extrato da conta chama o método extratoConta().
5-Sair chama o método pedirTalao().

A classe possui uma variável específica contadorTalao do tipo inteiro que armazena o número de talões de cheque disponíveis para serem contratados.
A partir dessa variável é possível implementar 3 métodos.
-pedirTalao() é um método chamado ao completar 10 movimentações na conta ou ao pedido de Sair. Caso possua saldo disponível, é possível contratar até 3 talões ao custo de 30 reais por talão de cheque contratado.
-quantidadeTaloes() é um método que mostra em console a quantidade de talões disponíveis para serem contratados.
-atualizaTaloes() é um método que atualiza o valor da variável contadorTalao.

### Conta Empresarial - Luiz Fernando

A classe ContaEmpresarial consiste em uma classe filha que estende atributos e métodos da classe Conta.

Um dos principais métodos é operacoes() que possibilita realizar movimentações na conta.

Ela apresenta as opções:

1- Débito: chama o método debitarValor().
2- Crédito: chama o método creditarValor().
3- Empréstimo: chama o método pedirEmprestimo().
4- Saldo: imprime o saldo atual através do método getSaldo().
5- Extrato de movimentações: chama o método extratoConta().
6- Pagar empréstimo: chama método pagarEmprestimos().
7- Solicitar limite de empréstimo: imprime o limite atual de empréstimos através do método getEmprestimoEmpresa().
8- Sair: chama o método menuPrincipal() da classe Menu.

O método pagarEmprestimos dá 5 opções de parcelamento pro cliente que solicitar empréstimo com acréscimo de juros em cada opção através do método escolherParcelas()
O método escolherParcelas() utiliza-se de três listas do tipo ArrayList para armazenar:
- as opções das parcelas que devem ser pagas;
- os valores com acréscimo de juros que devem ser debitados do saldo;
- os valores que creditados no limite de empréstimo após o pagamento das parcelas.


### Conta Estudantil - Emerson
### Menu

Optamos pela segregação de alguns métodos em classes específicas, como por exemplo a classe Menu.

	Essa consiste na junção de todos os métodos relacionados aos menus apresentados no projeto: menuInicial, menuBuscarConta, menuCriarConta, menuDadosClientes, menuSelecionarContas. 

### Cliente 
Na classe Cliente implementamos os atributos nome, cpf, dataNascimento, salario e CNPJ. A classe possuí um construtor além dos métodos Getters e Setters.
Além disso, os métodos  validarNome, validarCPF, validarData, validarCNPJ e validarSenha.
Esses são utilizados como validadores para algumas entradas solicitadas, assegurando uma maior segurança do sistema, e protegendo contra possíveis acionamento de “exceptions”.

## Utilização do sistema
Após acessar a classe Principal.java, uma lista de opções aparecerá no console. Escolha uma das opções e digite o número correspondente.

Caso você acesse a opção "Entrar" correspondente ao número 1, um novo menu aparecerá no console solicitando a escolha de CPF ou CNPJ e senha para acessar a conta.

Caso você acesse a opção "Abrir conta" correspondente ao número 2, um novo menu aparecerá no console solicitando a escolha entre [1]-Conta poupança, [2]-Conta corrente, [3]-Conta especial, [4]-Conta empresarial, [5]-Conta estudantil.

## UML diagrams
<p align="center">
  <img src="https://raw.githubusercontent.com/Ludmilact/projeto-banco-empiricus/main/assets/UML.png" alt="UML" />
</p>
<br>