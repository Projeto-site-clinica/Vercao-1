package clinica.Repository;

import clinica.Entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    @Query("FROM Consulta WHERE ativo = true")
    List<Consulta> findConsultaByAtivo();
}