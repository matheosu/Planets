# Planets
Este projeto é uma implementação do desafio da B2W/BIT. O projeto é uma API REST de Planetas.

## Utilizações
* Java 8 & Java EE 7
* Maven (https://maven.apache.org)
* Wildfly-Swarm (http://wildfly-swarm.io/)
* MongoDB (https://www.mongodb.com)

## Configurações
O arquivo project-default.yml define algumas  configurações para a comunicação do MongoDB, como host, porta e nome do banco de dados.

## Execução
Toda e qualquer execução será exposto a porta 8080 e o contexto da aplicação será /api. Caso execute localmente, o acesso a API será assim: http://localhost:8080/api/. O Swagger(planets.yml) contém a descrição dos Resources e seus respectivos objetos.

### Execução via Maven
Para executar o projeto diretamente, antes faça suas alterações no **project-default.yml** para o apontamento do MongoDB.
Quando estiver configurado, execute o comando:
``` mvn wildfly-swarm:run ```

### Execução via Docker
#### Pré-requisitos
* Docker (https://docs.docker.com/install/)
* Docker Compose (https://docs.docker.com/compose/install/)

*Como descrito no docker-compse do projeto, será iniciado um container com a API de Planetas e um MongoDB.*

Para a exeção via docker, primeiro faça a geração da imagem do projeto para o seu docker-registry, através do comando:
``` mvn clean package -Pdocker ``` 

Após a execução do comando, vai existir uma nova imagem no seu docker-registry chamada **planets**, sendo assim inicie os containers através do Docker Compose assim como o comando:
``` docker-compose up ```

*Caso não queria que os containers fiquem presos execute com o parametro -d ``` docker-compose up -d```*

