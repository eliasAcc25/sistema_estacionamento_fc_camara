package Fcamara.sistem_estacionamento.controller;

import Fcamara.sistem_estacionamento.model.Estabelecimento;
import Fcamara.sistem_estacionamento.model.Movimentacao;
import Fcamara.sistem_estacionamento.model.TipoVeiculo;
import Fcamara.sistem_estacionamento.model.Veiculos;
import Fcamara.sistem_estacionamento.repository.EstabelecimentoRepository;
import Fcamara.sistem_estacionamento.repository.MovimentacaoRepository;
import Fcamara.sistem_estacionamento.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@DisplayName("Testes Simples - EstabelecimentoController")
public class EstabelecimentoControllerTeste {

    //  MOCKS - Simulam as dependências
    @Mock
    private EstabelecimentoRepository estabelecimentoRepository;

    @Mock
    private MovimentacaoRepository movimentacaoRepository;

    @Mock
    private VeiculoRepository veiculoRepository;

    // CONTROLLER
    @InjectMocks
    private EstabelecimentoController controller;

    private Estabelecimento estabelecimentoTeste;
    private Veiculos veiculoTeste;
    private Movimentacao movimentacaoTeste;

    @BeforeEach
    void preparaTestes() {
        estabelecimentoTeste = criarEstabelecimento();
        veiculoTeste = criarVeiculo();
        movimentacaoTeste = criarMovimentacao();
    }


    @Test
    @DisplayName("Deve criar estabelecimento")
    void testCriarEstabelecimento() {
        // 📝 GIVEN - Prepara o cenário
        when(estabelecimentoRepository.save(any(Estabelecimento.class)))
            .thenReturn(estabelecimentoTeste);

        // 🎬 WHEN - Executa o método que queremos testar
        Estabelecimento resultado = controller.criarEstabelecimento(estabelecimentoTeste);

        // 🔍 THEN - Verifica se deu certo
        assertNotNull(resultado);
        assertEquals("Estacionamento Teste", resultado.getNome());
        assertEquals("12.345.678/0001-90", resultado.getCnpj());

        // ✅ Verifica se o repository foi chamado 1 vez
        verify(estabelecimentoRepository, times(1)).save(estabelecimentoTeste);
    }

