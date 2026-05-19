package com.example.tienda_web_express.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_cliente", nullable = false, length = 150)
    private String nombreCliente;

    @Column(nullable = false, length = 150)
    private String correo;

    @Column(columnDefinition = "TEXT")
    private String comentario;

    @Column(name = "fecha_pedido", nullable = false)
    @Builder.Default
    private LocalDateTime fechaPedido = LocalDateTime.now();

    @Column(nullable = false, precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal total = BigDecimal.ZERO;
}
