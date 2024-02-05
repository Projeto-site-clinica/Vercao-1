package clinica.DTO;

import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Getter @Setter
public class ConsultaDTO {
    private Long id;

    private Boolean ativo = true;

    private String nomeConsulta;

    private double valor;

    private String descricao;

    private Timestamp tempo;

    private DoutorDTO doutor;
}