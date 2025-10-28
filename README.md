# Synco App

Uma API REST desenvolvida em Java Spring Boot para centralizar a gestão de informações em ambientes de aprendizagem.

O Synco App nasceu para resolver a comunicação deficiente entre a esfera administrativa (coordenação/professores) e os alunos. Ao criar um hub unificado de informações, a plataforma garante que avisos, horários e feedbacks sejam distribuídos de forma eficiente, servindo como uma fonte única de verdade para toda a comunidade acadêmica.

## 📋 Visão Geral

Esta API fornece endpoints para:
- 🔐 Autenticação e registro de usuários
- 👥 Gerenciamento completo de usuários (CRUD)
- 🔒 Controle de acesso baseado em roles (USER, ADMIN)
- 🔑 Autenticação via JWT (JSON Web Token)

## 🛠️ Tecnologias e Dependências

### Stack Principal
- **Java**: 22
- **Spring Boot**: 3.3.0
- **Spring Security**: Autenticação e autorização
- **Spring Data JPA**: Persistência de dados
- **H2 Database**: Banco de dados em memória (desenvolvimento)
- **MySQL**: Suporte para banco de dados em produção
- **Lombok**: Redução de código boilerplate
- **Bean Validation**: Validação de dados

### Bibliotecas Adicionais
- **JWT (jjwt)**: 0.11.5 - Geração e validação de tokens
- **Commons Validator**: 1.8.0 - Validação de email
- **Passay**: 1.6.6 - Validação de senha
- **SpringDoc OpenAPI**: 2.5.0 - Documentação Swagger/OpenAPI

### Build
- **Maven**: Gerenciamento de dependências e build

## 🏗️ Arquitetura do Projeto

O projeto segue uma arquitetura em camadas com separação de responsabilidades:

```
src/main/java/com/api/blog/
├── config/                      # Configurações gerais (OpenAPI, etc)
├── core/                        # Serviços centrais (autenticação)
└── module/                      # Módulos de domínio
    ├── authentication/          # Módulo de autenticação
    │   ├── application/         # Controllers e DTOs
    │   │   ├── controller/      # REST Controllers
    │   │   └── dto/             # Request/Response objects
    │   └── domain/              # Lógica de negócio
    │       ├── service/         # Services
    │       ├── use_case/        # Casos de uso
    │       └── exception/       # Exceções customizadas
    └── user/                    # Módulo de usuários
        ├── application/         # Controllers e DTOs
        ├── domain/              # Lógica de negócio
        └── infrastructure/      # Repositórios e persistência
```

### Padrões Utilizados
- **Clean Architecture**: Separação entre camadas de aplicação, domínio e infraestrutura
- **Repository Pattern**: Abstração da camada de persistência
- **DTO Pattern**: Objetos de transferência de dados
- **Use Case Pattern**: Encapsulamento da lógica de negócio

## 🚀 Como Rodar o Projeto

### Pré-requisitos
- Java 22 ou superior
- Maven 3.6+

### Instalação e Execução

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/Luca5Eckert/blog-api.git
   cd blog-api
   ```

2. **Build do projeto**:
   ```bash
   mvn clean package
   ```

3. **Executar a aplicação**:
   ```bash
   mvn spring-boot:run
   ```
   
   Ou execute o JAR gerado:
   ```bash
   java -jar target/blog-0.0.1-SNAPSHOT.jar
   ```

4. **Acessar a aplicação**:
   - API: http://localhost:8080
   - Swagger UI: http://localhost:8080/swagger-ui/index.html
   - H2 Console: http://localhost:8080/h2-console

### Configuração do Banco de Dados

Por padrão, a aplicação usa H2 (em memória) para desenvolvimento:
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=
```

Para usar MySQL em produção, atualize `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/blog_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

## 📚 Documentação da API

### Swagger/OpenAPI

A documentação interativa está disponível através do Swagger UI:

- **Swagger UI (interface interativa)**: http://localhost:8080/swagger-ui/index.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs
- **OpenAPI YAML**: `docs/openapi.yaml` (arquivo local)

### Endpoints Principais

#### 🔐 Autenticação (`/api/blog/auth`)

| Método | Endpoint | Descrição | Autenticação |
|--------|----------|-----------|--------------|
| POST | `/api/blog/auth/register` | Registrar novo usuário | Não |
| POST | `/api/blog/auth/login` | Fazer login | Não |

#### 👥 Usuários (`/api/blog/users`)

| Método | Endpoint | Descrição | Autenticação |
|--------|----------|-----------|--------------|
| GET | `/api/blog/users` | Listar todos os usuários | Sim |
| GET | `/api/blog/users/{id}` | Buscar usuário por ID | Sim |
| POST | `/api/blog/users` | Criar novo usuário | Sim |
| PATCH | `/api/blog/users` | Editar usuário | Sim |
| DELETE | `/api/blog/users` | Deletar usuário | Sim |

### Exemplos de Requisições

#### 1. Registrar um novo usuário

```bash
curl -X POST http://localhost:8080/api/blog/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "João Silva",
    "email": "joao@example.com",
    "password": "SenhaForte123!"
  }'
```

**Resposta (201 Created)**:
```json
{
  "id": 1,
  "name": "João Silva",
  "email": "joao@example.com",
  "roleUser": "USER"
}
```

#### 2. Fazer login

```bash
curl -X POST http://localhost:8080/api/blog/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "joao@example.com",
    "password": "SenhaForte123!"
  }'
