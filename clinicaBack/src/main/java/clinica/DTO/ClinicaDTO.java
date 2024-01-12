package clinica.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ClinicaDTO extends UsuarioDTO {
    private String cnpj;

    private Double avaliacao;

    private String descricao;

    private Boolean solicitacao = false;

    private List<DoutorDTO> doutores;

    private List<SecretariaDTO> secretarias;
}
