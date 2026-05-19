package com.example.tienda_web_express.controller;

import com.example.tienda_web_express.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ProductoService productoService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("productosDestacados", productoService.listarDestacados());
        return "index";
    }
}
