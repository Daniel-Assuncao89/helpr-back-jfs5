package org.soulcodeacademy.helpr.repositories;
import org.soulcodeacademy.helpr.domain.Cargo;
import org.soulcodeacademy.helpr.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    List<Funcionario> findByCargo(Cargo cargo);

    List<Funcionario> findByFotoIsNull();

    Funcionario findByEmail(String email);

    List<Funcionario> findByFotoIsNotNull();

    @Query(value = "SELECT * FROM usuarios INNER JOIN cargo ON usuarios.id_cargo = cargo.id_cargo WHERE dtype = 'Funcionario' AND cargo.salario BETWEEN :valor1 AND :valor2", nativeQuery = true)
    List<Funcionario> findBySalarioEntreFaixas(Double valor1, Double valor2);

    @Query(value = "SELECT count(id_Cargo) FROM usuarios WHERE dtype = 'Funcionario' AND id_cargo = :idCargo", nativeQuery = true)
    Integer CountCargo(Integer idCargo);

    @Query(value = "SELECT * FROM usuarios WHERE dtype = 'Funcionario' AND foto IS NULL", nativeQuery = true)
    List<Funcionario> findFotoNull();
}
