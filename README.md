# Blog API

Projeto: API para gerenciar posts e usuários (exemplo).

Visão geral
- Linguagem: Java
- Stack: Spring Boot (exemplo)
- Objetivo: fornecer endpoints REST para criar, listar, atualizar e deletar posts.

Como rodar (desenvolvimento)
1. Build:
   mvn clean package
2. Rodar:
   mvn spring-boot:run
   ou
   java -jar target/{seu-artifact}.jar

Endpoints de documentação
- Swagger UI (interativo): http://localhost:8080/swagger-ui/index.html
- Especificação OpenAPI (JSON): http://localhost:8080/v3/api-docs
- Especificação local (arquivo): docs/openapi.yaml

Documentação de API
- A especificação OpenAPI está em `docs/openapi.yaml`. Ela descreve os endpoints, modelos de request/response e códigos de erro.
- Você pode gerar clientes e servidores a partir desta especificação com ferramentas como `openapi-generator`.

Como atualizar a documentação da API
1. Se usar OpenAPI manual: edite `docs/openapi.yaml`.
2. Se gerar a partir do código: comente/adicione as anotações nos controllers (ex.: `@Operation`, `@Parameter`) e rode a aplicação para acessar `/v3/api-docs`.

Contribuindo
- Crie uma branch com nome descritivo: `docs/add-api-docs` ou `feature/docs-openapi`.
- Abra PR e referencie a issue: `Fixes #25` (ou `Closes #25`) no corpo do PR.
- Checklist do PR:
  - [ ] README atualizado
  - [ ] docs/openapi.yaml presente e válida
  - [ ] Instruções para rodar e acessar Swagger UI
  - [ ] Exemplos de requests (curl) para endpoints principais

