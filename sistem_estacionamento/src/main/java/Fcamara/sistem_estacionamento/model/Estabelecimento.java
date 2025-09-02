package Fcamara.sistem_estacionamento.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "estabelecimento")
@Schema(description = "Entidade que representa um estabelecimento de estacionamento")
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do estabelecimento", example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Nome do estabelecimento", example = "Estacionamento FCamara", required = true)
    private String nome;

    @Column(nullable = false)
    @Schema(description = "CNPJ do estabelecimento", example = "12.345.678/0001-90", required = true)
    private String cnpj;

    @Column(nullable = false)
    @Schema(description = "Endereço completo", example = "Rua das Flores, 123 - São Paulo/SP", required = true)
    private String endereco;

    @Column(nullable = false)
    @Schema(description = "Telefone de contato", example = "(11) 99999-9999", required = true)
    private String telefone;

    @Column(nullable = false)
    @Schema(description = "Quantidade de vagas para motos", example = "50", required = true)
    private Integer vagasMotos;

    @Column(nullable = false)
    @Schema(description = "Quantidade de vagas para carros", example = "100", required = true)
    private Integer vagasCarros;
}
