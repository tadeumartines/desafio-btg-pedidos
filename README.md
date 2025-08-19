# Desafio Técnico BTG FICC - Processamento de Pedidos

Este repositório contém a solução para o desafio técnico proposto pelo BTG Pactual FICC, que consiste em uma arquitetura de microserviços para processar pedidos de forma assíncrona e expor uma API REST para consulta dos dados.

## Arquitetura da Solução

A solução foi implementada utilizando uma arquitetura de microserviços para garantir escalabilidade, resiliência e separação de responsabilidades.

*   **`order-consumer`**: Microserviço responsável por consumir mensagens de pedidos de uma fila RabbitMQ, processá-las (calculando o valor total) e persistir os dados em um banco de dados MongoDB.
*   **`order-api`**: Microserviço que expõe uma API REST para consultar os dados dos pedidos já processados e salvos no MongoDB.
*   **`order-shared`**: Módulo Maven compartilhado que contém as entidades (DTOs) do projeto, evitando duplicação de código entre os serviços.
*   **RabbitMQ**: Atua como o message broker, desacoplando o recebimento dos pedidos do seu processamento.
*   **MongoDB**: Banco de dados NoSQL utilizado para armazenar os documentos dos pedidos.

---

## Tecnologias Utilizadas

*   **Java 17**
*   **Spring Boot 3.3.2**
*   **Apache Maven**
*   **MongoDB**
*   **RabbitMQ**
*   **Docker & Docker Compose**
*   **Git & GitHub**
*   **Docker Hub**

---

## Como Executar a Aplicação

A aplicação é totalmente containerizada e orquestrada com Docker Compose, tornando sua execução simples e rápida em qualquer ambiente que possua Docker.

### Pré-requisitos

*   [Docker](https://www.docker.com/products/docker-desktop/ ) instalado e em execução.
*   [Git](https://git-scm.com/ ) para clonar o repositório.

### Passos para Execução

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/tadeumartines/desafio-btg-pedidos.git
    cd desafio-btg-pedidos
    ```

2.  **Inicie todo o ambiente com Docker Compose:**
    *   Este comando irá baixar as imagens publicadas no Docker Hub (ou construí-las, se não encontrá-las ) e iniciar os 4 contêineres (order-api, order-consumer, rabbitmq, mongodb) em uma rede compartilhada.
    ```bash
    docker-compose up
    ```

3.  **Aguarde a inicialização:**
    *   Espere até que os logs no terminal se estabilizem e você veja as mensagens "Started OrderApiApplication" e "Started OrderConsumerApplication".

### Acessando os Serviços

*   **API REST (`order-api`):**
    *   A API estará disponível em `http://localhost:8080`.
    *   Exemplos de endpoints:
        *   `GET /v1/pedidos/{codigoPedido}/valor-total`
        *   `GET /v1/clientes/{codigoCliente}/pedidos/quantidade`
        *   `GET /v1/clientes/{codigoCliente}/pedidos`

*   **Interface de Gerenciamento do RabbitMQ:**
    *   Disponível em: `http://localhost:15672`
    *   **Login:** `admin`
    *   **Senha:** `admin`

*   **Banco de Dados MongoDB:**
    *   Disponível para conexão em: `mongodb://root:root@localhost:27017/`

---

## Publicação das Imagens

As imagens Docker para os microserviços estão publicadas no Docker Hub:

*   **Consumidor:** [hub.docker.com/r/tadeumartines/order-consumer](https://hub.docker.com/r/tadeumartines/order-consumer )
*   **API:** [hub.docker.com/r/tadeumartines/order-api](https://hub.docker.com/r/tadeumartines/order-api )

