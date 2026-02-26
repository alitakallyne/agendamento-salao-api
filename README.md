## Sobre o projeto
## 📅 Sistema de Agendamento de Horários

Este projeto é uma API REST desenvolvida em Java + Spring Boot, com foco em regras de negócio realistas para controle de agendamentos profissionais (salões, clínicas, consultórios, etc).
O sistema permite criar, listar, atualizar, reagendar, cancelar e concluir agendamentos, respeitando regras de disponibilidade, horário de funcionamento e status.

## 🧩 Entidades Principais

- Cliente

- Profissional

- Serviço

- Agendamento

- Horário de Funcionamento

## 🚀 Tecnologias Utilizadas

- ☕ Java 17  
- 🌱 Spring Boot  
- 📊 H2 Database (ambiente local)
- 🛠 Maven

## 📂 Estrutura do Projeto

```text
└───com
    └───alita
        └───agendador_horarios
            │   AgendadorHorariosApplication.java
            │   
            ├───controller
            │       AgendamentoController.java
            │       
            ├───infrastructure
            │   ├───dto
            │   │       AgendamentoResponse.java
            │   │       CriarAgendamentoRequest.java
            │   │
            │   ├───entity
            │   │       Agendamento.java
            │   │       Cliente.java
            │   │       HorarioFuncionamento.java
            │   │       Profissional.java
            │   │       Servico.java
            │   │       StatusAgendamento.java
            │   │
            │   └───repository
            │           AgendamentoRepository.java
            │           ClienteRepository.java
            │           HorarioFuncionamentoRepository.java
            │           ProfissionalRepository.java
            │           ServicoRepository.java
            │
            └───services
                    AgendamentoService.java
```

## ▶️ Como Executar o Projeto
1️⃣ Clonar o repositório
```
git clone https://github.com/alitakallyne/agendamento-api.git
```
2️⃣ Acessar o projeto
```
cd agendamento-api
```
3️⃣ Rodar a aplicação
```
./mvnw spring-boot:run
```

## 💡Este projeto foi inicialmente inspirado em um estudo prático apresentado pela Javanauta e posteriormente refatorado, evoluído.

## 👩‍💻 Autora

Alita Kallyne
Projeto criado para estudo e evolução profissional em Java e Spring Boot.

## 📜 Licença

Este projeto é de uso livre para fins de estudo e aprendizado.