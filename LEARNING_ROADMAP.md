# First Words — App Roadmap (and re-skilling plan)

Feature-driven, not topic-driven: each milestone is a real piece of the
app. Android/Kotlin concepts get called out inline, learned by building
them, not studied in the abstract first. This file lives in the repo on
purpose — it survives even if a chat session doesn't.

Current state: `HomeScreen` renders a static hardcoded category list.
`HomeViewModel` is an empty stub. `SubcategoryScreen` is an empty grid
of 6 placeholder items. Modules: `app`, `core:common`,
`core:designsystem`, `feature:home`, with `build-logic` convention
plugins already set up.

Status legend: `[ ]` not started · `[~]` in progress · `[x]` done

## Milestone 1 — Home screen driven by real state (done)

- [x] Define `Category` as a proper domain model (moved to
      `dao/Category.kt`)
- [x] Create a `CategoryRepository` interface + in-memory
      implementation (`CategoryRepositoryImpl`)
- [x] Inject the repository into `HomeViewModel` via Hilt
      (`@Inject constructor` + `RepositoryModule` with `@Binds`)
- [x] Expose `StateFlow<HomeUIState>` from the ViewModel (manual
      `MutableStateFlow` + `collect`, `val` fields throughout)
- [x] `HomeScreen` collects that state with
      `collectAsStateWithLifecycle()`, grid driven by real data
- Concepts hit: Hilt DI, repository pattern, `StateFlow`/`stateIn`,
  unidirectional data flow, why `HomeUIState` fields should be `val`
  not `var`

## Milestone 2 — Room persistence for categories & subcategories

- [ ] `CategoryEntity` + `SubcategoryEntity` with a foreign key
      relation (design already sketched in chat)
- [ ] `@Relation`/`@Embedded` query to fetch a category with its
      subcategories in one shot
- [ ] Seed the 5 categories x 5 subcategories on first run
- [ ] Swap the in-memory repository impl from Milestone 1 for a
      Room-backed one — `HomeViewModel` shouldn't need to change at
      all, which is the whole point of the interface
- Concepts hit: Room entities/DAO/relations, migrations basics, Hilt
  modules (`@Provides`/`@Binds`), why swapping data sources didn't
  touch the ViewModel

## Milestone 3 — Subcategory screen, for real (done)

- [x] Navigation Compose: type-safe `HomeRoute`/`SubCategoryRoute`
      (moved into `feature:home/route/Route.kt` so both `app` and
      `feature:home` can see them), real `categoryId` passed through
- [x] `SubCategoryViewModel` reads `categoryId` via
      `SavedStateHandle.toRoute<SubCategoryRoute>()`, loads that
      category's words from the repository
- [x] Grid shows real subcategory words (`items { }` fixed,
      `@HiltViewModel` added, `feature:home` given its own Hilt/KSP
      build setup so injection actually works at runtime) — confirmed
      running on-device
- Concepts hit: Navigation Compose args, per-destination ViewModel
  scoping, `SavedStateHandle`

## Milestone 4 — Make a word "learnable" (the actual product feature)

- [ ] Tapping a word shows it large + says it out loud
      (`TextToSpeech`) — this is the core interaction for a kid's
      first-words app
- [ ] Track "practiced" / "learned" state per word, persisted in Room
- [ ] Simple visual feedback (animation) when a word is marked learned
- Concepts hit: Android system services (`TextToSpeech`), more Room
  writes/relations, Compose animations (`animate*AsState`)

## Milestone 5 — Settings + a second feature module

- [ ] New `feature:settings` module (practice modularization for real,
      not just reading about it)
- [ ] Sound on/off, maybe a child's name, stored in DataStore
      (not Room — this is exactly the "simple key-value" case)
- Concepts hit: DataStore, adding a new Gradle module correctly with
  the existing convention plugins

## Milestone 6 — Testing pass

- [ ] Fake `CategoryRepository` for tests (this is why it's an
      interface)
- [ ] Unit tests for `HomeViewModel`/`SubcategoryViewModel` (Turbine
      for the `StateFlow` assertions)
- [ ] Room DAO tests (in-memory database)
- [ ] A couple of Compose UI tests
- Concepts hit: test doubles, coroutine testing, Compose testing APIs

## Milestone 7 — Polish & 2026-current topics

- [ ] Adaptive layout pass (tablet/foldable) for the category grid
- [ ] Predictive back, Material You dynamic color check
- [ ] Optional: a Glance ("Word of the Day") home screen widget
- [ ] Baseline Profile once the app has enough real usage flows to
      profile
- Concepts hit: adaptive apps, Glance, Baseline Profiles

## Milestone 8 — CI/CD & release readiness

- [ ] GitHub Actions running build + tests on PRs
- [ ] R8/ProGuard + signing config
- Concepts hit: CI pipelines, release build config

---
Notes / running log (add dated entries as we go):

- 2026-08-12: Milestone 1 finished (Home screen fully wired to real
  state via Hilt + Flow). Jumped ahead into Milestone 3 (skipped Room
  for now, still using the in-memory repository) — navigation routes
  moved into `feature:home`, `SavedStateHandle.toRoute()` wired up.
  Two small things left before Milestone 3 is done: missing
  `@HiltViewModel` on `SubCategoryViewModel`, and the subcategory grid
  needs `items(...)` instead of a single `item { forEach }`. Both
  fixed same day — Milestone 3's core flow (tap category, see its real
  words) works end to end.
- 2026-08-12: Deliberately deferring Milestone 2 (Room) for now —
  staying on the in-memory repository.
- 2026-08-13: Milestone 3 confirmed fully working on-device (Hilt/KSP
  fix for `feature:home` sorted out that morning). Confirmed decision:
  "practiced" word tracking in Milestone 4 stays in-memory too, no
  Room yet — consistent with skipping Milestone 2 for now.
