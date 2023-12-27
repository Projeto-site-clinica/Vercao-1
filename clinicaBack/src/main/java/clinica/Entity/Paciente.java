package clinica.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="paciente",schema = "public")
@Getter @Setter
public class Paciente extends Abstract {

    @Column(name = "rg",unique = true,nullable = false)
    private String rg;

    @Column(name = "cpf",unique = true,nullable = false)
    private String cpf;

    @Column(name = "data_nacimento",nullable = false)
    private String dataNacimento;
}