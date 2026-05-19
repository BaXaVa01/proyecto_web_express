package com.example.tienda_web_express.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaDTO {
    private Long id;
    @NotBlank(message = "El nombre de la categoría es obligatorio")
    private String nombre;
}
