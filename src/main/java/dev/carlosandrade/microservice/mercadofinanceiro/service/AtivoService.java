package dev.carlosandrade.microservice.mercadofinanceiro.service;

import dev.carlosandrade.microservice.mercadofinanceiro.controllers.AtivosController;
import dev.carlosandrade.microservice.mercadofinanceiro.entity.Ativo;
import dev.carlosandrade.microservice.mercadofinanceiro.repository.AtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class AtivoService {

    @Autowired
    AtivoRepository repository;


    public List<Ativo> getAll() {

        List<Ativo> listRegisters = repository.findAll();

        if (!listRegisters.isEmpty()) {

            for (Ativo register : listRegisters) {
                String ticker = register.getTicker();
                register.add(linkTo(methodOn(AtivosController.class).read(ticker)).withRel("read"));
            }
        }

        return listRegisters;
    }

    public Ativo update(String ticker, Ativo ativo) {

        Optional<Ativo> ativoExisting = repository.findByTickerIgnoreCase(ticker);
        if (ativoExisting.isPresent()) {
            Ativo updatedAtivo = ativoExisting.get();

            if (ativo.getNome() != null && !ativo.getNome().equals(updatedAtivo.getNome())) {
                updatedAtivo.setNome(ativo.getNome());
            }

            if (ativo.getCnpj() != null && !ativo.getCnpj().equals(updatedAtivo.getCnpj())) {
                updatedAtivo.setCnpj(ativo.getCnpj());
            }

            repository.save(updatedAtivo);

            return (Ativo) repository.save(updatedAtivo).add(linkTo(methodOn(AtivosController.class).getAll()).withRel("getAll"));

        } else {
            return null;
        }
    }

    public Ativo read(String ticker) {

        Optional<Ativo> register = repository.findByTickerIgnoreCase(ticker);
        return register.isPresent() ? (Ativo) register.get().add(linkTo(methodOn(AtivosController.class).getAll()).withRel("getAll")) : null;
    }

    public Ativo create(Ativo ativo) {

        Optional<Ativo> ativoBuscado = repository.findByTickerIgnoreCase(ativo.getTicker());
        return !ativoBuscado.isPresent() ? (Ativo) repository.save(ativo).add(linkTo(methodOn(AtivosController.class).getAll()).withRel("getAll")) : null;
    }

    public boolean delete(String ticker) {

        return repository.findByTickerIgnoreCase(ticker).map(existingAtivo -> {
            repository.delete(existingAtivo);
            return true;
        }).orElse(false);
    }
}
