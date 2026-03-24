# 🏠 Sistema de Locação de Imóveis

## 📌 Sobre o Projeto
Este projeto é uma aplicação desenvolvida em Java com Spring Boot, com o objetivo de gerenciar um sistema de locação de imóveis.

A aplicação permite o controle de clientes e imóveis, aplicando regras de negócio como verificação de disponibilidade e gerenciamento de locações.

---

## 🚀 Tecnologias Utilizadas
- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- JUnit 5
- Mockito

---

## 📂 Estrutura do Projeto

O projeto segue uma arquitetura em camadas:

src/
├── main/
│ ├── controllers/ # Camada de entrada (requisições HTTP)
│ ├── services/ # Regras de negócio.
| |── DTO/ Para esconder os dados dos clientes.
│ ├── repositories/ # Acesso ao banco de dados.
│ ├── model/ # Entidades do sistema.
│ └── infrastructure/ # Configurações e integrações.
│
└── test/ # Testes unitários e de integração.


---

## ⚙️ Funcionalidades

- ✅ Cadastro de clientes
- ✅ Cadastro de imóveis
- ✅ Registro de aluguéis
- ✅ Verificação de disponibilidade de imóveis
- ✅ Atualização e remoção de dados

---

## ▶️ Como Executar o Projeto

## Pré-requisitos
- Java 17 ou superior
- Maven
- MySQL

### 1. Clonar o repositório
```bash
git clone https://github.com/seu-usuario/seu-repositorio.git

## 2. Acessar o projeto
cd Projeto-SA-main

## 3. Configurar o banco de dados
 application.properties

--spring.datasource.url=jdbc:mysql://localhost:3306/locacao_imoveis
--spring.datasource.username=root
--spring.datasource.password=sua_senha
--spring.jpa.hibernate.ddl-auto=update

## 4. Executar o projeto
mvn spring-boot:run

## A aplicação estará disponível em:
http://localhost:8080/

📸 Demonstração do Sistema
🏠 Tela Inicial

🏢 Cadastro de Imóvel

📋 Lista de Imóveis


🧪 Testes

## Para executar os testes:
mvn test

👨‍💻 Autor

Wellinton Lima Braz
📧 wellinton.braz29@gmail.com

🔗 https://github.com/Wellinton98
