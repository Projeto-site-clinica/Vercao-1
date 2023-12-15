package clinica.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalTime;

@Entity
@Table(name="consultas",schema = "public")
@Getter @Setter
public class ConsultasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ativo")
    private Boolean ativo = true;

    @Column(name = "nome")
    private String nomeConsulta;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "discricao")
    private String discricao;

    @Column(name = "tempo")
    private LocalTime tempo;
}