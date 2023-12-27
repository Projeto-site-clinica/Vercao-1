package clinica.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SecretariaDTO extends AbstractDTO {
    private String rg;

    private String cpf;

    private String dataNacimento;

    private ClinicaDTO clinicaId;
}