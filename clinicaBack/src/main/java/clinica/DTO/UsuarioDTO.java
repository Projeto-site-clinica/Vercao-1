package clinica.DTO;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter @Setter
public class UsuarioDTO {
    private Long id;

    private Boolean ativo = true;

    //dados pessoal
    private String username;

    private String celular;

    private String email;

    private String password;

    private String token;

    private String role;

    //endereco
    private int cep;

    private String estado;

    private String cidade;

    private String rua;

    private String bairro;

    private int numero;

    private String complemento;

    private String referencia;
}