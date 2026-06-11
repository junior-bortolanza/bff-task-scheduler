
# Angendador de Tarefas - Microsservicos
Projeto pessoal desenvolvido para praticar arquitetura de microsserviços com Spring Boot.
 
A arquitetura utiliza o padrão **BFF (Backend for Frontend)**, onde um serviço centraliza e orquestra as chamadas aos microsserviços internos, expondo uma API unificada para o cliente.
 
---



## Arquitetura

![App Screenshot](https://github.com/junior-bortolanza/bff-task-scheduler/blob/master/bff-agendador-tarefas/src/main/resources/microsservices.png)


## Repositórios

| Serviço | Repositório |
|---|---|
| BFF | [github.com/junior-bortolanza/bff-agendador-tarefas](https://github.com/junior-bortolanza/bff-task-scheduler) |
| Usuários | [github.com/junior-bortolanza/ms-usuarios](https://github.com/junior-bortolanza/user) |
| Planejamento de Tarefas | [github.com/junior-bortolanza/ms-agendador-tarefas](https://github.com/junior-bortolanza/task-planner) |
| Notificação | [github.com/junior-bortolanza/ms-notificacao](https://github.com/junior-bortolanza/notification) |
 

 
---

## Tech Stack
 
| Camada | Tecnologias |
|---|---|
| Linguagem | Java 17 |
| Framework | Spring Boot 3.x · Spring Security · Spring Data JPA · Spring Data MongoDB |
| Build | Gradle · Lombok |
| Comunicação | Feign Client (entre microsserviços) |
| Segurança | JWT · OAuth2 (Resource Server) |
| Agendamento | Spring CRON (`@Scheduled`) |
| Banco de Dados | PostgreSQL (ms-usuarios) · MongoDB (ms-tarefas, ms-notificacao) |
| Containers | Docker · Docker Compose |
| Documentação | Swagger / OpenAPI |
| Qualidade | SonarQube |
| Testes | JUnit 5 |

## Pré-requisitos

- Docker e Docker Compose instalados
- Java 21+ (para rodar sem Docker)
- Gradle 8+

## Rodando o projeto com Docker

Clone o repositório principal (BFF):
 
```bash
git clone https://github.com/junior-bortolanza/bff-task-scheduler
cd bff-agendador-tarefas
```
 
uba todos os containers (BFF + 3 microsserviços + bancos + SonarQube):
 
```bash
docker compose up -d
```
 
Os serviços estarão disponíveis nas seguintes portas:
 
| Container | Porta |
|---|---|
| `usuario` | http://localhost:8080 |
| `agendador-tarefas` | http://localhost:8081 |
| `notificacao` | http://localhost:8084 |
| `bff-agendador-tarefas` | http://localhost:8083 |
| `postgres` | localhost:5433 |
| `mongo` | localhost:27017 |
 
> O `docker-compose.yml` orquestra 6 containers em uma rede bridge compartilhada.
 
---

## Dockerfile
 
O projeto utiliza **multi-stage build** para otimizar o tamanho da imagem final:
 
```dockerfile
FROM maven:3.8-openjdk-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests
 
FROM openjdk:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar /app/bff-agendador-tarefas.jar
EXPOSE 8083
CMD ["java", "-jar", "/app/bff-agendador-tarefas.jar"]
```
 
- **Stage 1 (build):** compila o projeto com Maven
- **Stage 2 (runtime):** copia apenas o `.jar` para uma imagem Alpine enxuta
---

## Funcionalidades

- Cadastro e autenticação de usuários com JWT
- CRUD de tarefas com agendamento via CRON
- Envio de notificações automáticas baseado em gatilhos das tarefas
- BFF como ponto único de entrada, orquestrando chamadas aos microsserviços via Feign Client
- Qualidade de código monitorada via SonarQube, com aplicação de boas práticas de **Clean Code** e **SOLID**
- Ambiente completo containerizado com Docker Compose
---
## O que aprendi

- Implementar o padrão BFF para centralizar e simplificar a comunicação entre cliente e microsserviços
- Configurar Spring Security com JWT em múltiplos serviços independentes
- Usar Feign Client para comunicação declarativa entre microsserviços
- Gerenciar dois bancos distintos (PostgreSQL e MongoDB) no mesmo ecossistema
- Orquestrar múltiplos containers com Docker Compose, incluindo ferramentas de qualidade como SonarQube
- Configurar CRON jobs com Spring para automação de tarefas agendadas
---


## Authors

**Junior Bortolanza**
[github.com/junior-bortolanza](https://github.com/junior-bortolanza) · [juniorbortolanza.dev](https://juniorbortolanza.dev)

