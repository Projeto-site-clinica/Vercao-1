package clinica.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="clinica",schema = "public")
@Getter @Setter
public class Clinica extends Abstract {

    @Column(name = "cnpj",unique = true,nullable = false)
    private String cnpj;

    @Column(name = "avaliacao")
    private Double avaliacao;

    @Column(name = "discricao",nullable = false)
    private String discricao;

    @Column(name = "solicitacao")
    private Boolean solicitacao = false;

    @JoinColumn( name = "doutores")
    private List<Doutor> doutores;

    @JoinColumn( name = "secretaria")
    private List<Secretaria> secretaria;
}