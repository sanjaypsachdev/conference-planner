# Conference Planner

A Spring Boot REST API for managing conference sessions: list, create, update, and delete sessions with optional filtering by track and speaker.

---

## Tech stack

| Layer        | Technology                    |
| ------------ | ----------------------------- |
| Framework    | Spring Boot 4.0.3             |
| Java         | 25                            |
| Web          | Spring Web MVC                |
| Data         | Spring Data JPA               |
| Database     | H2 (in-memory, runtime)        |
| Validation   | Jakarta Bean Validation       |
| Build        | Maven                         |

---

## Running the application

**Build and run:**

```bash
./mvnw spring-boot:run
```

**Run tests:**

```bash
./mvnw test
```

The API is available at **http://localhost:8080**. On startup, sample sessions are seeded via `DataSeeder`.

---

## API overview

Base path: **`/api`**

### Health

| Method | Path           | Description              |
|--------|----------------|--------------------------|
| `GET`  | `/api/health`  | Health check (status, timestamp, app name) |

### Sessions

| Method   | Path                  | Description                          |
|----------|-----------------------|--------------------------------------|
| `GET`    | `/api/sessions`       | List all sessions (optional query: `track`, `speaker`) |
| `GET`    | `/api/sessions/{id}`  | Get a session by ID                  |
| `POST`   | `/api/sessions`       | Create a session (body: see below)    |
| `PUT`    | `/api/sessions/{id}`  | Update a session by ID               |
| `DELETE` | `/api/sessions/{id}`  | Delete a session by ID               |

**Create/update body (JSON):**

- `title` (string, required, 3–120 chars)
- `speaker` (string, required, 2–80 chars)
- `startTime` (ISO-8601 date-time, required)
- `endTime` (ISO-8601 date-time, required; must be after `startTime`)
- `room` (string, required)
- `track` (string, optional, max 40 chars)

**Example – create session:**

```bash
curl -X POST http://localhost:8080/api/sessions \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Intro to Spring Boot",
    "speaker": "Jane Doe",
    "startTime": "2025-06-01T09:00:00",
    "endTime": "2025-06-01T10:30:00",
    "room": "Hall A",
    "track": "Backend"
  }'
```

**Example – list sessions with filter:**

```bash
curl "http://localhost:8080/api/sessions?track=Backend&speaker=Jane"
```

---

## Project structure

```
src/main/java/com/sanjaypsachdev/conferenceplanner/
├── ConferencePlannerApplication.java   # Entry point
├── api/                                # REST layer
│   ├── HealthController.java           # GET /api/health
│   ├── SessionsController.java        # CRUD /api/sessions
│   ├── SessionCreationRequest.java    # POST body DTO
│   ├── SessionUpdateRequest.java      # PUT body DTO
│   └── SessionResponse.java           # Response DTO
├── config/
│   └── DataSeeder.java                # Initial sample sessions
├── data/
│   ├── SessionEntity.java             # JPA entity
│   └── SessionRepository.java        # JPA repository
└── service/
    └── SessionService.java            # Session business logic
```

---

## Reference documentation

- [Apache Maven](https://maven.apache.org/guides/index.html)
- [Spring Boot Maven Plugin](https://docs.spring.io/spring-boot/4.0.3/maven-plugin)
- [Build OCI image](https://docs.spring.io/spring-boot/4.0.3/maven-plugin/build-image.html)
- [Validation](https://docs.spring.io/spring-boot/4.0.3/reference/io/validation.html)
- [Spring Web](https://docs.spring.io/spring-boot/4.0.3/reference/web/servlet.html)

### Guides

- [Validating form input](https://spring.io/guides/gs/validating-form-input/)
- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
- [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
- [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

---

## Maven parent overrides

The project POM overrides empty `<license>`, `<developers>`, and related elements from the Spring Boot parent to avoid inheriting unwanted metadata. If you switch to a different parent and want that inheritance, remove those overrides from `pom.xml`.
