# Microservices

Monorepo de exemplo com arquitetura de microserviços em Java (Spring Boot), composto por dois serviços principais e mensageria via RabbitMQ:

- Store (frontend/backoffice da loja) — expõe API REST e consome eventos
- Warehouse (estoque/fulfillment) — expõe API REST e publica eventos

Ambos usam H2 (in-memory) para persistência durante o desenvolvimento e se comunicam via HTTP e RabbitMQ.

## Arquitetura

- **Store (porta 8081, context-path `/store`)**
  - Endpoints REST para cadastro e consulta de produtos do ponto de vista da loja
  - Realiza compras e consulta disponibilidade
  - Consome mensagens de alteração de disponibilidade (via RabbitMQ)
  - Chama o `Warehouse` quando necessário via `RestClient` (base configurada em `warehouse.base-path`)

- **Warehouse (porta 8080, context-path `/warehouse`)**
  - Endpoints REST para cadastro de produtos e gerenciamento de estoque
  - Publica eventos de alteração de disponibilidade no RabbitMQ
  - Notifica a `Store` via mensageria quando o estoque muda
  - Chama a `Store` quando necessário via `RestClient` (base configurada em `store.base-path`)

- **RabbitMQ**
  - Exchange/Queue/Binding para o evento: `product-change-availability`
  - Management UI: `http://localhost:15672` (user: `admin`, pass: `admin`)

## Serviços e principais endpoints

### Store (`http://localhost:8081/store`)

- `POST /products` — cria produto na loja
- `POST /products/{id}/purchase` — efetiva a compra do produto
- `GET /products` — lista produtos disponíveis
- `GET /products/{id}` — detalhes do produto

Swagger UI: `http://localhost:8081/store/swagger-ui/index.html#/`

### Warehouse (`http://localhost:8080/warehouse`)

- `POST /products` — cria produto no estoque (e replica na Store via HTTP)
- `PUT /products/{id}/purchased` — registra compra (decrementa estoque e, se necessário, muda disponibilidade)
- `GET /products/{id}` — detalhes do produto (visão do estoque)
- `POST /stocks` — cria/associa estoque
- `PUT /stocks/{id}/released` — libera estoque (disponível)
- `DELETE /stocks/{id}/inactived` — inativa estoque (indisponível)

## Mensageria (RabbitMQ)

- Exchange: `product.change.availability.exchange`
- Queue: `product.change.availability.queue`
- Routing key: `product.change.availability.routing.key`
- Producer: `warehouse` publica mensagens `StockStatusMessage` quando o status do estoque muda
- Consumer: `store` consome e atualiza disponibilidade local (via `spring.rabbitmq.*`)

## Configuração e execução

Pré-requisitos:

- Docker e Docker Compose
- JDK 17+ (para build local, opcional se usar apenas Docker)

Rede Docker:
Este projeto assume a rede externa `Microservices`. Caso não exista, crie-a:

```bash
docker network create Microservices
```

Subindo serviços (em terminais separados ou na raiz de cada módulo):

- `warehouse/docker-compose.yml` sobe `warehouse` e `rabbitmq`
- `store/docker-compose.yml` sobe `store`

Comandos:

```bash
cd warehouse && docker compose up --build
cd ../store && docker compose up --build
```

Perfis e portas:

- Store: `SPRING_PROFILES_ACTIVE=dev`, porta host `8081`
- Warehouse: `SPRING_PROFILES_ACTIVE=dev`, porta host `8080`
- RabbitMQ: `5672` (broker) e `15672` (management)

## Variáveis de configuração relevantes

Store (`store/src/main/resources/application.yml`):

- `server.servlet.context-path=/store`, `server.port=8081`
- H2 em memória: `jdbc:h2:mem:store`
- RabbitMQ host: `rabbitmq` (no Docker), credenciais `admin/admin`
- `warehouse.base-path`: URL base para consumir a API do Warehouse

Warehouse (`warehouse/src/main/resources/application.yml`):

- `server.servlet.context-path=/warehouse`, `server.port=8080`
- H2 em memória: `jdbc:h2:mem:warehouse`
- RabbitMQ host: `rabbitmq`, credenciais `admin/admin`
- `store.base-path`: URL base para chamar a API da Store de dentro do container (`http://storeapp:8081/store`)
- Config AMQP de disponibilidade de produto (exchange/queue/routing-key)

## Fluxo principal

1. Produto é criado no `Warehouse` (`POST /warehouse/products`) e replicado na `Store` via HTTP.
2. Compras acontecem na `Store` (`POST /store/products/{id}/purchase`).
3. O `Warehouse` gerencia estoque; quando disponibilidade muda, publica evento em RabbitMQ.
4. A `Store` consome o evento e atualiza a disponibilidade local.

## Desenvolvimento

- Código Java com Spring Boot, Lombok, RestClient e Spring AMQP
- H2 com `ddl-auto: create` para inicialização rápida
- Scripts `start-dev.sh` em cada serviço para dev

## Acesso rápido

- Store Swagger: `http://localhost:8081/store/swagger-ui/index.html#/`
- Warehouse base: `http://localhost:8080/warehouse`
- RabbitMQ UI: `http://localhost:15672` (admin/admin)
