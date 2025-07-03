package br.com.desafiofullstack.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import br.com.desafiofullstack.domain.Credito;
import br.com.desafiofullstack.dto.CreditoResponseDto;
import br.com.desafiofullstack.repositories.CreditoRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreditoServiceTest {
	@Mock
    private CreditoRepository creditoRepository;

    @InjectMocks
    private CreditoService creditoService;

    private Credito creditoExemplo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        creditoExemplo = new Credito(
                1L,
                "CRED-001",
                "NFSE-001",
                LocalDate.of(2024, 1, 1),
                new BigDecimal("1200.00"),
                "ISSQN",
                true,
                new BigDecimal("5.00"),
                new BigDecimal("24000.00"),
                new BigDecimal("3000.00"),
                new BigDecimal("21000.00")
        );
    }

    @Test
    void testGetCreditosByNumeroNfse_retornaListaComUmItem() {
        when(creditoRepository.findByNumeroNfse("NFSE-001"))
                .thenReturn(List.of(creditoExemplo));

        List<CreditoResponseDto> resultado = creditoService.getCreditosByNumeroNfse("NFSE-001");

        assertEquals(1, resultado.size());
        assertEquals("CRED-001", resultado.get(0).getNumeroCredito());
        verify(creditoRepository, times(1)).findByNumeroNfse("NFSE-001");
    }

    @Test
    void testGetCreditosByNumeroNfse_retornaListaVazia() {
        when(creditoRepository.findByNumeroNfse("NFSE-XYZ")).thenReturn(Collections.emptyList());

        List<CreditoResponseDto> resultado = creditoService.getCreditosByNumeroNfse("NFSE-XYZ");

        assertTrue(resultado.isEmpty());
        verify(creditoRepository).findByNumeroNfse("NFSE-XYZ");
    }

    @Test
    void testGetCreditoByNumeroCredito_retornaOptionalPresente() {
        when(creditoRepository.findByNumeroCredito("CRED-001"))
                .thenReturn(Optional.of(creditoExemplo));

        Optional<CreditoResponseDto> resultado = creditoService.getCreditoByNumeroCredito("CRED-001");

        assertTrue(resultado.isPresent());
        assertEquals("NFSE-001", resultado.get().getNumeroNfse());
        verify(creditoRepository).findByNumeroCredito("CRED-001");
    }

    @Test
    void testGetCreditoByNumeroCredito_retornaOptionalVazio() {
        when(creditoRepository.findByNumeroCredito("CRED-999")).thenReturn(Optional.empty());

        Optional<CreditoResponseDto> resultado = creditoService.getCreditoByNumeroCredito("CRED-999");

        assertFalse(resultado.isPresent());
        verify(creditoRepository).findByNumeroCredito("CRED-999");
    }
}
