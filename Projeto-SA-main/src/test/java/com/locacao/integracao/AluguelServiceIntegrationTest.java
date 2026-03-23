package com.locacao.integracao;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.locacao.dto.ImovelRequestDTO;
import com.locacao.dto.ImovelResponseDTO;
import com.locacao.service.ImovelService;

@SpringBootTest
@Transactional // Limpa o banco de dados após cada teste
public class AluguelServiceIntegrationTest {

    @Autowired
    private ImovelService imovelService;

    @Test
    @DisplayName("Deve salvar um imóvel e buscar por ID com sucesso")
    public void deveSalvarEBuscarImovel() {
        // Preparação (Dica: Use Integer 3, não 3L)
        ImovelRequestDTO dto = new ImovelRequestDTO(
            "Rua Teste, 123", "Casa", (short) 3, (short) 2, (short) 1,
            true, true, "Casa de luxo", new BigDecimal("2500.00"), 
            "foto.jpg", "Aluguel"
        );

        // Execução
        ImovelResponseDTO salvo = imovelService.salvar(dto);
        ImovelResponseDTO encontrado = imovelService.buscarPorId(salvo.id());

        // Verificação
        assertNotNull(encontrado);
        assertEquals("Rua Teste, 123", encontrado.endereco());
        assertEquals(salvo.id(), encontrado.id());
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar ID que não existe")
    public void deveLancarExcecaoAoBuscarIdInexistente() {
        // Importante: Use Integer para bater com o seu Service
        Integer idInexistente = 9999;

        // Verifica se o service lança RuntimeException (ou a específica do seu projeto)
        assertThrows(RuntimeException.class, () -> {
            imovelService.buscarPorId(idInexistente);
        });
    }

    @Test
    @DisplayName("Deve deletar um imóvel com sucesso")
    public void deveDeletarImovel() {
        ImovelRequestDTO dto = new ImovelRequestDTO(
            "Rua para Deletar", "Apto", (short) 1, (short) 1, (short) 0,
            false, true, "Simples", new BigDecimal("800.00"), 
            "foto2.jpg", "Venda"
        );

        ImovelResponseDTO salvo = imovelService.salvar(dto);
        Integer id = salvo.id();

        // Executa a deleção
        imovelService.deletar(id);

        // Verifica se o ID sumiu (deve dar erro ao buscar agora)
        assertThrows(RuntimeException.class, () -> {
            imovelService.buscarPorId(id);
        });
    }
}

