package org.soulcodeacademy.helpr.repositories;
import org.soulcodeacademy.helpr.domain.Dependente;
import org.soulcodeacademy.helpr.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface DependenteRepository extends JpaRepository<Dependente, Integer> {

    @Query(value = "SELECT * FROM dependente WHERE data_de_nascimento BETWEEN :valor1 AND :valor2", nativeQuery = true)
    List<Dependente> findByDataEntreFaixas(LocalDate valor1, LocalDate valor2);

    Dependente findByCpf(String cpf);

    List<Dependente> findByEscolaridade(String escolaridade);

    List<Dependente> findByFuncionario(Funcionario funcionario);


}
