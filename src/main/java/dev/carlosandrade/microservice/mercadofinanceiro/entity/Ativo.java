package dev.carlosandrade.microservice.mercadofinanceiro.entity;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ativos")
public class Ativo extends RepresentationModel {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticker", nullable = false, unique = true)
    private String ticker;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "tipo_ativo", nullable = false)
    private String tipoAtivo;

    @Column(name = "cnpj")
    private String cnpj;
}
