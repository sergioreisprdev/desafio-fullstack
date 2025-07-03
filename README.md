# Desafio Técnico: API de Consulta de Créditos

Este projeto implementa uma API RESTful para consulta de créditos constituídos, utilizando **Java com Spring Boot** no backend e **Angular** no frontend. O banco de dados utilizado é o **PostgreSQL**, com gerenciamento de migrações via **Flyway**. A aplicação é totalmente containerizada usando **Docker** e **Docker Compose**.

## Sumário

1.  [Tecnologias Utilizadas](#1-tecnologias-utilizadas)
2.  [Estrutura do Projeto](#2-estrutura-do-projeto)
3.  [Pré-requisitos](#3-pré-requisitos)
4.  [Configuração do Banco de Dados](#4-configuração-do-banco-de-dados)
5.  [Executando a Aplicação Localmente](#5-executando-a-aplicação-localmente)
    * [Backend (Spring Boot)](#backend-spring-boot)
    * [Frontend (Angular)](#frontend-angular)
6.  [Executando a Aplicação via Docker Compose](#6-executando-a-aplicação-via-docker-compose)
7.  [Endpoints da API](#7-endpoints-da-api)
8.  [Documentação da API (Swagger)](#8-documentação-da-api-swagger)
9.  [Testes Automatizados](#9-testes-automatizados)
10. [Coleção Postman](#10-coleção-postman)
11. [Desafios Adicionais (Próximos Passos)](#11-desafios-adicionais-próximos-passos)

---

## 1. Tecnologias Utilizadas

* **Backend:**
    * Java 17+
    * Spring Boot 3+
    * Spring Data JPA
    * Hibernate
    * Maven
    * Flyway (Gerenciamento de Migrações de Banco de Dados)
* **Banco de Dados:**
    * PostgreSQL
* **Frontend:**
    * Angular 20+
    * TypeScript
    * HTML5
* **Containerização:**
    * Docker
    * Docker Compose

## 2. Estrutura do Projeto

A estrutura de pastas principal é organizada para facilitar a containerização com Docker Compose:

Markdown

# Desafio Técnico: API de Consulta de Créditos

Este projeto implementa uma API RESTful para consulta de créditos constituídos, utilizando **Java com Spring Boot** no backend e **Angular** no frontend. O banco de dados utilizado é o **PostgreSQL**, com gerenciamento de migrações via **Flyway**. A aplicação é totalmente containerizada usando **Docker** e **Docker Compose**.

## Sumário

1.  [Tecnologias Utilizadas](#1-tecnologias-utilizadas)
2.  [Estrutura do Projeto](#2-estrutura-do-projeto)
3.  [Pré-requisitos](#3-pré-requisitos)
4.  [Configuração do Banco de Dados](#4-configuração-do-banco-de-dados)
5.  [Executando a Aplicação Localmente](#5-executando-a-aplicação-localmente)
    * [Backend (Spring Boot)](#backend-spring-boot)
    * [Frontend (Angular)](#frontend-angular)
6.  [Executando a Aplicação via Docker Compose](#6-executando-a-aplicação-via-docker-compose)
7.  [Endpoints da API](#7-endpoints-da-api)
8.  [Documentação da API (Swagger)](#8-documentação-da-api-swagger)
9.  [Testes Automatizados](#9-testes-automatizados)
10. [Coleção Postman](#10-coleção-postman)
11. [Desafios Adicionais (Próximos Passos)](#11-desafios-adicionais-próximos-passos)

---

## 1. Tecnologias Utilizadas

* **Backend:**
    * Java 17+
    * Spring Boot 3+
    * Spring Data JPA
    * Hibernate
    * Maven
    * Flyway (Gerenciamento de Migrações de Banco de Dados)
* **Banco de Dados:**
    * PostgreSQL
* **Frontend:**
    * Angular 20+
    * TypeScript
    * HTML5
* **Containerização:**
    * Docker
    * Docker Compose

## 2. Estrutura do Projeto

A estrutura de pastas principal é organizada para facilitar a containerização com Docker Compose:

# Estrutura do Projeto

```plaintext
desafio-creditos/
├── backend/                  # Contém o projeto Spring Boot
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile            # Dockerfile para o backend
├── frontend/                 # Contém o projeto Angular
│   ├── src/
│   ├── angular.json
│   ├── package.json
│   ├── Dockerfile            # Dockerfile para o frontend
│   └── nginx.conf            # Configuração Nginx para servir o Angular
├── db/                       # Contém os scripts SQL para Flyway
│   ├── V1__create_credito_table.sql
│   └── V2__insert_test_data.sql
└── docker-compose.yml        # Orquestração dos serviços Docker
 ```


## 3. Pré-requisitos

Para rodar a aplicação, você precisará ter instalado:

* **Java Development Kit (JDK) 17 ou superior**
* **Maven 3.6+**
* **Node.js 20+**
* **Angular CLI** (`npm install -g @angular/cli`)
* **Docker Desktop** (inclui Docker Engine e Docker Compose)

## 4. Configuração do Banco de Dados

O banco de dados PostgreSQL será provisionado automaticamente pelo Docker Compose ou pode ser configurado localmente. As migrações do banco de dados são gerenciadas pelo Flyway, que será executado automaticamente pelo Spring Boot na inicialização.

Os scripts de migração estão localizados em `db/`:
* `V1__create_credito_table.sql`: Cria a tabela `credito`.
* `V2__insert_test_data.sql`: Insere 12 registros de teste na tabela `credito`.

## 5. Executando a Aplicação Localmente

Para rodar o backend e o frontend separadamente em sua máquina local:

### Backend (Spring Boot)

1.  **Navegue** até o diretório `backend/`:
    ```bash
    cd desafio-creditos/backend
    ```
2.  **Configure o Banco de Dados:**
    * Certifique-se de ter uma instância do PostgreSQL rodando localmente (você pode usar o Docker para isso: `docker run --name local-postgres -e POSTGRES_DB=creditos_db -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres:16-alpine`).
    * O Spring Boot, com o Flyway configurado, aplicará as migrações automaticamente ao iniciar.
3.  **Compile e Execute:**
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```
    O backend estará acessível em `http://localhost:8080`.

### Frontend (Angular)

1.  **Navegue** até o diretório `frontend/`:
    ```bash
    cd desafio-creditos/frontend
    ```
2.  **Instale as Dependências:**
    ```bash
    npm install
    ```
3.  **Execute a Aplicação:**
    ```bash
    ng serve
    ```
    O frontend estará acessível em `http://localhost:4200`.

## 6. Executando a Aplicação via Docker Compose

Esta é a forma recomendada para rodar a aplicação completa, pois ela orquestra o banco de dados, o backend e o frontend em containers isolados.

1.  **Navegue** até o diretório raiz do projeto (`desafio-creditos/`):
    ```bash
    cd desafio-creditos
    ```
2.  **Construa as Imagens Docker:**
    ```bash
    docker compose build
    ```
    Este comando construirá as imagens para o backend e o frontend com base nos seus respectivos `Dockerfile`s.
3.  **Inicie os Serviços:**
    ```bash
    docker compose up
    ```
    Este comando iniciará o PostgreSQL, o backend Spring Boot e o frontend Angular.
    * O PostgreSQL (`postgres_db`) será inicializado e as migrações do Flyway serão aplicadas pelo backend.
    * O backend (`desafio_creditos_backend`) estará disponível na porta `8080` do seu host.
    * O frontend (`desafio_creditos_frontend`) estará disponível na porta `4200` do seu host (mapeando a porta 80 do container Nginx).

4.  **Acesse a Aplicação:**
    Abra seu navegador e acesse: `http://localhost:4200`

5.  **Para parar os serviços:**
    No terminal onde o `docker compose up` está rodando, pressione `Ctrl+C`. Para remover os containers e volumes (se desejar uma limpeza completa):
    ```bash
    docker compose down -v
    ```

## 7. Endpoints da API

A API RESTful do backend expõe os seguintes endpoints:

* **GET /api/creditos/{numeroNfse}**
    * **Descrição:** Retorna uma lista de créditos constituídos com base no número da NFS-e.
    * **Parâmetro:** `numeroNfse` (String) - Número identificador da NFS-e.
    * **Exemplo:** `GET http://localhost:8080/api/creditos/NFSE001`

* **GET /api/creditos/credito/{numeroCredito}**
    * **Descrição:** Retorna os detalhes de um crédito constituído específico com base no número do crédito constituído.
    * **Parâmetro:** `numeroCredito` (String) - Número identificador do crédito constituído.
    * **Exemplo:** `GET http://localhost:8080/api/creditos/credito/CRED001`

## 8. Documentação da API (Swagger)

A documentação interativa da API é gerada automaticamente utilizando Swagger (OpenAPI).

Para acessar a documentação:

1.  Certifique-se de que o backend Spring Boot esteja em execução (localmente ou via Docker Compose).
2.  Abra seu navegador e acesse: `http://localhost:8080/swagger-ui.html`

    Você poderá visualizar todos os endpoints disponíveis, seus parâmetros, modelos de resposta e testar as requisições diretamente pela interface do Swagger UI.

**Observação:** Para que o Swagger funcione, você precisará adicionar as dependências do SpringDoc OpenAPI ao seu `pom.xml` (no diretório `backend/`) e, se necessário, configurar a aplicação Spring Boot para expor a documentação.

## 9. Testes Automatizados

O backend inclui exemplos de testes unitários para as camadas de Serviço e Controller, utilizando JUnit e Mockito.

Para executar os testes do backend:

1.  **Navegue** até o diretório `backend/`:
    ```bash
    cd desafio-creditos/backend
    ```
2.  **Execute os testes Maven:**
    ```bash
    mvn test
    ```

## 10. Coleção Postman

Uma coleção Postman foi fornecida para facilitar o teste manual dos endpoints da API.

Você pode importar o arquivo JSON da coleção diretamente no Postman. Ele contém exemplos de requisições para todos os endpoints, incluindo casos de sucesso e de "não encontrado".

## 11. Desafios Adicionais (Próximos Passos)
