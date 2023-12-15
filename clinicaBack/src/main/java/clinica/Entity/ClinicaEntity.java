package clinica.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="clinica",schema = "public")
@Getter @Setter
public class ClinicaEntity extends AbtractEntity{

    @Column(name = "cnpj",unique = true,nullable = false)
    private String cnpj;

    @Column(name = "avaliacao")
    private Double avaliacao;

    @Column(name = "discricao",nullable = false)
    private String discricao;

    @Column(name = "solicitacao")
    private Boolean solicitacao = false;

    @ManyToMany
    @JoinTable(
            name = "clinica_doutores",
            joinColumns = @JoinColumn(name = "clinica_id"),
            inverseJoinColumns = @JoinColumn(name = "doutor_id")
    )
    private List<DoutorEntity> doutores;

    @ManyToMany
    @JoinTable(
            name = "clinica_secretaria",
            joinColumns = @JoinColumn(name = "clinica_id"),
            inverseJoinColumns = @JoinColumn(name = "secretaria_id")
    )
    private List<SecretariaEntity> secretarias;
}