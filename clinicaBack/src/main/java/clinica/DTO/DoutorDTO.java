package clinica.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter @Setter
public class DoutorDTO extends AbstractDTO{
    private String rg;

    private String cpf;

    private String dataNacimento;

    private String formacao;

    private Double avaliacao;

    private String discricao;

    private Boolean solicitacao = false;

    private ClinicaDTO clinicaId;

    private List<ConsultaDTO> consulta;

    private LocalTime horarioStart;

    private LocalTime horarioEnd;
}