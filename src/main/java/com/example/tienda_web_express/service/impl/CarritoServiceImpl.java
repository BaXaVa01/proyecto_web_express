package com.example.tienda_web_express.service.impl;

import com.example.tienda_web_express.dto.CarritoItemDTO;
import com.example.tienda_web_express.dto.ProductoDTO;
import com.example.tienda_web_express.service.CarritoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarritoServiceImpl implements CarritoService {

    private final HttpSession session;
    private static final String CART_SESSION_KEY = "carrito";

    @SuppressWarnings("unchecked")
    private List<CarritoItemDTO> getCartFromSession() {
        List<CarritoItemDTO> cart = (List<CarritoItemDTO>) session.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute(CART_SESSION_KEY, cart);
        }
        return cart;
    }

    @Override
    public void agregarProducto(ProductoDTO producto) {
        List<CarritoItemDTO> cart = getCartFromSession();
        Optional<CarritoItemDTO> existingItem = cart.stream()
                .filter(item -> item.getProductoId().equals(producto.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setCantidad(existingItem.get().getCantidad() + 1);
        } else {
            cart.add(CarritoItemDTO.builder()
                    .productoId(producto.getId())
                    .nombre(producto.getNombre())
                    .precio(producto.getPrecio())
                    .imagen(producto.getImagen())
                    .cantidad(1)
                    .build());
        }
        session.setAttribute(CART_SESSION_KEY, cart);
    }

    @Override
    public void eliminarProducto(Long productoId) {
        List<CarritoItemDTO> cart = getCartFromSession();
        cart.removeIf(item -> item.getProductoId().equals(productoId));
        session.setAttribute(CART_SESSION_KEY, cart);
    }

    @Override
    public List<CarritoItemDTO> obtenerItems() {
        return getCartFromSession();
    }

    @Override
    public BigDecimal calcularTotal() {
        return getCartFromSession().stream()
                .map(CarritoItemDTO::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void limpiarCarrito() {
        session.removeAttribute(CART_SESSION_KEY);
    }

    @Override
    public int obtenerCantidadTotal() {
        return getCartFromSession().stream()
                .mapToInt(CarritoItemDTO::getCantidad)
                .sum();
    }
}
