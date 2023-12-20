//package clinica.Repository;
//
//import java.util.List;
//import java.util.Optional;
//import clinica.Entity.Paciente;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//
//public interface LoginRepository extends JpaRepository<Paciente, Long>{
//    @Query("FROM Paciente WHERE ativo = true AND username = :login")
//    public Optional<Paciente> findByUsername(String login);
//
//    @Query("FROM User WHERE ativo = true")
//    List<Paciente> findUserByAtivo();
//
//    @Query(value = "SELECT password FROM usuario WHERE id = :id",nativeQuery = true)
//    String findSenhaById(Long id);
//
//}