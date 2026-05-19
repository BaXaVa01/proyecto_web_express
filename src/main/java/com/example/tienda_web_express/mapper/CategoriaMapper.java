package com.example.tienda_web_express.mapper;

import com.example.tienda_web_express.dto.CategoriaDTO;
import com.example.tienda_web_express.entity.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {
    public CategoriaDTO toDto(Categoria entity) {
        if (entity == null) return null;
        return CategoriaDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .build();
    }

    public Categoria toEntity(CategoriaDTO dto) {
        if (dto == null) return null;
        return Categoria.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .build();
    }
}
