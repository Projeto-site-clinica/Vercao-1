package clinica.Repository;

import clinica.Entity.DoutorHorario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoutorHorarioRepository extends JpaRepository<DoutorHorario, Long> {
    @Query("FROM Doutor WHERE ativo = true")
    List<DoutorHorario> findDoutorHorarioByAtivo();
}
