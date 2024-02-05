package clinica.DTO;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
public class CalendarioDTO {
    private Long id;

    private Boolean ativo = true;

    private Timestamp data;
}