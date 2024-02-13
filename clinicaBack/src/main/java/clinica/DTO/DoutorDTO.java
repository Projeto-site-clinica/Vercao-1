package clinica.DTO;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter @Setter
public class DoutorDTO extends UsuarioDTO {
    private String rg;

    private String cpf;

    private String dataNacimento;

    private String formacao;

    private Double avaliacao;

    private String descricao;

    private Boolean solicitacao = false;

    private ClinicaDTO clinica;

    private List<ConsultaDTO> consulta;

    private Timestamp horarioStart;

    private Timestamp horarioEnd;

    private Timestamp intervaloStart;

    private Timestamp intervaloEnd;
}