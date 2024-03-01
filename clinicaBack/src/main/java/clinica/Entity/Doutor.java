package clinica.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id_doutor")
@Getter @Setter
public class Doutor extends Usuario {

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

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "solicitacao")
    private Boolean solicitacao = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "clinica_id")
    private Clinica clinica;

    @OneToMany(mappedBy = "doutorConsulta", cascade = CascadeType.ALL)
    private List<Consulta> consultas;

    @OneToMany(mappedBy = "doutorHorario", cascade = CascadeType.ALL)
    private List<DoutorHorario> horarios;

//    @Column(name = "perfis")
//    private List<Perfis> perfis;
}