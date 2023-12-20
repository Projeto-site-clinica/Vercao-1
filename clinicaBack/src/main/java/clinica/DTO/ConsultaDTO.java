package clinica.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ConsultaDTO {
    private Long id;

    private Boolean ativo = true;

    private String nomeConsulta;

    private double valor;

    private String descricao;

    private LocalDateTime tempo;
}