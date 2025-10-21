# Exemplos de Uso da API

Este documento contém exemplos práticos de como usar a API Blog.

## Sumário

1. [Registro e Login](#registro-e-login)
2. [Gerenciamento de Usuários](#gerenciamento-de-usuários)
3. [Usando o Swagger UI](#usando-o-swagger-ui)
4. [Autenticação JWT](#autenticação-jwt)

---

## Registro e Login

### 1. Registrar um novo usuário

**Request:**
```bash
curl -X POST http://localhost:8080/api/blog/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Maria Silva",
    "email": "maria@example.com",
    "password": "SenhaSegura123!"
  }'
```

**Response (201 Created):**
```json
{
  "id": 1,
  "name": "Maria Silva",
  "email": "maria@example.com",
  "roleUser": "USER"
}
```

### 2. Fazer login

**Request:**
```bash
curl -X POST http://localhost:8080/api/blog/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "maria@example.com",
    "password": "SenhaSegura123!"
  }'
```

**Response (202 Accepted):**
```json
{
  "id": 1,
  "email": "maria@example.com",
  "roles": [
    {
      "authority": "ROLE_USER"
    }
  ],
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJtYXJpYUBleGFtcGxlLmNvbSIsImlhdCI6MTYxNjIzOTAyMiwiZXhwIjoxNjE2MzI1NDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
}
```

**Importante:** Guarde o token retornado! Você precisará dele para acessar os endpoints protegidos.

---

## Gerenciamento de Usuários

> **Nota:** Todos os endpoints de usuários requerem autenticação. Use o token obtido no login.

### 3. Listar todos os usuários

**Request:**
```bash
curl -X GET http://localhost:8080/api/blog/users \
  -H "Authorization: Bearer SEU_TOKEN_AQUI"
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "name": "Maria Silva",
    "email": "maria@example.com",
    "role": "USER",
    "createAt": "2025-01-15T10:30:00Z",
    "updateAt": "2025-01-15T10:30:00Z"
  },
  {
    "id": 2,
    "name": "João Admin",
    "email": "admin@example.com",
    "role": "ADMIN",
    "createAt": "2025-01-15T11:00:00Z",
    "updateAt": "2025-01-15T11:00:00Z"
  }
]
```

### 4. Buscar usuário por ID

**Request:**
```bash
curl -X GET http://localhost:8080/api/blog/users/1 \
  -H "Authorization: Bearer SEU_TOKEN_AQUI"
```

**Response (200 OK):**
```json
{
  "id": 1,
  "name": "Maria Silva",
  "email": "maria@example.com",
  "role": "USER",
  "createAt": "2025-01-15T10:30:00Z",
  "updateAt": "2025-01-15T10:30:00Z"
}
```

### 5. Criar novo usuário (requer autenticação)

**Request:**
```bash
curl -X POST http://localhost:8080/api/blog/users \
  -H "Authorization: Bearer SEU_TOKEN_AQUI" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Pedro Santos",
    "email": "pedro@example.com",
    "password": "SenhaForte456!",
    "roleUser": "USER"
  }'
```

**Response (201 Created):**
```json
{
  "id": 3,
  "name": "Pedro Santos",
  "email": "pedro@example.com",
  "roleUser": "USER"
}
```

### 6. Editar usuário

> **Importante:** Apenas o próprio usuário ou um ADMIN pode editar.

**Request:**
```bash
curl -X PATCH http://localhost:8080/api/blog/users \
  -H "Authorization: Bearer SEU_TOKEN_AQUI" \
  -H "Content-Type: application/json" \
  -d '{
    "id": 1,
    "name": "Maria Silva Santos",
    "email": "maria.santos@example.com"
  }'
```

**Response (202 Accepted):**
```json
{
  "id": 1,
  "name": "Maria Silva Santos",
  "email": "maria.santos@example.com"
}
```

### 7. Deletar usuário

> **Importante:** Apenas o próprio usuário ou um ADMIN pode deletar.

**Request:**
```bash
curl -X DELETE http://localhost:8080/api/blog/users \
  -H "Authorization: Bearer SEU_TOKEN_AQUI" \
  -H "Content-Type: application/json" \
  -d '{
    "id": 3
  }'
```

**Response (202 Accepted):**
```
User deleted with success
```

---

## Usando o Swagger UI

O Swagger UI oferece uma interface interativa para testar a API:

1. **Acessar o Swagger UI:**
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

2. **Autenticar (para endpoints protegidos):**
   - Clique no botão **"Authorize"** no topo da página
   - No campo `Value`, insira: `Bearer SEU_TOKEN_AQUI`
   - Clique em **"Authorize"**
   - Clique em **"Close"**

3. **Testar um endpoint:**
   - Expanda o endpoint desejado (clique nele)
   - Clique em **"Try it out"**
   - Preencha os parâmetros necessários
   - Clique em **"Execute"**
   - Veja a resposta abaixo

---

## Autenticação JWT

### Como funciona

1. Faça login usando `/api/blog/auth/login`
2. Guarde o token JWT retornado
3. Inclua o token no header `Authorization` de todas as requisições protegidas:
   ```
   Authorization: Bearer {seu_token}
   ```

### Validade do Token

- O token é válido por **24 horas** (86400000 ms)
- Após expirar, faça login novamente para obter um novo token

### Exemplo completo de fluxo

```bash
# 1. Fazer login e capturar o token
TOKEN=$(curl -s -X POST http://localhost:8080/api/blog/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "maria@example.com",
    "password": "SenhaSegura123!"
  }' | jq -r '.token')

# 2. Usar o token para acessar endpoint protegido
curl -X GET http://localhost:8080/api/blog/users \
  -H "Authorization: Bearer $TOKEN"
```

---

## Tratamento de Erros

### Erro 400 - Bad Request
Dados inválidos enviados na requisição.

**Exemplo:**
```json
{
  "message": "Email inválido",
  "status": 400
}
```

### Erro 401 - Unauthorized
Token não fornecido ou inválido.

**Exemplo:**
```json
{
  "message": "Token JWT inválido ou expirado",
  "status": 401
}
```

### Erro 403 - Forbidden
Usuário não tem permissão para executar a operação.

**Exemplo:**
```json
{
  "message": "Usuário sem permissão para editar este recurso",
  "status": 403
}
```

### Erro 404 - Not Found
Recurso não encontrado.

**Exemplo:**
```json
{
  "message": "Usuário não encontrado",
  "status": 404
}
```

---

## Validações de Dados

### Campos de Usuário

| Campo | Tipo | Obrigatório | Validações |
|-------|------|-------------|------------|
| name | String | Sim | Máximo 30 caracteres, não vazio |
| email | String | Sim | Email válido, máximo 150 caracteres, único |
| password | String | Sim | Máximo 180 caracteres, deve atender critérios de complexidade |
| roleUser | Enum | Sim (create) | USER ou ADMIN |

### Regras de Senha

A senha deve atender aos seguintes critérios (validados pela biblioteca Passay):
- Comprimento adequado
- Complexidade suficiente (letras, números, caracteres especiais)

---

## Dicas e Boas Práticas

1. **Sempre use HTTPS em produção** para proteger o token JWT
2. **Não compartilhe seu token JWT** - ele dá acesso total à sua conta
3. **Armazene o token de forma segura** - nunca no código ou repositório
4. **Faça logout invalidando o token** do lado do cliente
5. **Use o Swagger UI** para explorar e entender a API antes de integrar
6. **Verifique os códigos de status HTTP** nas respostas
7. **Trate os erros adequadamente** na sua aplicação cliente

---

## Recursos Adicionais

- **Documentação completa:** Veja o [README.md](../README.md)
- **Especificação OpenAPI:** Veja [openapi.yaml](openapi.yaml)
- **Swagger UI:** http://localhost:8080/swagger-ui/index.html
- **OpenAPI JSON:** http://localhost:8080/v3/api-docs

---

## Suporte

Em caso de dúvidas ou problemas:
1. Verifique a documentação no README.md
2. Consulte a especificação OpenAPI
3. Abra uma issue no GitHub: https://github.com/Luca5Eckert/blog-api/issues
