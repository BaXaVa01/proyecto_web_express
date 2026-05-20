package com.example.tienda_web_express.controller;

import com.example.tienda_web_express.dto.ProductoDTO;
import com.example.tienda_web_express.service.CarritoService;
import com.example.tienda_web_express.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService carritoService;
    private final ProductoService productoService;

    @GetMapping
    public String verCarrito(Model model) {
        model.addAttribute("items", carritoService.obtenerItems());
        model.addAttribute("total", carritoService.calcularTotal());
        return "carrito";
    }

    @PostMapping("/agregar")
    public String agregarAlCarrito(@RequestParam Long productoId) {
        ProductoDTO producto = productoService.obtenerPorId(productoId);
        if (producto != null) {
            carritoService.agregarProducto(producto);
        }
        return "redirect:/carrito";
    }

    @PostMapping("/eliminar")
    public String eliminarDelCarrito(@RequestParam Long productoId) {
        carritoService.eliminarProducto(productoId);
        return "redirect:/carrito";
    }

    @PostMapping("/finalizar")
    public String finalizarPedido() {
        carritoService.limpiarCarrito();
        return "redirect:/productos?mensaje=pedido_exitoso";
    }
}