    @Test
    @DisplayName("Deve listar estabelecimentos")
    void testListarEstabelecimentos() {
        // 📝 GIVEN
        List<Estabelecimento> lista = Arrays.asList(estabelecimentoTeste);
        when(estabelecimentoRepository.findAll()).thenReturn(lista);

        // 🎬 WHEN
        List<Estabelecimento> resultado = controller.listarEstabelecimentos();

        // 🔍 THEN
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Estacionamento Teste", resultado.get(0).getNome());
        verify(estabelecimentoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve buscar estabelecimento por ID")
    void testBuscarEstabelecimentoPorId() {
        // 📝 GIVEN
        Long id = 1L;
        when(estabelecimentoRepository.findById(id))
            .thenReturn(Optional.of(estabelecimentoTeste));

        // 🎬 WHEN
        ResponseEntity<Estabelecimento> resposta = controller.buscarEstabelecimento(id);

        // 🔍 THEN
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
        assertEquals("Estacionamento Teste", resposta.getBody().getNome());
        verify(estabelecimentoRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve retornar 404 quando estabelecimento não existe")
    void testBuscarEstabelecimentoInexistente() {
        // 📝 GIVEN
        Long id = 999L;
        when(estabelecimentoRepository.findById(id)).thenReturn(Optional.empty());

        // 🎬 WHEN
        ResponseEntity<Estabelecimento> resposta = controller.buscarEstabelecimento(id);

        // 🔍 THEN
        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
        assertNull(resposta.getBody());
        verify(estabelecimentoRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve atualizar estabelecimento")
    void testAtualizarEstabelecimento() {
        // 📝 GIVEN
        Long id = 1L;
        when(estabelecimentoRepository.findById(id))
            .thenReturn(Optional.of(estabelecimentoTeste));
        when(estabelecimentoRepository.save(any(Estabelecimento.class)))
            .thenReturn(estabelecimentoTeste);

        // 🎬 WHEN
        ResponseEntity<Estabelecimento> resposta = controller.atualizarEstabelecimento(id, estabelecimentoTeste);

        // 🔍 THEN
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
        verify(estabelecimentoRepository, times(1)).findById(id);
        verify(estabelecimentoRepository, times(1)).save(any(Estabelecimento.class));
    }

    @Test
    @DisplayName("Deve excluir estabelecimento")
    void testExcluirEstabelecimento() {
        // 📝 GIVEN
        Long id = 1L;
        when(estabelecimentoRepository.findById(id))
            .thenReturn(Optional.of(estabelecimentoTeste));
        doNothing().when(estabelecimentoRepository).deleteById(id);

        // 🎬 WHEN
        ResponseEntity<Void> resposta = controller.excluirEstabelecimento(id);

        // 🔍 THEN
        assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());
        verify(estabelecimentoRepository, times(1)).findById(id);
        verify(estabelecimentoRepository, times(1)).deleteById(id);
    }

    // ========== 🚗 TESTES DE VEÍCULO ==========

    @Test
    @DisplayName("Deve criar veículo")
    void testCriarVeiculo() {
        // 📝 GIVEN
        when(veiculoRepository.save(any(Veiculos.class))).thenReturn(veiculoTeste);

        // 🎬 WHEN
        Veiculos resultado = controller.criarVeiculo(veiculoTeste);

        // 🔍 THEN
        assertNotNull(resultado);
        assertEquals("ABC-1234", resultado.getPlaca());
        assertEquals("Toyota", resultado.getMarca());
        verify(veiculoRepository, times(1)).save(veiculoTeste);
    }

    @Test
    @DisplayName("Deve listar veículos")
    void testListarVeiculos() {
        // 📝 GIVEN
        List<Veiculos> lista = Arrays.asList(veiculoTeste);
        when(veiculoRepository.findAll()).thenReturn(lista);

        // 🎬 WHEN
        List<Veiculos> resultado = controller.listarVeiculos();

        // 🔍 THEN
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("ABC-1234", resultado.get(0).getPlaca());
        verify(veiculoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve buscar veículo por ID")
    void testBuscarVeiculoPorId() {
        // 📝 GIVEN
        Long id = 1L;
        when(veiculoRepository.findById(id)).thenReturn(Optional.of(veiculoTeste));

        // 🎬 WHEN
        ResponseEntity<Veiculos> resposta = controller.buscarVeiculo(id);

        // 🔍 THEN
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
        assertEquals("ABC-1234", resposta.getBody().getPlaca());
        verify(veiculoRepository, times(1)).findById(id);
    }


    @Test
    @DisplayName("Deve registrar entrada de veículo")
    void testEntradaVeiculo() {
        // 📝 GIVEN
        Long veiculoId = 1L;
        Long estabelecimentoId = 1L;

        when(veiculoRepository.findById(veiculoId))
            .thenReturn(Optional.of(veiculoTeste));
        when(estabelecimentoRepository.findById(estabelecimentoId))
            .thenReturn(Optional.of(estabelecimentoTeste));
        when(movimentacaoRepository.save(any(Movimentacao.class)))
            .thenReturn(movimentacaoTeste);

        // 🎬 WHEN
        ResponseEntity<String> resposta = controller.entrada(veiculoId, estabelecimentoId);

        // 🔍 THEN
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertTrue(resposta.getBody().contains("entrou no estacionamento"));
        assertTrue(resposta.getBody().contains("ABC-1234"));

        verify(veiculoRepository, times(1)).findById(veiculoId);
        verify(estabelecimentoRepository, times(1)).findById(estabelecimentoId);
        verify(movimentacaoRepository, times(1)).save(any(Movimentacao.class));
    }

    @Test
    @DisplayName("Deve dar erro quando veículo não existe na entrada")
    void testEntradaVeiculoInexistente() {
        // 📝 GIVEN
        Long veiculoId = 999L;
        Long estabelecimentoId = 1L;

        when(veiculoRepository.findById(veiculoId)).thenReturn(Optional.empty());

        // 🎬 WHEN
        ResponseEntity<String> resposta = controller.entrada(veiculoId, estabelecimentoId);

        // 🔍 THEN
        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        assertEquals("Veículo não encontrado!", resposta.getBody());
        verify(veiculoRepository, times(1)).findById(veiculoId);
        verify(movimentacaoRepository, never()).save(any(Movimentacao.class));
    }

    @Test
    @DisplayName("Deve registrar saída de veículo")
    void testSaidaVeiculo() {
        // 📝 GIVEN
        Long veiculoId = 1L;
        List<Movimentacao> movimentacoes = Arrays.asList(movimentacaoTeste);

        when(movimentacaoRepository.findVeiculosAtualmenteEstacionados())
            .thenReturn(movimentacoes);
        when(movimentacaoRepository.save(any(Movimentacao.class)))
            .thenReturn(movimentacaoTeste);

        // 🎬 WHEN
        ResponseEntity<String> resposta = controller.saida(veiculoId);

        // 🔍 THEN
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertTrue(resposta.getBody().contains("saiu do estacionamento"));
        assertTrue(resposta.getBody().contains("ABC-1234"));

        verify(movimentacaoRepository, times(1)).findVeiculosAtualmenteEstacionados();
        verify(movimentacaoRepository, times(1)).save(any(Movimentacao.class));
    }

    @Test
    @DisplayName("Deve listar veículos no estacionamento")
    void testListarVeiculosNoEstacionamento() {
        // 📝 GIVEN
        List<Movimentacao> movimentacoes = Arrays.asList(movimentacaoTeste);
        when(movimentacaoRepository.findVeiculosAtualmenteEstacionados())
            .thenReturn(movimentacoes);

        // 🎬 WHEN
        List<Movimentacao> resultado = controller.veiculosNoEstacionamento();

        // 🔍 THEN
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("ABC-1234", resultado.get(0).getVeiculo().getPlaca());
        verify(movimentacaoRepository, times(1)).findVeiculosAtualmenteEstacionados();
    }

    // ========== 🛠️ MÉTODOS AUXILIARES (Para criar dados de teste) ==========


    private Estabelecimento criarEstabelecimento() {
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setId(1L);
        estabelecimento.setNome("Estacionamento Teste");
        estabelecimento.setCnpj("12.345.678/0001-90");
        estabelecimento.setEndereco("Rua Teste, 123");
        estabelecimento.setTelefone("(11) 99999-9999");
        estabelecimento.setVagasMotos(50);
        estabelecimento.setVagasCarros(100);
        return estabelecimento;
    }

    private Veiculos criarVeiculo() {
        Veiculos veiculo = new Veiculos();
        veiculo.setId(1L);
        veiculo.setMarca("Toyota");
        veiculo.setModelo("Corolla");
        veiculo.setCor("Prata");
        veiculo.setPlaca("ABC-1234");
        veiculo.setTipo(TipoVeiculo.CARRO);
        return veiculo;
    }

    private Movimentacao criarMovimentacao() {
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setId(1L);
        movimentacao.setVeiculo(veiculoTeste);
        movimentacao.setEstabelecimento(estabelecimentoTeste);
        movimentacao.setDataHoraEntrada(LocalDateTime.now());
        return movimentacao;
    }
}

