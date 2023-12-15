package clinica.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name="doutor",schema = "public")
@Getter @Setter
public class DoutorEntity extends AbtractEntity{

    @Column(name = "rg",unique = true,nullable = false)
    private String rg;

    @Column(name = "cpf",unique = true,nullable = false)
    private String cpf;

    @Column(name = "data_nacimento",nullable = false)
    private String dataNacimento;

    @Column(name = "formacao", nullable = false)
    private String formacao;

    @Column(name = "avaliacao")
    private Double avaliacao;

    @Column(name = "discricao",nullable = false)
    private String discricao;

    @Column(name = "solicitacao")
    private Boolean solicitacao = false;

    @ManyToMany(mappedBy = "doutores")
    @Column(name = "clinica_id")
    private ClinicaEntity clinicaId;

    @Column(name = "consultas")
    private List<ConsultasEntity> consultas;

    @Column(name = "horario",nullable = false)
    private List<CalendarioEntity> calendario;

    @Column(name = "horario",nullable = false)
    private LocalTime horarioStart;

    @Column(name = "horario",nullable = false)
    private LocalTime horarioEnd;

//    @Column(name = "perfis")
//    private List<Perfis> perfis;
}