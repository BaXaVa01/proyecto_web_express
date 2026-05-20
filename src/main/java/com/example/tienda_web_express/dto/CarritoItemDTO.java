package com.example.tienda_web_express.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoItemDTO {
    private Long productoId;
    private String nombre;
    private BigDecimal precio;
    private Integer cantidad;
    private String imagen;

    public BigDecimal getSubtotal() {
        return precio.multiply(new BigDecimal(cantidad));
    }
}
