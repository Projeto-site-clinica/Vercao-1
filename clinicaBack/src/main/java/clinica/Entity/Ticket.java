package clinica.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="ticket",schema = "public")
@Getter @Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ativo")
    private Boolean ativo = true;

    @Column(name = "paciente_id",nullable = false)
    private Paciente pacienteId;

    @Column(name = "doutor_id",nullable = false)
    private Doutor doutorId;

    @Column(name = "log_marcar",nullable = false)
    private LocalDateTime logMarcar;

    @Column(name = "data",nullable = false)
    private Calendario data;

    @Column(name = "consulta",nullable = false)
    private Consulta consulta;
}