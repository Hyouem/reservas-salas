# Reservas de Salas

Projeto 32/52 — Organize reservas de salas com horário e responsável.

## Stack
Java 25, Spring Boot, PostgreSQL 17, Flyway, React 19, esbuild, Docker Compose, Playwright.

## API
- `GET /api/health` — saúde da API
- `GET /api/entries` — listar registros
- `POST /api/entries` — criar registro (`room, responsible, startAt` obrigatórios)
- `DELETE /api/entries/{id}` — excluir registro

O cabeçalho `X-Tenant` identifica um workspace demonstrativo, **não é autenticação nem isolamento seguro entre usuários**.

## Evidência real
![Aplicação em execução](docs/images/funcionamento.png)

A imagem é criada pelo script de publicação somente após o frontend e a API responderem, com um registro persistido.

## Execução
`docker compose up -d --build`

Frontend: http://localhost:5192 | API: http://localhost:8202/api/health

## Critérios de conclusão do MVP
Build Java e frontend, PostgreSQL saudável, GET/POST/DELETE funcionando, screenshot real versionado, push público e CI concluída com sucesso.

## Limites do MVP
Registro CRUD por workspace demonstrativo. Sem autenticação ou regras avançadas de conciliação, conflitos de reserva, notificações ou agendamento automático.
