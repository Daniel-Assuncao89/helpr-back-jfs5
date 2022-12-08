package org.soulcodeacademy.helpr.repositories;

import org.soulcodeacademy.helpr.domain.FuturoCandidato;
import org.soulcodeacademy.helpr.domain.enums.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuturoCandidatoRepository extends JpaRepository<FuturoCandidato, Integer> {

    Optional<FuturoCandidato> findByEmail(String email);

    @Query(value = "SELECT * FROM futuro_candidato WHERE nome_completo LIKE :nome% ", nativeQuery = true)
    List<FuturoCandidato> findByNomeCompleto(String nome);

    List<FuturoCandidato> findBySetor(Setor setor);
}
