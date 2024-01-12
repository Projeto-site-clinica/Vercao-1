package clinica.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PacienteDTO extends UsuarioDTO {

    private String rg;

    private String cpf;

    private String dataNacimento;
}