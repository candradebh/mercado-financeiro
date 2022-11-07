package dev.carlosandrade.microservice.mercadofinanceiro.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.time.LocalDateTime;


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

    @Column(name = "preco")
    private double preco;


    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime created_at;


    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updated_at;


}
