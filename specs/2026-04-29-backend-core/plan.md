# Phase 3 — Plan: Backend Core

Each task group is independently committable. Complete them in order — later groups depend on earlier ones.

---

## Group 1 — Database schema

1. Create `src/main/resources/db/schema.sql`.
2. Write `CREATE TABLE club` with columns: `club_id SERIAL PRIMARY KEY`, `club_name VARCHAR(255) NOT NULL`, `city VARCHAR(100)`, `founded INTEGER`, `stadium VARCHAR(255)`, `capacity INTEGER`.
3. Write `CREATE TABLE player` with columns: `player_id SERIAL PRIMARY KEY`, `first_name VARCHAR(100) NOT NULL`, `last_name VARCHAR(100) NOT NULL`, `nationality VARCHAR(100)`, `birth_date DATE`, `birth_place VARCHAR(255)`, `position VARCHAR(50)`, `weight REAL`, `height REAL`, `club_id INTEGER REFERENCES club(club_id)`.

---

## Group 2 — JPA entity classes

1. Create `Club.java` in `model/`:
   - Annotate with `@Entity`, `@Table(name = "club")`.
   - Map every column from schema with `@Column`; `club_id` uses `@Id @GeneratedValue(strategy = GenerationType.IDENTITY)`.
   - Add `@OneToMany(mappedBy = "club")` list of players.
   - Add Lombok `@Getter @Setter @NoArgsConstructor @AllArgsConstructor`.
2. Create `Player.java` in `model/`:
   - Annotate with `@Entity`, `@Table(name = "player")`.
   - Map every column; `player_id` uses `@Id @GeneratedValue`.
   - Add `@ManyToOne @JoinColumn(name = "club_id")` for the club FK.
   - Add Lombok annotations.

---

## Group 3 — DTO classes

1. Create `ClubRequestDTO.java` — fields matching the POST/PATCH club body; annotate `clubName` with `@NotBlank`.
2. Create `ClubResponseDTO.java` — all club fields plus a `List<PlayerSummaryDTO>` for embedded players.
3. Create `PlayerSummaryDTO.java` — lightweight player projection (id, firstName, lastName, nationality) used inside club responses.
4. Create `PlayerRequestDTO.java` — fields matching the POST/PATCH player body; annotate `firstName` and `lastName` with `@NotBlank`; `clubId` with `@NotNull`.
5. Create `PlayerResponseDTO.java` — all player fields plus `clubName`.

---

## Group 4 — Repository interfaces

1. Create `ClubRepository.java` in `repository/` extending `JpaRepository<Club, Integer>`.
2. Create `PlayerRepository.java` in `repository/` extending `JpaRepository<Player, Integer>`.
3. Add `List<Player> findByClub_ClubId(Integer clubId)` to `PlayerRepository` for the `/api/clubs/:id/players` endpoint.

---

## Group 5 — Custom exceptions

1. Create `ClubNotFoundException.java` extending `RuntimeException` — constructor accepts `clubId`.
2. Create `PlayerNotFoundException.java` extending `RuntimeException` — constructor accepts `playerId`.

---

## Group 6 — Service classes

1. Create `ClubService.java` in `services/`:
   - `createClub(ClubRequestDTO)` → saves entity, returns `ClubResponseDTO`.
   - `getClubById(Integer)` → returns `ClubResponseDTO` or throws `ClubNotFoundException`.
   - `getAllClubs(int limit, int offset)` → returns paginated list.
   - `updateClub(Integer, ClubRequestDTO)` → partial update, returns `ClubResponseDTO` or throws `ClubNotFoundException`.
2. Create `PlayerService.java` in `services/`:
   - `createPlayer(PlayerRequestDTO)` → verifies club exists (throws `ClubNotFoundException` if not), saves entity, returns `PlayerResponseDTO`.
   - `getPlayerById(Integer)` → returns `PlayerResponseDTO` or throws `PlayerNotFoundException`.
   - `getPlayersByClubId(Integer)` → returns list of `PlayerResponseDTO`.
   - `updatePlayer(Integer, PlayerRequestDTO)` → partial update, returns `PlayerResponseDTO` or throws `PlayerNotFoundException`.

---

## Group 7 — Controller classes

1. Create `ClubController.java` in `controller/` annotated `@RestController @RequestMapping("/api/clubs")`:
   - `POST /api/clubs` → `201 ClubResponseDTO`
   - `GET /api/clubs` → `200 { clubs, total }` with `?limit` and `?offset` params
   - `GET /api/clubs/{id}` → `200 ClubResponseDTO`
   - `PATCH /api/clubs/{id}` → `200 ClubResponseDTO`
   - `GET /api/clubs/{id}/players` → `200 List<PlayerResponseDTO>`
2. Create `PlayerController.java` in `controller/` annotated `@RestController @RequestMapping("/api/players")`:
   - `POST /api/players` → `201 PlayerResponseDTO`
   - `GET /api/players/{id}` → `200 PlayerResponseDTO`
   - `PATCH /api/players/{id}` → `200 PlayerResponseDTO`

---

## Group 8 — Global exception handler

1. Create `GlobalExceptionHandler.java` in `controller/` annotated `@ControllerAdvice`:
   - `@ExceptionHandler(ClubNotFoundException.class)` → `404 { code: "CLUB_NOT_FOUND", message }`
   - `@ExceptionHandler(PlayerNotFoundException.class)` → `404 { code: "PLAYER_NOT_FOUND", message }`
   - `@ExceptionHandler(MethodArgumentNotValidException.class)` → `400 { code: "INVALID_REQUEST", errors: [...] }`
   - `@ExceptionHandler(Exception.class)` → `500 { code: "INTERNAL_ERROR", message }`
