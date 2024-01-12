package clinica.Repository;

import clinica.Entity.Secretaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecretariaRepository extends JpaRepository<Secretaria, Long> {
    @Query("FROM Secretaria WHERE ativo = true")
    List<Secretaria> findSecretariaByAtivo();

    @Query(value = "SELECT password FROM secretaria WHERE id = :id",nativeQuery = true)
    String findSenhaById(Long id);
}