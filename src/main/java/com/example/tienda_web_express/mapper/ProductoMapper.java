package com.example.tienda_web_express.mapper;

import com.example.tienda_web_express.dto.ProductoDTO;
import com.example.tienda_web_express.entity.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {
    public ProductoDTO toDto(Producto entity) {
        if (entity == null) return null;
        return ProductoDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .precio(entity.getPrecio())
                .imagen(entity.getImagen())
                .stock(entity.getStock())
                .categoriaId(entity.getCategoria() != null ? entity.getCategoria().getId() : null)
                .categoriaNombre(entity.getCategoria() != null ? entity.getCategoria().getNombre() : null)
                .build();
    }

    public Producto toEntity(ProductoDTO dto) {
        if (dto == null) return null;
        return Producto.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .imagen(dto.getImagen())
                .stock(dto.getStock())
                .build();
    }
}
