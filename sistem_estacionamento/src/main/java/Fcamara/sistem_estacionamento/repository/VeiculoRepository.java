package Fcamara.sistem_estacionamento.repository;

import Fcamara.sistem_estacionamento.model.Veiculos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculos, Long> {
    Veiculos findByPlaca(String placa);
}
