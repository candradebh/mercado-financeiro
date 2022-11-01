package dev.carlosandrade.microservice.mercadofinanceiro.repository;

import dev.carlosandrade.microservice.mercadofinanceiro.entity.Ativo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AtivoRepository extends JpaRepository<Ativo, Long> {


    Optional<Ativo> findByTicker(String ticker);


}