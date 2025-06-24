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

    @GetMapping("/carteiras-logado")
    public String carteirasLogado() {
        return "carteiras-logado";
    }

    @GetMapping("/conteudo-logado")
    public String conteudoLogado() {
        return "conteudo-logado";
    }

    @GetMapping("/historico-logado")
    public String historicoLogado() {
        return "historico-logado";
    }

    @GetMapping("/home-logada")
    public String homeLogada() {
        return "home-logada";
    }

    @GetMapping("/perfil-contas")
    public String perfilContas() {
        return "perfil-contas";
    }

    @GetMapping("/perfil-dadospessoais")
    public String perfilDadosPessoais() {
        return "perfil-dadospessoais";
    }

    @GetMapping("/perfil-notificacoes")
    public String perfilNotificacoes() {
        return "perfil-notificacoes";
    }

    @GetMapping("/perfil-seguranca")
    public String perfilSeguranca() {
        return "perfil-seguranca";
    }

    @GetMapping("/perfil-suporte")
    public String perfilSuporte() {
        return "perfil-suporte";
    }
}
