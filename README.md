# project-bills

### Sobre o Projeto
O projeto consiste no gerenciamento de contas financeiras, onde as principais regras são aplicação de multas em contas com atrasos.

### Frameworks do Projeto
* Spring Boot
* Liquibase
* Maven 
* JUnit 4

### Banco de Dados do Projeto
* MySQL

### Instrução de Execução do Container

Foi utilizado o docker para executar um container do banco de dados.
Abaixo segue o comando para subir o container, a partir da raiz do projeto.

```
docker-compose up -d 

```
### Exemplo do Json do Postman
##### Cadastrar conta
* /api/v1/bills
* POST
``` javascript
{
    "name": "maria",
    "billAmount": 1500,
    "invoiceDate": "2020-04-19",
    "paymentDate": "2020-04-19"
}
```
##### Listar todas as contas
* /api/v1/bills/all
* GET
``` javascript
[
    {
        "name": "maria",
        "billAmount": 1500,
        "invoiceDate": "2020-04-04",
        "paymentDate": "2020-04-14",
        "daysOfDelay": 12,
        "correctedValue": 1576.8
    }
]
```
