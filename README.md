# FootballSystem (Spring Boot + PostgreSQL + Flyway)

Structure matches the Electronics-Store sample:
- config
- controller
- dto
- mapper (MapStruct)
- model (JPA)
- repository
- service
- serviceImpl
- db/migration (Flyway)
- test
- docker (docker-compose + Dockerfile)

## Run locally
1) Start PostgreSQL (example using docker):
```bash
docker compose up -d db
```
2) Run app:
```bash
./gradlew bootRun
```
3) Basic auth (from application.properties):
- user: admin
- pass: admin

## API
- `/api/teams`
- `/api/players`
- `/api/matches`

## Git Flow
Changes for feature branch: ManyToMany Match-Referee.
