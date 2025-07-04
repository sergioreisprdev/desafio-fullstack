package br.com.desafiofullstack.resources;

import br.com.desafiofullstack.dto.CreditoResponseDto;
import br.com.desafiofullstack.services.CreditoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/creditos")
public class CreditoResource {

	@Autowired
	private CreditoService creditoService;

	/**
	 * GET /api/creditos/{numeroNfse} Retorna uma lista de créditos constituídos com
	 * base no número da NFS-e.
	 * 
	 * @param numeroNfse Número identificador da NFS-e.
	 * @return ResponseEntity com a lista de DTOs de créditos e status OK, ou
	 *         NOT_FOUND se nenhum crédito for encontrado.
	 */
	@GetMapping("/{numeroNfse}")
	public ResponseEntity<List<CreditoResponseDto>> getCreditosByNumeroNfse(@PathVariable String numeroNfse) {
		List<CreditoResponseDto> creditos = creditoService.getCreditosByNumeroNfse(numeroNfse);
		if (!creditos.isEmpty()) {
			return new ResponseEntity<>(creditos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * GET /api/creditos/credito/{numeroCredito} Retorna os detalhes de um crédito
	 * constituído específico com base no número do crédito constituído.
	 * 
	 * @param numeroCredito Número identificador do crédito constituído.
	 * @return ResponseEntity com o DTO do crédito e status OK, ou NOT_FOUND se não
	 *         existir.
	 */
	@GetMapping("/credito/{numeroCredito}")
	public ResponseEntity<CreditoResponseDto> getCreditoByNumeroCredito(@PathVariable String numeroCredito) {
		Optional<CreditoResponseDto> creditoDto = creditoService.getCreditoByNumeroCredito(numeroCredito);
		return creditoDto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	/**
	 * GET /api/creditos
	 * Retorna todos os créditos constituídos.
	 *
	 * @return Lista de todos os créditos em formato DTO.
	 */
	@GetMapping
	public ResponseEntity<List<CreditoResponseDto>> getAllCreditos() {
	    List<CreditoResponseDto> creditos = creditoService.getAllCreditos();
	    return new ResponseEntity<>(creditos, HttpStatus.OK);
	}

}
