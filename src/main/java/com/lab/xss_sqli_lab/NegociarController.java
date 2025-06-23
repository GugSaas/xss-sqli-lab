package com.lab.xss_sqli_lab;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NegociarController {

    @GetMapping("/negociar-logado")
    public String negociarLogado(@RequestParam(required = false) String busca, Model model) {
        model.addAttribute("busca", busca); // <--- valor refletido aqui
        return "negociar-logado"; // renderiza o template negociar-logado.html
    }
}
