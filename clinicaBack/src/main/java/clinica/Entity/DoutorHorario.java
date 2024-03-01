package clinica.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter@Setter
public class DoutorHorario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ativo")
    private Boolean ativo = true;

    @Column(name = "dia_semana")
    private int diaSemana;

    @Column(name = "horario_inicial_manha")
    private Timestamp horarioInicialManha;

    @Column(name = "horario_final_manha")
    private Timestamp horarioFinalManha;

    @Column(name = "horario_inicial_tarde")
    private Timestamp horarioInicialTarde;

    @Column(name = "horario_final_tarde")
    private Timestamp horarioFinalTarde;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doutor_horario")
    @JsonIgnoreProperties("horario")
    private Doutor doutorHorario;
}
