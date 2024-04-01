package clinica.Service;

import clinica.DTO.*;
import clinica.Entity.Doutor;
import clinica.Entity.DoutorHorario;
import clinica.Repository.DoutorHorarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoutorHorarioService {
    @Autowired
    private DoutorHorarioRepository doutorHorarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public DoutorHorarioDTO findDoutorHorarioById(Long id) {
        DoutorHorario doutorHorario = doutorHorarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DoutorHorario não encontrado!"));
        return doutorHorarioToDTO(doutorHorario);
    }

    public List<DoutorHorarioDTO> listar() {
        return doutorHorarioRepository.findDoutorHorarioByAtivo().stream().map(this::doutorHorarioToDTO).toList();
    }

    public MensagemDTO cadastrarDoutorHorario(DoutorHorarioDTO doutorHorarioDTO) {
        DoutorHorario doutorHorario = toDoutorHorario(doutorHorarioDTO);
        doutorHorarioRepository.save(doutorHorario);
        return new MensagemDTO("Horário cadastrado com sucesso!", HttpStatus.CREATED);
    }
    public MensagemDTO editarDoutorHorario(Long id, DoutorHorarioDTO doutorHorarioDTO) {
//        String horaMinutos = String.valueOf(doutorHorarioDTO.getHorarioInicialManha());
//        int hora = Integer.parseInt(horaMinutos.split(":")[0]);
//        int minutos = Integer.parseInt(horaMinutos.split(":")[1]);
//
//        // Criar um objeto LocalTime
//        LocalTime localTime = LocalTime.of(hora, minutos);
//
//        // Definir uma data fixa para a criação do Timestamp (por exemplo, hoje)
//        LocalDate hoje = LocalDate.now();
//
//        // Combinação de data e hora
//        LocalDateTime localDateTime = LocalDateTime.of(hoje, localTime);
//
//        // Converter para Timestamp
//        Timestamp timestamp = Timestamp.valueOf(localDateTime);


//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//        LocalTime localTime = LocalTime.parse((CharSequence) doutorHorarioDTO.getHorarioInicialManha(), formatter);
//
//        Timestamp timestamp = Timestamp.valueOf(localTime.atDate(java.time.LocalDate.now()));

        DoutorHorario doutorHorario = toDoutorHorario(doutorHorarioDTO);

        doutorHorarioRepository.save(doutorHorario);
        return new MensagemDTO("Horário atualizado com sucesso!", HttpStatus.CREATED);
    }
    public MensagemDTO deletarDoutorHorario(Long id) {
        DoutorHorario doutorBanco = doutorHorarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doutor com ID " + id + " não existe!"));
        desativarDoutorHorario(doutorBanco);
        return new MensagemDTO("Horário deletado com sucesso!", HttpStatus.CREATED);
    }

    private void desativarDoutorHorario(DoutorHorario doutorHorario) {
        doutorHorario.setAtivo(false);
        doutorHorarioRepository.save(doutorHorario);
    }

    public DoutorHorarioDTO doutorHorarioToDTO(DoutorHorario doutorHorario){
        DoutorHorarioDTO doutorHorarioDTO = new DoutorHorarioDTO();

        doutorHorarioDTO.setId(doutorHorario.getId());
        doutorHorarioDTO.setDiaSemana(doutorHorario.getDiaSemana());
        doutorHorarioDTO.setHorarioInicialManha(doutorHorario.getHorarioInicialManha());
        doutorHorarioDTO.setHorarioFinalManha(doutorHorario.getHorarioFinalManha());
        doutorHorarioDTO.setHorarioInicialTarde(doutorHorario.getHorarioFinalTarde());
        doutorHorarioDTO.setHorarioFinalTarde(doutorHorario.getHorarioFinalTarde());


        if (doutorHorario.getDoutorId() != null){
            DoutorDTO doutorDTO = new DoutorDTO();
            doutorDTO.setId(doutorHorario.getDoutorId().getId());
            doutorHorarioDTO.setDoutorId(doutorDTO);
        }
        return doutorHorarioDTO;
    }

    public DoutorHorario toDoutorHorario(DoutorHorarioDTO doutorHorarioDTO){
        DoutorHorario novoDoutorHorario = new DoutorHorario();

        novoDoutorHorario.setId(doutorHorarioDTO.getId());
        novoDoutorHorario.setDiaSemana(doutorHorarioDTO.getDiaSemana());
        novoDoutorHorario.setHorarioInicialManha(doutorHorarioDTO.getHorarioInicialManha());
        novoDoutorHorario.setHorarioFinalManha(doutorHorarioDTO.getHorarioFinalManha());
        novoDoutorHorario.setHorarioInicialTarde(doutorHorarioDTO.getHorarioInicialTarde());
        novoDoutorHorario.setHorarioFinalTarde(doutorHorarioDTO.getHorarioFinalTarde());

        if (doutorHorarioDTO.getDoutorId() != null) {
            Doutor doutor = new Doutor();
            doutor.setId(doutorHorarioDTO.getDoutorId().getId());
            novoDoutorHorario.setDoutorId(doutor);
        }
        return novoDoutorHorario;
    }
}
