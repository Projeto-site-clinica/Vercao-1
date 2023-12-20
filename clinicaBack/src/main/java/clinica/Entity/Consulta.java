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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ativo")
    private Boolean ativo = true;

    @Column(name = "nome")
    private String nomeConsulta;

    @Column(name = "valor")
    private double valor;

    @Column(name = "discricao")
    private String descricao;

    @Column(name = "tempo")
    private LocalDateTime tempo;
}