```

**Resposta (202 Accepted)**:
```json
{
  "id": 1,
  "email": "joao@example.com",
  "roles": [
    {
      "authority": "ROLE_USER"
    }
  ],
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

#### 3. Listar todos os usuários (autenticado)

```bash
curl -X GET http://localhost:8080/api/blog/users \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

**Resposta (200 OK)**:
```json
[
  {
    "id": 1,
    "name": "João Silva",
    "email": "joao@example.com",
    "role": "USER",
    "createAt": "2025-01-15T10:30:00Z",
    "updateAt": "2025-01-15T10:30:00Z"
  }
]
```

#### 4. Buscar usuário por ID (autenticado)

```bash
curl -X GET http://localhost:8080/api/blog/users/1 \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

#### 5. Criar novo usuário (autenticado)

```bash
curl -X POST http://localhost:8080/api/blog/users \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..." \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Admin User",
    "email": "admin@example.com",
    "password": "AdminPass123!",
    "roleUser": "ADMIN"
  }'
```

#### 6. Editar usuário (autenticado)

```bash
curl -X PATCH http://localhost:8080/api/blog/users \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..." \
  -H "Content-Type: application/json" \
  -d '{
    "id": 1,
    "name": "João Silva Santos",
    "email": "joao.santos@example.com"
  }'
```

#### 7. Deletar usuário (autenticado)

```bash
curl -X DELETE http://localhost:8080/api/blog/users \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..." \
  -H "Content-Type: application/json" \
  -d '{
    "id": 1
  }'
```

### Modelos de Dados

#### UserRegisterRequest
```json
{
  "name": "string (max 30)",
  "email": "string (max 150)",
  "password": "string (max 180)"
}
```

#### UserLoginRequest
```json
{
  "email": "string",
  "password": "string"
}
```

#### UserCreateRequest
```json
{
  "name": "string (max 30)",
  "email": "string (max 150)",
  "password": "string (max 180)",
  "roleUser": "USER | ADMIN"
}
```

#### UserEditRequest
```json
{
  "id": "number",
  "name": "string (max 30)",
  "email": "string (max 150)"
}
```

#### UserDeleteRequest
```json
{
  "id": "number"
}
```

### Códigos de Status HTTP

| Código | Descrição |
|--------|-----------|
| 200 | OK - Requisição bem-sucedida |
| 201 | Created - Recurso criado com sucesso |
| 202 | Accepted - Requisição aceita |
| 400 | Bad Request - Dados inválidos |
| 401 | Unauthorized - Não autenticado |
| 403 | Forbidden - Sem permissão |
| 404 | Not Found - Recurso não encontrado |

## 🔒 Autenticação e Segurança

### JWT (JSON Web Token)

A API usa JWT para autenticação. Após o login, você recebe um token que deve ser incluído no header `Authorization` de todas as requisições protegidas:

```
Authorization: Bearer {seu_token_jwt}
```

### Roles e Permissões

- **USER**: Pode visualizar informações e editar/deletar apenas seus próprios dados
- **ADMIN**: Pode gerenciar todos os usuários

### Validações

- **Email**: Deve ser válido e único no sistema (max 150 caracteres)
- **Senha**: Validada com regras de complexidade (max 180 caracteres)
- **Nome**: Obrigatório (max 30 caracteres)

## 🧪 Testes

Para executar os testes:

```bash
mvn test
```

## 📖 Como Atualizar a Documentação

### Documentação Manual (OpenAPI YAML)

Edite o arquivo `docs/openapi.yaml` manualmente.

### Documentação Gerada (Swagger)

As anotações OpenAPI nos controllers geram automaticamente a documentação. Para atualizar:

1. Adicione/edite anotações nos controllers:
   - `@Tag`: Agrupar endpoints
   - `@Operation`: Descrever operação
   - `@ApiResponses`: Documentar respostas
   - `@Parameter`: Documentar parâmetros

2. Execute a aplicação

3. Acesse http://localhost:8080/v3/api-docs para ver o JSON gerado

## 🤝 Contribuindo

Contribuições são bem-vindas! Para contribuir:

1. Crie uma branch com nome descritivo:
   ```bash
   git checkout -b feature/minha-feature
   # ou
   git checkout -b docs/atualizar-documentacao
   ```

2. Faça suas alterações e commit:
   ```bash
   git commit -m "feat: adiciona nova funcionalidade"
   ```

3. Abra um Pull Request referenciando a issue:
   ```
   Fixes #25
   ```

### Checklist do PR

- [ ] README atualizado (se necessário)
- [ ] `docs/openapi.yaml` atualizado (se necessário)
- [ ] Anotações OpenAPI nos controllers
- [ ] Testes adicionados/atualizados
- [ ] Build passa sem erros
- [ ] Código segue os padrões do projeto

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

## 📞 Contato

- **GitHub**: [@Luca5Eckert](https://github.com/Luca5Eckert)
- **Repositório**: [blog-api](https://github.com/Luca5Eckert/blog-api)

## 🔗 Links Úteis

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [JWT.io](https://jwt.io/)
- [SpringDoc OpenAPI](https://springdoc.org/)
- [OpenAPI Specification](https://swagger.io/specification/)

