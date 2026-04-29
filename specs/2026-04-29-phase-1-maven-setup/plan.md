# Phase 1 — Plan: Maven Setup

Each task group is independently committable. Complete them in order.

---

## Group 1 — Create `pom.xml`

1. Add the `spring-boot-starter-parent` as the Maven parent at the latest 3.x version.
2. Set `<groupId>football.data</groupId>`, `<artifactId>football-data</artifactId>`, `<version>0.0.1-SNAPSHOT</version>`.
3. Set the `<java.version>21</java.version>` property.
4. Add dependencies:
   - `spring-boot-starter-web`
   - `spring-boot-starter-thymeleaf`
   - `spring-boot-starter-data-jpa`
   - `spring-boot-starter-validation`
   - `postgresql` with `<scope>runtime</scope>`
   - `lombok` with `<optional>true</optional>`
   - `spring-boot-devtools` with `<scope>runtime</scope>` and `<optional>true</optional>`
   - `spring-boot-starter-test` with `<scope>test</scope>`
5. Add the `spring-boot-maven-plugin` in `<build><plugins>`, configured to exclude Lombok and DevTools from the final JAR.

---

## Group 2 — Create `src/main/resources/application.properties`

1. Create the directory path `src/main/resources/` if it does not exist.
2. Add datasource configuration using environment variable placeholders:
   ```
   spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/football_data}
   spring.datasource.username=${DB_USERNAME:postgres}
   spring.datasource.password=${DB_PASSWORD:}
   spring.datasource.driver-class-name=org.postgresql.Driver
   ```
3. Add JPA / Hibernate configuration:
   ```
   spring.jpa.hibernate.ddl-auto=none
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.format_sql=true
   ```
4. Add SQL initialisation mode (so schema.sql runs on startup in dev):
   ```
   spring.sql.init.mode=always
   ```
5. Set server port:
   ```
   server.port=8080
   ```

---

## Group 3 — Create `.gitignore`

1. Add standard Maven build output: `target/`
2. Add compiled class files: `*.class`
3. Add packaged artifacts: `*.jar`, `*.war`
4. Add IDE directories and files:
   - IntelliJ: `.idea/`, `*.iml`, `*.iws`, `*.ipr`
   - VS Code: `.vscode/`
   - Eclipse: `.classpath`, `.project`, `.settings/`
5. Add OS noise: `.DS_Store`, `Thumbs.db`
6. Add environment secrets: `.env`, `*.env`
