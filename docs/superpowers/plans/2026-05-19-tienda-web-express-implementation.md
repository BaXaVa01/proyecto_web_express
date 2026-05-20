# Tienda Web Express Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Implement product search/filtering and a session-based shopping cart.

**Architecture:** 
- Search: Derived queries in JPA Repository + Controller `@RequestParam`.
- Cart: `HttpSession` storage with a dedicated `CarritoService` for totals and state management.

**Tech Stack:** Spring Boot, Spring Data JPA, Thymeleaf, Bootstrap 5.

---

### Task 1: Search & Filter Repository & Service

**Files:**
- Modify: `src/main/java/com/example/tienda_web_express/repository/ProductoRepository.java`
- Modify: `src/main/java/com/example/tienda_web_express/service/ProductoService.java`
- Modify: `src/main/java/com/example/tienda_web_express/service/impl/ProductoServiceImpl.java`
- Test: `src/test/java/com/example/tienda_web_express/ProductoSearchTests.java`

- [ ] **Step 1: Write failing test for combined search**
- [ ] **Step 2: Update ProductoRepository**
```java
List<Producto> findByNombreContainingIgnoreCaseAndCategoriaId(String nombre, Long categoriaId);
List<Producto> findByNombreContainingIgnoreCase(String nombre);
```
- [ ] **Step 3: Update ProductoService and Impl**
```java
// Service
List<ProductoDTO> buscarProductos(String nombre, Long categoriaId);
// Impl logic to handle nulls
```
- [ ] **Step 4: Run tests to verify**
- [ ] **Step 5: Commit**

### Task 2: Search & Filter UI

**Files:**
- Modify: `src/main/java/com/example/tienda_web_express/controller/ProductoController.java`
- Modify: `src/main/resources/templates/catalogo.html`

- [ ] **Step 1: Update ProductoController to handle search params**
- [ ] **Step 2: Update catalogo.html with search form and Bootstrap styling**
- [ ] **Step 3: Add "No results" message**
- [ ] **Step 4: Verify UI manually or with integration test**
- [ ] **Step 5: Commit**

### Task 3: Shopping Cart Logic

**Files:**
- Create: `src/main/java/com/example/tienda_web_express/dto/CarritoItemDTO.java`
- Create: `src/main/java/com/example/tienda_web_express/service/CarritoService.java`
- Create: `src/main/java/com/example/tienda_web_express/service/impl/CarritoServiceImpl.java`
- Test: `src/test/java/com/example/tienda_web_express/CarritoServiceTests.java`

- [ ] **Step 1: Create CarritoItemDTO**
- [ ] **Step 2: Write failing tests for CarritoService (add, remove, total)**
- [ ] **Step 3: Implement CarritoService using HttpSession**
- [ ] **Step 4: Run tests to verify**
- [ ] **Step 5: Commit**

### Task 4: Shopping Cart Controller & Views

**Files:**
- Create: `src/main/java/com/example/tienda_web_express/controller/CarritoController.java`
- Create: `src/main/resources/templates/carrito.html`
- Modify: `src/main/resources/templates/catalogo.html`
- Modify: `src/main/resources/templates/detalle.html`
- Modify: `src/main/resources/templates/index.html` (for nav)

- [ ] **Step 1: Create CarritoController with endpoints (agregar, ver, eliminar, finalizar)**
- [ ] **Step 2: Create carrito.html with Bootstrap table and totals**
- [ ] **Step 3: Add "Agregar al carrito" buttons to catalog and detail**
- [ ] **Step 4: Update navbar to include "Carrito" link**
- [ ] **Step 5: Verify full flow**
- [ ] **Step 6: Commit**
