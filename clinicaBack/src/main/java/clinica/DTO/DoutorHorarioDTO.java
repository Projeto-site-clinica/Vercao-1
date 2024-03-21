package clinica.DTO;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
public class DoutorHorarioDTO {
    private Long id;

    private Boolean ativo = true;

    private int diaSemana;

    private Timestamp horarioInicialManha;

    private Timestamp horarioFinalManha;

    private Timestamp horarioInicialTarde;

    private Timestamp horarioFinalTarde;

    private DoutorDTO doutorId;
}
