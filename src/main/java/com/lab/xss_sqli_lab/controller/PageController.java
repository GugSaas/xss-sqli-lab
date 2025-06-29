package com.lab.xss_sqli_lab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
