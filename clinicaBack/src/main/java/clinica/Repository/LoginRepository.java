package clinica.Repository;

import java.util.Optional;
import clinica.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoginRepository extends JpaRepository<Usuario, Long>{
    @Query("FROM Usuario WHERE ativo = true AND username = :login")
    public Optional<Usuario> findByUsername(String login);
}