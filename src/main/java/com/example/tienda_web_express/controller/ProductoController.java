package com.example.tienda_web_express.controller;

import com.example.tienda_web_express.service.CategoriaService;
import com.example.tienda_web_express.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    @GetMapping
    public String catalogo(@RequestParam(required = false) Long categoriaId, 
                           @RequestParam(required = false) String nombre, 
                           Model model) {
        model.addAttribute("productos", productoService.buscarProductos(nombre, categoriaId));
        model.addAttribute("categorias", categoriaService.listarTodas());
        model.addAttribute("categoriaSeleccionada", categoriaId);
        model.addAttribute("nombreBusqueda", nombre);
        return "catalogo";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        model.addAttribute("producto", productoService.obtenerPorId(id));
        return "detalle";
    }
}
