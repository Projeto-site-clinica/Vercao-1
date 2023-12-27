package clinica.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="clinica",schema = "public")
@Getter @Setter
public class Clinica extends Abstract {

    @Column(name = "cnpj",unique = true,nullable = false)
    private String cnpj;

    @Column(name = "avaliacao")
    private Double avaliacao;

    @Column(name = "descricao",nullable = false)
    private String descricao;

    @Column(name = "solicitacao")
    private Boolean solicitacao = false;

    @OneToMany(mappedBy = "clinicaId",cascade = CascadeType.ALL)
    @Column(name = "doutor_id")
    @JsonIgnoreProperties("clinicaId")
    private List<Doutor> doutores;

    @OneToMany(mappedBy = "clinicaId",cascade = CascadeType.ALL)
    @Column(name = "secretarias_id")
    @JsonIgnoreProperties("clinicaId")
    private List<Secretaria> secretarias;
}