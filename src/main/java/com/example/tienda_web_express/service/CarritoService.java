package com.example.tienda_web_express.service;

import com.example.tienda_web_express.dto.CarritoItemDTO;
import com.example.tienda_web_express.dto.ProductoDTO;
import java.math.BigDecimal;
import java.util.List;

public interface CarritoService {
    void agregarProducto(ProductoDTO producto);
    void eliminarProducto(Long productoId);
    List<CarritoItemDTO> obtenerItems();
    BigDecimal calcularTotal();
    void limpiarCarrito();
    int obtenerCantidadTotal();
}
