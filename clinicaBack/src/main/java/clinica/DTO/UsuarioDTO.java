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
    private String nome;

    private String username;

    private String celular;

    private String email;

    private String password;

    private String token;

    private String role;

    //endereco
    private String cep;

    private String estado;

    private String cidade;

    private String rua;

    private String bairro;

    private Integer numero;

    private String complemento;

    private String referencia;
}