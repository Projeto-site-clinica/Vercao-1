package clinica.Repository;

import clinica.Entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    @Query("FROM Paciente WHERE ativo = true")
    List<Paciente> findPacienteByAtivo();
}