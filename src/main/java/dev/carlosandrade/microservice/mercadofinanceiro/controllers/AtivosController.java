package dev.carlosandrade.microservice.mercadofinanceiro.controllers;

import dev.carlosandrade.microservice.mercadofinanceiro.entity.Ativo;
import dev.carlosandrade.microservice.mercadofinanceiro.repository.AtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/ativos")
public class AtivosController {

    @Autowired
    AtivoRepository repository;

    @GetMapping("/")
    public ResponseEntity getAll() {

        List<Ativo> listRegisters = repository.findAll();

        if (!listRegisters.isEmpty()) {

            for (Ativo register : listRegisters) {
                String ticker = register.getTicker();
                register.add(linkTo(methodOn(AtivosController.class).read(ticker)).withRel("read"));
            }
            return ResponseEntity.ok(listRegisters);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity create(@Validated @RequestBody Ativo ativo) {

        //Ativo ativoBuscado = repository.findByTicker(ativo.getTicker()).stream().findFirst().orElse(null);
        Optional<Ativo> ativoBuscado = repository.findByTicker(ativo.getTicker());

        if (ativoBuscado.isPresent()) {

            return ResponseEntity.badRequest()
                    .body("O  ativo " + ativo.getTicker() + " ja esta cadastrado no sistema");
        }

        return ResponseEntity.ok(repository.save(ativo));


    }

    @GetMapping("{ticker}")
    public ResponseEntity read(@PathVariable String ticker) {
        Optional<Ativo> register = repository.findByTicker(ticker);

        if (!register.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(register.get().add(linkTo(methodOn(AtivosController.class).getAll()).withRel("getAll")));
        }
    }

    @PutMapping("{ticker}")
    public ResponseEntity update(@PathVariable String ticker, @Validated @RequestBody Ativo ativo) {
        return repository.findByTicker(ticker).map(existingAtivo -> {
            Ativo updatedAtivo = existingAtivo;
            updatedAtivo.setNome(ativo.getNome());
            updatedAtivo.setCnpj(ativo.getCnpj());
            return ResponseEntity.ok().body(repository.save(updatedAtivo));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{ticker}")
    public ResponseEntity delete(@PathVariable String ticker) {

        repository.findByTicker(ticker).map(existingAtivo -> {

            repository.delete(existingAtivo);

            return ResponseEntity.ok();

        });

        return ResponseEntity.notFound().build();

    }


}
