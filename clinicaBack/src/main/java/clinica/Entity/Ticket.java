package clinica.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ativo")
    private Boolean ativo = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id",nullable = false)
    private Paciente pacienteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doutor_id",nullable = false)
    private Doutor doutorId;

    @Column(name = "log_marcar",nullable = false)
    private LocalDateTime logMarcar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data",nullable = false)
    private Calendario data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consulta_ticket",nullable = false)
    private Consulta consulta;
}