package dev.carlosandrade.microservice.mercadofinanceiro;

import dev.carlosandrade.microservice.mercadofinanceiro.entity.Ativo;
import dev.carlosandrade.microservice.mercadofinanceiro.repository.AtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MercadoFinanceiroApplication implements CommandLineRunner {

	@Autowired
	AtivoRepository ativoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MercadoFinanceiroApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {


		Ativo ativo1 = Ativo.builder().ticker("VALE3").nome("VALE ON").tipoAtivo("Ações").build();
		Ativo ativo2 = Ativo.builder().ticker("PETR3").nome("PETROBRAS ON").tipoAtivo("Ações").build();
		Ativo ativo3 = Ativo.builder().ticker("PETR4").nome("PETROBRAS PN").tipoAtivo("Ações").build();

		ativoRepository.save(ativo1);
		ativoRepository.save(ativo2);
		ativoRepository.save(ativo3);

	}

}
