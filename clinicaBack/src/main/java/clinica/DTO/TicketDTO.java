package clinica.DTO;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
public class TicketDTO {
    private Long id;

    private Boolean ativo = true;

    private PacienteDTO pacienteId;

    private DoutorDTO doutorId;

    private Timestamp logMarcar;

    private CalendarioDTO data;

    private ConsultaDTO consulta;
}