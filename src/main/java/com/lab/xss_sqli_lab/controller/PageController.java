package com.lab.xss_sqli_lab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/carteiras")
    public String carteirasLogado() {
        return "carteiras-logado";
    }

    @GetMapping("/conteudo")
    public String conteudoLogado() {
        return "conteudo-logado";
    }

    @GetMapping("/negociar")
    public String negociarLogado(@RequestParam(required = false) String busca, Model model) {
        model.addAttribute("busca", busca);
        return "negociar";
    }

    @GetMapping("/historico")
    public String historicoLogado() {
        return "historico-logado";
    }

    @GetMapping("/home")
    public String homeLogada() {
        return "home-logada";
    }

    @GetMapping("/perfil/contas")
    public String perfilContas() {
        return "perfil-contas";
    }

    @GetMapping("/perfil/dados")
    public String perfilDadosPessoais() {
        return "perfil-dadospessoais";
    }

    @GetMapping("/perfil/notificacoes")
    public String perfilNotificacoes() {
        return "perfil-notificacoes";
    }

    @GetMapping("/perfil/seguranca")
    public String perfilSeguranca() {
        return "perfil-seguranca";
    }

    @GetMapping("/perfil/suporte")
    public String perfilSuporte() {
        return "perfil-suporte";
    }

    @GetMapping("/suporte/tickets")
    public String suporteTickets() {
        return "suporte-tickets";
    }
}
