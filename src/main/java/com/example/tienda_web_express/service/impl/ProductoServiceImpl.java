package com.example.tienda_web_express.service.impl;

import com.example.tienda_web_express.dto.ProductoDTO;
import com.example.tienda_web_express.entity.Producto;
import com.example.tienda_web_express.mapper.ProductoMapper;
import com.example.tienda_web_express.repository.ProductoRepository;
import com.example.tienda_web_express.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    @Override
    public List<ProductoDTO> listarTodos() {
        return productoRepository.findAll().stream()
                .map(productoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> listarPorCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId).stream()
                .map(productoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> buscarProductos(String nombre, Long categoriaId) {
        List<Producto> productos;
        boolean hasNombre = nombre != null && !nombre.trim().isEmpty();
        boolean hasCategoria = categoriaId != null;

        if (hasNombre && hasCategoria) {
            productos = productoRepository.findByNombreContainingIgnoreCaseAndCategoriaId(nombre, categoriaId);
        } else if (hasNombre) {
            productos = productoRepository.findByNombreContainingIgnoreCase(nombre);
        } else if (hasCategoria) {
            productos = productoRepository.findByCategoriaId(categoriaId);
        } else {
            productos = productoRepository.findAll();
        }

        return productos.stream()
                .map(productoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .map(productoMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<ProductoDTO> listarDestacados() {
        // Por ahora devolvemos los primeros 4 como destacados
        return productoRepository.findAll().stream()
                .limit(4)
                .map(productoMapper::toDto)
                .collect(Collectors.toList());
    }
}
