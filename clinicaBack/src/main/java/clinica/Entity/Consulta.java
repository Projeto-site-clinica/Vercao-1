package clinica.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter @Setter
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ativo")
    private Boolean ativo = true;

    @Column(name = "nome",nullable = false)
    private String nomeConsulta;

    @Column(name = "valor",nullable = false)
    private Double valor;

    @Column(name = "descricao",nullable = false)
    private String descricao;

    @Column(name = "tempo",nullable = false)
    private Timestamp duracao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doutor_consulta")
    @JsonIgnoreProperties("consulta")
    private Doutor doutorConsulta;

    //intervalo
}