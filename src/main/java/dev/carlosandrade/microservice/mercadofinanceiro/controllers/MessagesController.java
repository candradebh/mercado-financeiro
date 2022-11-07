package dev.carlosandrade.microservice.mercadofinanceiro.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/messages")
public class MessagesController {

    @GetMapping("/hello")
    public String hello() {
        Date dataAtual = new Date();
        return "Olá eu estou vivo! Hoje: " + dataAtual;
    }
}
