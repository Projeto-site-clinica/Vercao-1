package clinica.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name="doutor",schema = "public")
@Getter @Setter
public class Calendario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ativo")
    private Boolean ativo = true;

    @Column(name = "data",nullable = false)
    private LocalDateTime data;
}