package Fcamara.sistem_estacionamento.repository;

import Fcamara.sistem_estacionamento.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    //FAZ UMA BUSCA EM VEICULOS PRESENTES NO ESTACIONAMENTO
    @Query("SELECT m FROM Movimentacao m WHERE m.dataHoraSaida IS NULL")
    List<Movimentacao> findVeiculosAtualmenteEstacionados();

    // BUSCAR POR ESTABELECIMENTO
    List<Movimentacao> findByEstabelecimentoId(Long estabelecimentoId);
}
