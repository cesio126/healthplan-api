# API de Beneficiários de Plano de Saúde

Esta é uma aplicação Spring Boot que fornece uma API REST para gerenciar beneficiários de um plano de saúde, incluindo os documentos associados a eles. A API oferece operações básicas de CRUD e inclui autenticação por **Basic Authentication**.

## Funcionalidades

- **Gerenciamento de Beneficiários**: Criar, atualizar, listar e excluir beneficiários.
- **Gerenciamento de Documentos**: Associar documentos (por exemplo, RG ou CPF) a beneficiários.
- **Autenticação**: Acesso seguro aos endpoints utilizando **Basic Authentication**.
- **Documentação da API**: Documentação da API gerada automaticamente utilizando **Swagger/OpenAPI**.
- **Lombok**: Utilizado para reduzir o código boilerplate.

## Tecnologias Utilizadas

- **Java 11** ou superior
- **Spring Boot**
- **Spring Data JPA**: Para persistência de dados.
- **Spring Security**: Para autenticação e autorização.
- **H2 Database**: Banco de dados em memória para testes (pode ser substituído por outros bancos de dados).
- **Lombok**: Para eliminar código repetitivo como getters, setters, construtores, etc.
- **Springdoc OpenAPI**: Para gerar a documentação da API com **Swagger UI**.

## Endpoints Disponíveis

### Beneficiários

- `POST /api/beneficiarios`: Cadastrar um novo beneficiário.
- `GET /api/beneficiarios`: Listar todos os beneficiários.
- `GET /api/beneficiarios/{id}`: Buscar um beneficiário pelo ID.
- `PUT /api/beneficiarios/{id}`: Atualizar os dados de um beneficiário.
- `DELETE /api/beneficiarios/{id}`: Remover um beneficiário.

### Documentos

- `POST /api/documentos/beneficiario/{beneficiarioId}`: Cadastrar um novo documento para um beneficiário.
- `GET /api/documentos/beneficiario/{beneficiarioId}`: Listar todos os documentos de um beneficiário.
- `GET /api/documentos/{id}`: Buscar um documento pelo ID.
- `PUT /api/documentos/{id}`: Atualizar os dados de um documento.
- `DELETE /api/documentos/{id}`: Remover um documento.

## Autenticação

A API está protegida por **Basic Authentication**. Você precisa fornecer as credenciais corretas (usuário e senha) para acessar os endpoints. Exemplo de credenciais configuradas:

- **Usuário**: `user`
- **Senha**: `password`

Essas credenciais podem ser modificadas no arquivo de configuração de segurança da aplicação.

## Como Executar a Aplicação

### Pré-requisitos

- **Java 11** ou superior.
- **Maven** instalado.

### Passos

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/seu-usuario/nome-do-repositorio.git
