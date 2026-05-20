package com.example.tienda_web_express;

import com.example.tienda_web_express.dto.ProductoDTO;
import com.example.tienda_web_express.entity.Categoria;
import com.example.tienda_web_express.entity.Producto;
import com.example.tienda_web_express.mapper.ProductoMapper;
import com.example.tienda_web_express.repository.ProductoRepository;
import com.example.tienda_web_express.service.impl.ProductoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTests {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private ProductoMapper productoMapper;

    @InjectMocks
    private ProductoServiceImpl productoService;

    @Test
    void whenSearchingWithBothFilters_thenCallCorrectRepoMethod() {
        String name = "Mouse";
        Long catId = 1L;
        Producto p = new Producto();
        p.setNombre(name);
        
        when(productoRepository.findByNombreContainingIgnoreCaseAndCategoriaId(name, catId))
            .thenReturn(List.of(p));
        when(productoMapper.toDto(p)).thenReturn(new ProductoDTO());

        List<ProductoDTO> results = productoService.buscarProductos(name, catId);

        assertThat(results).hasSize(1);
    }
}
