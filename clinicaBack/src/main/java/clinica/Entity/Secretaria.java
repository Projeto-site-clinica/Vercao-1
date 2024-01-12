package clinica.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@PrimaryKeyJoinColumn(name = "id_secretaria")
@Getter @Setter
public class Secretaria extends Usuario {

    @Column(name = "rg",unique = true,nullable = false)
    private String rg;

    @Column(name = "cpf",unique = true,nullable = false)
    private String cpf;

    @Column(name = "data_nacimento",nullable = false)
    private String dataNacimento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "clinica_id")
    private Clinica clinicaId;
}