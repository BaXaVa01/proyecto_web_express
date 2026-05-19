package com.example.tienda_web_express.mapper;

import com.example.tienda_web_express.dto.PedidoDTO;
import com.example.tienda_web_express.entity.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {
    public PedidoDTO toDto(Pedido entity) {
        if (entity == null) return null;
        return PedidoDTO.builder()
                .id(entity.getId())
                .nombreCliente(entity.getNombreCliente())
                .correo(entity.getCorreo())
                .comentario(entity.getComentario())
                .fechaPedido(entity.getFechaPedido())
                .total(entity.getTotal())
                .build();
    }

    public Pedido toEntity(PedidoDTO dto) {
        if (dto == null) return null;
        return Pedido.builder()
                .id(dto.getId())
                .nombreCliente(dto.getNombreCliente())
                .correo(dto.getCorreo())
                .comentario(dto.getComentario())
                .fechaPedido(dto.getFechaPedido())
                .total(dto.getTotal())
                .build();
    }
}
