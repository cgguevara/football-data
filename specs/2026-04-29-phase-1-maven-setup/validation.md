# Phase 1 — Validation: Maven Setup

All checks must pass before this phase is considered complete and the branch is mergeable.

---

## 1. Dependency Resolution

```bash
mvn dependency:resolve
```

**Pass:** exits with `BUILD SUCCESS` and no `MISSING` or `resolution error` lines.
**Checks:** all declared dependencies (including Lombok, DevTools, PostgreSQL driver) are downloadable from Maven Central.

---

## 2. Clean Compile

```bash
mvn clean compile
```

**Pass:** exits with `BUILD SUCCESS`.
**Checks:** the pom.xml is syntactically valid, the Java version is accepted, and the Spring Boot parent is resolvable.

---

## 3. Test Phase (no tests yet)

```bash
mvn test
```

**Pass:** exits with `BUILD SUCCESS` and reports `Tests run: 0`.
**Checks:** the test infrastructure (`spring-boot-starter-test`) is on the classpath and the Maven Surefire plugin runs without error.

---

## 4. application.properties Keys

Open `src/main/resources/application.properties` and confirm every key listed in `requirements.md` is present. No key may have a hardcoded secret value (passwords must be empty or use `${ENV_VAR}` syntax).

---

## 5. .gitignore Coverage

Run:
```bash
git check-ignore -v target/
git check-ignore -v .env
git check-ignore -v .idea/
```

**Pass:** each command prints a match line (not empty output).

---

## 6. No Unintended Files Tracked

```bash
git status
```

**Pass:** only `pom.xml`, `src/main/resources/application.properties`, and `.gitignore` appear as new/modified files. The `target/` directory and any IDE files are absent from the output.

---

## Definition of Done

- [ ] All 6 checks above pass
- [ ] `pom.xml` reviewed and approved in PR
- [ ] No secrets or credentials committed
- [ ] Branch merged into `main`
