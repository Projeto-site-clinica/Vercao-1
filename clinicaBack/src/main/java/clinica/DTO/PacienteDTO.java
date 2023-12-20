package clinica.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PacienteDTO extends AbstractDTO{
    private String rg;

    private String cpf;

    private String dataNacimento;
}
