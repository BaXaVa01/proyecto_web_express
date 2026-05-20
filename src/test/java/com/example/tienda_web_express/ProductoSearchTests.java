package com.example.tienda_web_express;

import com.example.tienda_web_express.entity.Categoria;
import com.example.tienda_web_express.entity.Producto;
import com.example.tienda_web_express.repository.CategoriaRepository;
import com.example.tienda_web_express.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@org.springframework.test.context.ActiveProfiles("test")
public class ProductoSearchTests {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    private Categoria tech;
    private Categoria clothing;

    @BeforeEach
    void setUp() {
        tech = Categoria.builder().nombre("Tech").build();
        clothing = Categoria.builder().nombre("Clothing").build();
        categoriaRepository.save(tech);
        categoriaRepository.save(clothing);

        productoRepository.save(Producto.builder().nombre("Mouse").precio(new BigDecimal("10.00")).categoria(tech).build());
        productoRepository.save(Producto.builder().nombre("Keyboard").precio(new BigDecimal("20.00")).categoria(tech).build());
        productoRepository.save(Producto.builder().nombre("T-Shirt").precio(new BigDecimal("15.00")).categoria(clothing).build());
    }

    @Test
    void whenSearchByName_thenReturnMatchingProducts() {
        List<Producto> results = productoRepository.findByNombreContainingIgnoreCase("mou");
        assertThat(results).hasSize(1);
    }

    @Test
    void whenSearchByNameAndCategory_thenReturnMatchingProducts() {
        List<Producto> results = productoRepository.findByNombreContainingIgnoreCaseAndCategoriaId("Mouse", tech.getId());
        assertThat(results).hasSize(1);

        results = productoRepository.findByNombreContainingIgnoreCaseAndCategoriaId("Mouse", clothing.getId());
        assertThat(results).isEmpty();
    }
}
