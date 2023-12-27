package clinica.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="consulta",schema = "public")
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
    private LocalDateTime tempo;
}