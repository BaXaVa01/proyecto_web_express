package com.example.tienda_web_express.service;

import com.example.tienda_web_express.dto.ProductoDTO;
import java.util.List;

public interface ProductoService {
    List<ProductoDTO> listarTodos();
    List<ProductoDTO> listarPorCategoria(Long categoriaId);
    ProductoDTO obtenerPorId(Long id);
    List<ProductoDTO> listarDestacados(); // Simulación
}
