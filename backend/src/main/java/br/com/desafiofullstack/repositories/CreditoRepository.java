package br.com.desafiofullstack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.desafiofullstack.domain.Credito;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditoRepository extends JpaRepository<Credito, Long> {

    /**
     * Encontra uma lista de créditos pelo número da NFS-e.
     * @param numeroNfse O número da NFS-e.
     * @return Uma lista de créditos que correspondem ao numeroNfse.
     */
    List<Credito> findByNumeroNfse(String numeroNfse);

    /**
     * Encontra um crédito específico pelo número do crédito.
     * @param numeroCredito O número do crédito.
     * @return Um Optional contendo o crédito, se encontrado.
     */
    Optional<Credito> findByNumeroCredito(String numeroCredito);
}
