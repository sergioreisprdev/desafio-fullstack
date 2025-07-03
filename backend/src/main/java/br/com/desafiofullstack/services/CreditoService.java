package br.com.desafiofullstack.services;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiofullstack.domain.Credito;
import br.com.desafiofullstack.dto.CreditoResponseDto;
import br.com.desafiofullstack.repositories.CreditoRepository;

@Service
public class CreditoService {

	@Autowired
	private CreditoRepository creditoRepository;

    /**
     * Busca créditos pelo número da NFS-e e os mapeia para DTOs.
     * @param numeroNfse Número da NFS-e para buscar.
     * @return Lista de DTOs de créditos encontrados.
     */
    public List<CreditoResponseDto> getCreditosByNumeroNfse(String numeroNfse) {
        return creditoRepository.findByNumeroNfse(numeroNfse).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Busca um crédito específico pelo número do crédito e o mapeia para DTO.
     * @param numeroCredito Número do crédito para buscar.
     * @return Um Optional contendo o DTO do crédito, se encontrado.
     */
    public Optional<CreditoResponseDto> getCreditoByNumeroCredito(String numeroCredito) {
        return creditoRepository.findByNumeroCredito(numeroCredito)
                .map(this::convertToDto);
    }

    /**
     * Converte uma entidade Credito para um CreditoResponseDto.
     * @param credito A entidade Credito a ser convertida.
     * @return O DTO correspondente.
     */
    private CreditoResponseDto convertToDto(Credito credito) {
        return new CreditoResponseDto(
                credito.getNumeroCredito(),
                credito.getNumeroNfse(),
                credito.getDataConstituicao(),
                credito.getValorIssqn(),
                credito.getTipoCredito(),
                credito.isSimplesNacional(),
                credito.getAliquota(),
                credito.getValorFaturado(),
                credito.getValorDeducao(),
                credito.getBaseCalculo()
        );
    }

}
