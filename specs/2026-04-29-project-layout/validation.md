# Phase 2 — Validation: Project Layout

All checks must pass before this phase is considered complete and the branch is mergeable.

---

## 1. Directory Tree Check

Confirm every directory listed in `requirements.md` exists. A quick way to inspect the full tree:

```bash
find src -type d | sort
```

**Expected output must include (at minimum):**

```
src/main/java/football/data/controller
src/main/java/football/data/services
src/main/java/football/data/repository
src/main/java/football/data/model
src/main/resources/db
src/main/resources/templates/fragments
src/main/resources/templates/error
src/main/resources/static/css
src/main/resources/static/js
src/main/resources/static/images
src/test/java/football/data
```

**Pass:** every line above appears in the output.
**Fail:** any directory is missing.

---

## 2. Clean Compile

```bash
mvn clean compile
```

**Pass:** exits with `BUILD SUCCESS` and zero errors or warnings about missing source roots.
**Checks:** Maven resolves the `src/main/java/football/data` package root; the compiler plugin finds all four sub-packages without complaint.

---

## 3. Test Compile

```bash
mvn test-compile
```

**Pass:** exits with `BUILD SUCCESS`.
**Checks:** `src/test/java/football/data/` is recognised as a valid test source root.

---

## 4. No Unintended Files

```bash
git status
```

**Pass:** only the new directories (and any files already inside them from Phase 1, e.g. `application.properties`) appear. No compiled `.class` files, no IDE artefacts.

---

## Definition of Done

- [ ] All directories from the scope table in `requirements.md` exist
- [ ] `mvn clean compile` → `BUILD SUCCESS`
- [ ] `mvn test-compile` → `BUILD SUCCESS`
- [ ] `git status` is clean (no unintended tracked files)
- [ ] PR reviewed and approved
- [ ] Branch merged into `main`
