package com.example.tienda_web_express.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoDTO {
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombreCliente;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Formato de correo inválido")
    private String correo;

    private String comentario;

    private LocalDateTime fechaPedido;

    private BigDecimal total;

    // Para el formulario simplificado
    @NotNull(message = "Debe seleccionar un producto")
    private Long productoId;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad mínima es 1")
    private Integer cantidad;
}
