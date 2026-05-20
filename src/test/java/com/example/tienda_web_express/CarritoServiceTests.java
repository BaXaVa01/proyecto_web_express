package com.example.tienda_web_express;

import com.example.tienda_web_express.dto.CarritoItemDTO;
import com.example.tienda_web_express.dto.ProductoDTO;
import com.example.tienda_web_express.service.CarritoService;
import com.example.tienda_web_express.service.impl.CarritoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CarritoServiceTests {

    private CarritoService carritoService;
    private MockHttpSession session;

    @BeforeEach
    void setUp() {
        session = new MockHttpSession();
        carritoService = new CarritoServiceImpl(session);
    }

    @Test
    void whenAddProduct_thenCartContainsItem() {
        ProductoDTO product = ProductoDTO.builder()
                .id(1L)
                .nombre("Test Product")
                .precio(new BigDecimal("10.00"))
                .build();

        carritoService.agregarProducto(product);

        List<CarritoItemDTO> items = carritoService.obtenerItems();
        assertThat(items).hasSize(1);
        assertThat(items.get(0).getNombre()).isEqualTo("Test Product");
        assertThat(items.get(0).getCantidad()).isEqualTo(1);
    }

    @Test
    void whenCalculateTotal_thenResultIsCorrect() {
        carritoService.agregarProducto(ProductoDTO.builder().id(1L).precio(new BigDecimal("10.00")).build());
        carritoService.agregarProducto(ProductoDTO.builder().id(2L).precio(new BigDecimal("20.00")).build());

        BigDecimal total = carritoService.calcularTotal();
        assertThat(total).isEqualByComparingTo("30.00");
    }
}
