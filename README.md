# DevTasks

O **DevTasks** é um sistema de gerenciamento de tarefas simples desenvolvido para treinar conceitos de **Java**, **JDBC**, **Banco de Dados** e **Testes Unitários**. O objetivo do projeto é aprender e aplicar práticas de desenvolvimento que são comumente usadas no mercado, como integração com banco de dados, estruturação de código, testes e boas práticas de programação.

## Funcionalidades

- **Gerenciamento de Tarefas**: Adicionar, listar, atualizar e excluir tarefas.
- **Status de Tarefas**: Atribuição de diferentes status às tarefas (ex: Pendente, Concluída).
- **Data de Limite**: Definir uma data limite para cada tarefa.
- **Armazenamento em Banco de Dados**: Uso do PostgreSQL para persistir as informações das tarefas.

## Tecnologias Utilizadas

- **Java 17**: Linguagem de programação principal.
- **JDBC**: Para a interação com o banco de dados PostgreSQL.
- **JUnit 5**: Para a realização de testes unitários.
- **PostgreSQL**: Banco de dados utilizado para armazenamento das tarefas.
- **Git**: Controle de versão.

## Estrutura do Projeto

- **src/main/java**: Contém o código-fonte principal do projeto.
  - **model**: Contém as classes de modelo, como `Tarefa` e `StatusTarefa`.
  - **repository**: Contém a classe `TarefaRepository`, que interage diretamente com o banco de dados.
  - **service**: Contém a classe `TarefaService`, que gerencia as operações sobre as tarefas.
  - **Main**: Classe principal que inicializa a aplicação.
- **src/test/java**: Contém os testes unitários.

## Como Executar

### Pré-requisitos

1. **PostgreSQL**: Instalar o PostgreSQL em seu sistema.
2. **Banco de Dados**: Criar um banco de dados chamado `devtasks` no PostgreSQL.
3. **JDBC**: O arquivo de configuração do banco de dados foi omitido por questões de segurança, então adicione a sua configuração em `DatabaseConnection.java` com o seu usuário e senha do banco de dados.

### Passos para Rodar o Projeto

1. Clone o repositório para sua máquina local:
   ```bash
   git clone https://github.com/seuusuario/devtasks.git
