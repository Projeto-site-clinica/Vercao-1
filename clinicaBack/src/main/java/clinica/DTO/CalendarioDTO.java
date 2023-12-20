package clinica.DTO;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter @Setter
public class CalendarioDTO {
    private Long id;

    private Boolean ativo = true;

    private LocalDateTime data;
}