package clinica.Repository;

import clinica.Entity.Doutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoutorRepository extends JpaRepository<Doutor, Long> {
    @Query("FROM Doutor WHERE ativo = true")
    List<Doutor> findDoutorByAtivo();

    @Query(value = "SELECT password FROM doutor WHERE id = :id",nativeQuery = true)
    String findSenhaById(Long id);
}