# Tienda Web Express Upgrades Design Spec

## Overview
Implement advanced search/filtering and a session-based shopping cart for the "Tienda Web Express" Spring Boot application.

## 1. Search & Filters Module

### Architecture
- **Repository Layer**: Add a custom query or derived query method to `ProductoRepository` to handle combined filtering (name AND category).
- **Service Layer**: Update `ProductoService` to accept optional filters and return a filtered list of `ProductoDTO`.
- **Controller Layer**: Modify `ProductoController` to handle `@RequestParam` for `nombre` and `categoriaId`.
- **View Layer**: Update `catalogo.html` with a Bootstrap search bar and category selector.

### Data Flow
1. User enters name and/or selects category in UI.
2. Form submits GET request to `/productos`.
3. Controller extracts params and calls Service.
4. Repository executes query (likely `findByNombreContainingIgnoreCaseAndCategoriaId` or a custom `@Query`).
5. Results displayed in Thymeleaf template.

### UI/UX
- Search bar (text input) + Category dropdown + "Buscar" button.
- "Limpiar filtros" button to reset.
- Empty results message: "No se encontraron productos."
- Maintain selected filters in the UI after submission using `model.addAttribute`.

## 2. Shopping Cart Module

### Architecture
- **Session Management**: Use `HttpSession` to store a `Carrito` object (or a `Map<Long, Integer>` representing productId and quantity).
- **Controller Layer**: `CarritoController` to handle:
    - `POST /carrito/agregar`: Add product to session.
    - `GET /carrito`: View cart contents.
    - `POST /carrito/eliminar`: Remove item.
    - `POST /carrito/finalizar`: Clear cart and show success.
- **Service Layer**: `CarritoService` to manage business logic (totals calculation).

### Data Flow
1. User clicks "Agregar al carrito" in catalog/detail.
2. Request goes to `CarritoController`, updates `HttpSession`.
3. User navigates to `/carrito`.
4. Controller retrieves cart from session, hydrates with full `ProductoDTO` details from DB.
5. Totals calculated and displayed.

### UI/UX
- `/carrito` page with a table: Item, Price, Quantity, Subtotal, Actions (Delete).
- Summary card with "Total General".
- "Finalizar Pedido" button to clear cart.
- Cart icon in header showing item count (optional but good practice).

## 3. Tech Stack
- Spring Boot 3+
- Spring Data JPA
- Thymeleaf
- Bootstrap 5 (Standardized UI)
- HttpSession (Cart storage)

## 4. Testing Strategy
- **Unit Tests**: Test filtering logic in `ProductoService`. Test cart logic in `CarritoService`.
- **Integration Tests**: Verify Repository queries.
- **TDD**: Write failing tests for search and cart before implementation.
