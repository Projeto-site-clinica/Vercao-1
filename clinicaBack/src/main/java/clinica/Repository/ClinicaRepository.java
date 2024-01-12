package clinica.Repository;

import clinica.Entity.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClinicaRepository extends JpaRepository<Clinica, Long> {
    @Query("FROM Clinica WHERE ativo = true")
    List<Clinica> findClinicaByAtivo();

    @Query(value = "SELECT password FROM clinica WHERE id = :id",nativeQuery = true)
    String findSenhaById(Long id);
}