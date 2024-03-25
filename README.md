<a name="readme-top"></a>

# Introdução

O Microserviço Pagamento Service realiza a gestão de pagamentos. Ele simula a utilização de um Serviço de Pagamento Externo, como por exemplo o PagSeguro, para processar transações financeiras de forma segura e eficiente.

No Pagamento Service, temos o endpoint 'processar pagamento', destinado exclusivamente ao Sistema de Pagamento Externo (webhook). </br>
Após a criação da solicitação de pagamento, vamos receber uma requisição neste endpoint, e a partir daí, Pagamento Service irá acessar o Sistema de Pagamento Externo (PagSeguro) para obter o status do pagamento.</br>
Logo após irá obter os produtos e suas quantidades acessando o Carrinho Service.</br>
Em seguida será verificado o status do pagamento. </br>

*Caso o status do pagamento seja APROVADO:*

* O Pagamento Service acessará o Estoque Service para atualizar o estoque.
* Realizará o fechamento do pedido no Pedido Service.
* Salvará o status do pagamento como "PAGO" no banco de dados.


*Caso o pagamento seja REPROVADO:*

* O Pagamento Service irá se comunicar com o Produto Service para cancelar a reserva dos produtos.
* Efetuará o cancelamento do pedido no Pedido Service.
* Atualizará o status do pagamento para "CANCELADO" e salva no banco de dados.

Este processo garante a integridade dos dados e o gerenciamento adequado dos pedidos, independentemente do resultado da transação. O Pagamento Service desempenha um papel fundamental na conclusão bem-sucedida das transações e no tratamento de exceções de forma eficaz.



## Sumário
* [Instruções](#instrucoes)
* [Funcionalidades de Pagamento Service](#funcionalidades-de-pagamento-service)
* [Ilustração do MS Pagamento Service](#ilustração-do-ms-pagamento-service)


## Instruções

- Maven: Para build do projeto. **Para buildar:** mvn clean install
- Foi utilizado Lombok, Validation e MySql, portanto é necessário adicionar os plugins na IDE
- Antes de iniciar a instância dos Microserviços, é necessário garantir que os seguintes serviços estejam operacionais para garantir a operação adequada:</br>

	* **Service Discovery** - Inicie o Service Discovery. Execute e verifique se pelo menos uma instância do Service Discovery está operacional.</br></br>
	
	* **API Gateway** - Inicie o API Gateway. Verifique se pelo menos uma instância do API Gateway está em execução.

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

---------

### Funcionalidades de Pagamento Service

>[ Base URL: http://localhost:porta ] 

`Substitua <porta> pela porta atribuída dinamicamente pelo ambiente.`

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``POST``  
`*Para processar pagamento`

```
	/pagamentos/processar
```
<details>
  <summary>Exemplo Request:</summary>

```
curl --location 'http://localhost:9999/pagamentos/processar' \
--header 'Content-Type: application/json' \
--data '{
    "pagamentoExternoId": "37889def-1a69-4ca3-9cad-c69f94e483db"
}'
```
</details>

<details>
  <summary>Exemplo Responses:</summary>

200 - _OK_

```
```

404 - _Not Found_

```
{
    "code": "pagamento.pagamentoNaoEncontrado",
    "message": "Pagamento não encontrado."
}
```
</details>

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

---------

<a name="tecnologias"></a>
## 📍️ Tecnologias

- A API foi construída em Java 18 utilizando Spring Framework 3.2.3
- Padrão REST na construção das rotas e retornos
- SLF4J para registro de logs
- Utilização de código limpo e princípios **SOLID**
- Boas práticas da Linguagem/Framework
- Clean architecture
- Banco de Dados MySql
- Para facilitar a comunicação entre microserviços, o projeto utiliza o Spring Cloud Feign. 
- Service Discovery
- API Gateway

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

---------

### Ilustração do MS Pagamento Service

![pagamento-service.png](src%2Fmain%2Fjava%2Fdocument%2Fpagamento-service.png)