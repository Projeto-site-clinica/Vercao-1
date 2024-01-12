package clinica.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@PrimaryKeyJoinColumn(name = "id_paciente")
@Getter @Setter
public class Paciente extends Usuario {

    @Column(name = "rg",unique = true,nullable = false)
    private String rg;

    @Column(name = "cpf",unique = true,nullable = false)
    private String cpf;

    @Column(name = "data_nacimento",nullable = false)
    private String dataNacimento;
}