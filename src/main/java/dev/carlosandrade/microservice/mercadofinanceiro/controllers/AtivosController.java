package dev.carlosandrade.microservice.mercadofinanceiro.controllers;

import dev.carlosandrade.microservice.mercadofinanceiro.entity.Ativo;
import dev.carlosandrade.microservice.mercadofinanceiro.service.AtivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ativos")
public class AtivosController {

    @Autowired
    AtivoService service;

    @GetMapping("/")
    public ResponseEntity getAll() {

        List<Ativo> listaAtivos = service.getAll();
        return !listaAtivos.isEmpty() ? ResponseEntity.ok(listaAtivos) : ResponseEntity.notFound().build();

    }

    @GetMapping("{ticker}")
    public ResponseEntity read(@PathVariable String ticker) {
        Ativo ativo = service.read(ticker);
        return ativo != null ? ResponseEntity.ok(ativo) : ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity create(@Validated @RequestBody Ativo ativo) {
        Ativo ativoCriado = service.create(ativo);
        return ativoCriado != null ? ResponseEntity.ok(ativo) : ResponseEntity.badRequest().body("Ativo ja exisite.");
    }


    @PutMapping("{ticker}")
    public ResponseEntity update(@PathVariable String ticker, @Validated @RequestBody Ativo ativo) {
        Ativo ativoAtualizado = service.update(ticker, ativo);
        return ativoAtualizado != null ? ResponseEntity.ok(ativoAtualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("{ticker}")
    public ResponseEntity delete(@PathVariable String ticker) {

        return service.delete(ticker) == true ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();

    }


}
