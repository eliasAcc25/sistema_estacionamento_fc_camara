package Fcamara.sistem_estacionamento.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "veiculos")
@Schema(description = "Entidade que representa um veículo cadastrado no sistema")
public class Veiculos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do veículo", example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Marca do veículo", example = "Toyota", required = true)
    private String marca;

    @Column(nullable = false)
    @Schema(description = "Modelo do veículo", example = "Corolla", required = true)
    private String modelo;

    @Column(nullable = false)
    @Schema(description = "Cor do veículo", example = "Prata", required = true)
    private String cor;

    @Column(nullable = false, unique = true)
    @Schema(description = "Placa do veículo (única)", example = "ABC-1234", required = true)
    private String placa;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Tipo do veículo", example = "CARRO", allowableValues = {"CARRO", "MOTO"}, required = true)
    private TipoVeiculo tipo;
}
