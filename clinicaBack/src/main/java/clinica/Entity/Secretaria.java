package clinica.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="secretaria",schema = "public")
@Getter @Setter
public class Secretaria extends Abstract {

    @Column(name = "rg",unique = true,nullable = false)
    private String rg;

    @Column(name = "cpf",unique = true,nullable = false)
    private String cpf;

    @Column(name = "data_nacimento",nullable = false)
    private String dataNacimento;
}