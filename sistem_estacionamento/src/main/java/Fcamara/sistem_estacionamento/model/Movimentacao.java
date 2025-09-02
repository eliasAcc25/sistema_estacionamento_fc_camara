package Fcamara.sistem_estacionamento.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "rodizio_carros")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estabelecimento_id", nullable = false)
    private Estabelecimento estabelecimento;

    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculos veiculo;

    @Column(nullable = false)
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;

    public boolean temVeiculoNoMomento() {
        return dataHoraSaida == null;
    }
}
