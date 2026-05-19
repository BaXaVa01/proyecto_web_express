package com.example.tienda_web_express.controller;

import com.example.tienda_web_express.dto.PedidoDTO;
import com.example.tienda_web_express.service.PedidoService;
import com.example.tienda_web_express.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;
    private final ProductoService productoService;

    @GetMapping("/pedido")
    public String mostrarFormulario(@RequestParam Long productoId, Model model) {
        PedidoDTO pedidoDto = PedidoDTO.builder()
                .productoId(productoId)
                .cantidad(1)
                .build();
        model.addAttribute("pedido", pedidoDto);
        model.addAttribute("producto", productoService.obtenerPorId(productoId));
        return "pedido";
    }

    @PostMapping("/pedido")
    public String procesarPedido(@Valid @ModelAttribute("pedido") PedidoDTO pedidoDto, 
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("producto", productoService.obtenerPorId(pedidoDto.getProductoId()));
            return "pedido";
        }
        
        try {
            PedidoDTO guardado = pedidoService.guardarPedido(pedidoDto);
            model.addAttribute("pedidoConfirmado", guardado);
            return "pedido-exitoso";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("producto", productoService.obtenerPorId(pedidoDto.getProductoId()));
            return "pedido";
        }
    }
}
