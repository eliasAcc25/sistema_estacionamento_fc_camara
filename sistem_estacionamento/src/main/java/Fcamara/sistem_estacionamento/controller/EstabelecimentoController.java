package Fcamara.sistem_estacionamento.controller;

import Fcamara.sistem_estacionamento.model.Estabelecimento;
import Fcamara.sistem_estacionamento.model.Movimentacao;
import Fcamara.sistem_estacionamento.model.Veiculos;
import Fcamara.sistem_estacionamento.repository.EstabelecimentoRepository;
import Fcamara.sistem_estacionamento.repository.MovimentacaoRepository;
import Fcamara.sistem_estacionamento.repository.VeiculoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@Tag(name = "Sistema de Estacionamento", description = "API para gerenciamento de estacionamento de carros e motos")
public class EstabelecimentoController {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    // ========== ESTABELECIMENTO ==========
    @PostMapping("/estabelecimento")
    @Operation(summary = "Criar estabelecimento", description = "Cria um novo estabelecimento no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estabelecimento criado com sucesso",
                content = @Content(schema = @Schema(implementation = Estabelecimento.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public Estabelecimento criarEstabelecimento(
            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Dados do estabelecimento",
                content = @Content(
                    examples = @ExampleObject(
                        value = "{\n" +
                               "  \"nome\": \"Estacionamento FCamara\",\n" +
                               "  \"cnpj\": \"12.345.678/0001-90\",\n" +
                               "  \"endereco\": \"Rua das Flores, 123 - São Paulo/SP\",\n" +
                               "  \"telefone\": \"(11) 99999-9999\",\n" +
                               "  \"vagasMotos\": \"50\",\n" +
                               "  \"vagasCarros\": \"100\"\n" +
                               "}"
                    )
                )
            ) Estabelecimento estabelecimento) {
        return estabelecimentoRepository.save(estabelecimento);
    }

    @GetMapping("/estabelecimentos")
    @Operation(summary = "Listar estabelecimentos", description = "Lista todos os estabelecimentos cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de estabelecimentos")
    public List<Estabelecimento> listarEstabelecimentos() {
        return estabelecimentoRepository.findAll();
    }

    @GetMapping("/estabelecimento/{id}")
    @Operation(summary = "Buscar estabelecimento", description = "Busca um estabelecimento específico pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estabelecimento encontrado"),
        @ApiResponse(responseCode = "404", description = "Estabelecimento não encontrado")
    })
    public ResponseEntity<Estabelecimento> buscarEstabelecimento(
            @Parameter(description = "ID do estabelecimento", required = true) @PathVariable Long id) {
        Optional<Estabelecimento> estabelecimento = estabelecimentoRepository.findById(id);
        if (estabelecimento.isPresent()) {
            return ResponseEntity.ok(estabelecimento.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/estabelecimento/{id}")
    @Operation(summary = "Atualizar estabelecimento", description = "Atualiza os dados de um estabelecimento existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estabelecimento atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Estabelecimento não encontrado")
    })
    public ResponseEntity<Estabelecimento> atualizarEstabelecimento(
            @Parameter(description = "ID do estabelecimento", required = true) @PathVariable Long id,
            @RequestBody Estabelecimento estabelecimentoAtualizado) {
        Optional<Estabelecimento> estabelecimentoExistente = estabelecimentoRepository.findById(id);
        if (estabelecimentoExistente.isPresent()) {
            Estabelecimento estabelecimento = estabelecimentoExistente.get();
            estabelecimento.setNome(estabelecimentoAtualizado.getNome());
            estabelecimento.setCnpj(estabelecimentoAtualizado.getCnpj());
            estabelecimento.setEndereco(estabelecimentoAtualizado.getEndereco());
            estabelecimento.setTelefone(estabelecimentoAtualizado.getTelefone());
            estabelecimento.setVagasMotos(estabelecimentoAtualizado.getVagasMotos());
            estabelecimento.setVagasCarros(estabelecimentoAtualizado.getVagasCarros());
            return ResponseEntity.ok(estabelecimentoRepository.save(estabelecimento));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/estabelecimento/{id}")
    @Operation(summary = "Excluir estabelecimento", description = "Remove um estabelecimento do sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Estabelecimento excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Estabelecimento não encontrado")
    })
    public ResponseEntity<Void> excluirEstabelecimento(
            @Parameter(description = "ID do estabelecimento", required = true) @PathVariable Long id) {
        Optional<Estabelecimento> estabelecimento = estabelecimentoRepository.findById(id);
        if (estabelecimento.isPresent()) {
            estabelecimentoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // ========== VEÍCULO ==========
    @PostMapping("/veiculo")
    @Operation(summary = "Cadastrar veículo", description = "Cadastra um novo veículo no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Veículo cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public Veiculos criarVeiculo(
            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Dados do veículo",
                content = @Content(
                    examples = @ExampleObject(
                        value = "{\n" +
                               "  \"marca\": \"Toyota\",\n" +
                               "  \"modelo\": \"Corolla\",\n" +
                               "  \"cor\": \"Prata\",\n" +
                               "  \"placa\": \"ABC-1234\",\n" +
                               "  \"tipo\": \"CARRO\"\n" +
                               "}"
                    )
                )
            ) Veiculos veiculo) {
        return veiculoRepository.save(veiculo);
    }

    @GetMapping("/veiculos")
    @Operation(summary = "Listar veículos", description = "Lista todos os veículos cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de veículos")
    public List<Veiculos> listarVeiculos() {
        return veiculoRepository.findAll();
    }

    @GetMapping("/veiculo/{id}")
    @Operation(summary = "Buscar veículo", description = "Busca um veículo específico pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Veículo encontrado"),
        @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })
    public ResponseEntity<Veiculos> buscarVeiculo(
            @Parameter(description = "ID do veículo", required = true) @PathVariable Long id) {
        Optional<Veiculos> veiculo = veiculoRepository.findById(id);
        if (veiculo.isPresent()) {
            return ResponseEntity.ok(veiculo.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/veiculo/{id}")
    @Operation(summary = "Atualizar veículo", description = "Atualiza os dados de um veículo existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Veículo atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })
    public ResponseEntity<Veiculos> atualizarVeiculo(
            @Parameter(description = "ID do veículo", required = true) @PathVariable Long id,
            @RequestBody Veiculos veiculoAtualizado) {
        Optional<Veiculos> veiculoExistente = veiculoRepository.findById(id);
        if (veiculoExistente.isPresent()) {
            Veiculos veiculo = veiculoExistente.get();
            veiculo.setMarca(veiculoAtualizado.getMarca());
            veiculo.setModelo(veiculoAtualizado.getModelo());
            veiculo.setCor(veiculoAtualizado.getCor());
            veiculo.setPlaca(veiculoAtualizado.getPlaca());
            veiculo.setTipo(veiculoAtualizado.getTipo());
            return ResponseEntity.ok(veiculoRepository.save(veiculo));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/veiculo/{id}")
    @Operation(summary = "Excluir veículo", description = "Remove um veículo do sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Veículo excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })
    public ResponseEntity<Void> excluirVeiculo(
            @Parameter(description = "ID do veículo", required = true) @PathVariable Long id) {
        Optional<Veiculos> veiculo = veiculoRepository.findById(id);
        if (veiculo.isPresent()) {
            veiculoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // ========== ENTRADA/SAÍDA ==========
    @PostMapping("/entrada")
    @Operation(summary = "Registrar entrada", description = "Registra a entrada de um veículo no estacionamento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Entrada registrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Veículo ou estabelecimento não encontrado")
    })
    public ResponseEntity<String> entrada(
            @Parameter(description = "ID do veículo", required = true) @RequestParam Long veiculoId,
            @Parameter(description = "ID do estabelecimento", required = true) @RequestParam Long estabelecimentoId) {
        Optional<Veiculos> veiculoOpt = veiculoRepository.findById(veiculoId);
        Optional<Estabelecimento> estabelecimentoOpt = estabelecimentoRepository.findById(estabelecimentoId);

        if (veiculoOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Veículo não encontrado!");
        }
        if (estabelecimentoOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Estabelecimento não encontrado!");
        }

        Veiculos veiculo = veiculoOpt.get();
        Estabelecimento estabelecimento = estabelecimentoOpt.get();

        Movimentacao mov = new Movimentacao();
        mov.setVeiculo(veiculo);
        mov.setEstabelecimento(estabelecimento);
        mov.setDataHoraEntrada(LocalDateTime.now());

        movimentacaoRepository.save(mov);
        return ResponseEntity.ok("Veículo " + veiculo.getPlaca() + " entrou no estacionamento!");
    }

    @PostMapping("/saida")
    @Operation(summary = "Registrar saída", description = "Registra a saída de um veículo do estacionamento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Saída registrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Veículo não encontrado no estacionamento")
    })
    public ResponseEntity<String> saida(
            @Parameter(description = "ID do veículo", required = true) @RequestParam Long veiculoId) {
        var movimentacoes = movimentacaoRepository.findVeiculosAtualmenteEstacionados();
        if (movimentacoes != null) {
            for (Movimentacao m : movimentacoes) {
                if (m.getVeiculo().getId().equals(veiculoId)) {
                    m.setDataHoraSaida(LocalDateTime.now());
                    movimentacaoRepository.save(m);
                    return ResponseEntity.ok("Veículo " + m.getVeiculo().getPlaca() + " saiu do estacionamento!");
                }
            }
        }
        return ResponseEntity.badRequest().body("Veículo não encontrado no estacionamento!");
    }

    @GetMapping("/presentes")
    @Operation(summary = "Veículos no estacionamento", description = "Lista todos os veículos atualmente no estacionamento")
    @ApiResponse(responseCode = "200", description = "Lista de veículos presentes")
    public List<Movimentacao> veiculosNoEstacionamento() {
        return movimentacaoRepository.findVeiculosAtualmenteEstacionados();
    }
}
