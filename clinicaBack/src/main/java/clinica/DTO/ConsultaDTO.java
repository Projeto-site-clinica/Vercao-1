package clinica.DTO;

import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Getter @Setter
public class ConsultaDTO {
    private Long id;

    private Boolean ativo = true;

    private String nomeConsulta;

    private Double valor;

    private String descricao;

    private Timestamp duracao;

    private DoutorDTO doutorConsulta;
}