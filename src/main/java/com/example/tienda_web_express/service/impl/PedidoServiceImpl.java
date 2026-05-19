package com.example.tienda_web_express.service.impl;

import com.example.tienda_web_express.dto.PedidoDTO;
import com.example.tienda_web_express.entity.DetallePedido;
import com.example.tienda_web_express.entity.Pedido;
import com.example.tienda_web_express.entity.Producto;
import com.example.tienda_web_express.mapper.PedidoMapper;
import com.example.tienda_web_express.repository.DetallePedidoRepository;
import com.example.tienda_web_express.repository.PedidoRepository;
import com.example.tienda_web_express.repository.ProductoRepository;
import com.example.tienda_web_express.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final DetallePedidoRepository detallePedidoRepository;
    private final ProductoRepository productoRepository;
    private final PedidoMapper pedidoMapper;

    @Override
    @Transactional
    public PedidoDTO guardarPedido(PedidoDTO pedidoDto) {
        Producto producto = productoRepository.findById(pedidoDto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (producto.getStock() < pedidoDto.getCantidad()) {
            throw new RuntimeException("Stock insuficiente");
        }

        BigDecimal subtotal = producto.getPrecio().multiply(new BigDecimal(pedidoDto.getCantidad()));

        Pedido pedido = Pedido.builder()
                .nombreCliente(pedidoDto.getNombreCliente())
                .correo(pedidoDto.getCorreo())
                .comentario(pedidoDto.getComentario())
                .fechaPedido(LocalDateTime.now())
                .total(subtotal)
                .build();

        pedido = pedidoRepository.save(pedido);

        DetallePedido detalle = DetallePedido.builder()
                .pedido(pedido)
                .producto(producto)
                .cantidad(pedidoDto.getCantidad())
                .precioUnitario(producto.getPrecio())
                .subtotal(subtotal)
                .build();

        detallePedidoRepository.save(detalle);

        // Actualizar stock
        producto.setStock(producto.getStock() - pedidoDto.getCantidad());
        productoRepository.save(producto);

        return pedidoMapper.toDto(pedido);
    }
}
