package clinica.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name="doutor",schema = "public")
@Getter @Setter
public class Doutor extends Abstract {

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

    @Column(name = "descricao",nullable = false)
    private String descricao;

    @Column(name = "solicitacao")
    private Boolean solicitacao = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "clinica_id")
    private Clinica clinicaId;

    @OneToMany
    @JoinColumn(name = "consulta")
    private List<Consulta> consulta;

    @Column(name = "horario_start",nullable = false)
    private LocalTime horarioStart;

    @Column(name = "horario_end",nullable = false)
    private LocalTime horarioEnd;

//    @Column(name = "perfis")
//    private List<Perfis> perfis;
}