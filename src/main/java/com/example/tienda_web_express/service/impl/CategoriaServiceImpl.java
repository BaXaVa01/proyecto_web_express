package com.example.tienda_web_express.service.impl;

import com.example.tienda_web_express.dto.CategoriaDTO;
import com.example.tienda_web_express.mapper.CategoriaMapper;
import com.example.tienda_web_express.repository.CategoriaRepository;
import com.example.tienda_web_express.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Override
    public List<CategoriaDTO> listarTodas() {
        return categoriaRepository.findAll().stream()
                .map(categoriaMapper::toDto)
                .collect(Collectors.toList());
    }
}
