package clinica.Service;

import clinica.DTO.*;
import clinica.Entity.Doutor;
import clinica.Entity.DoutorHorario;
import clinica.Repository.DoutorHorarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

    public DoutorHorarioDTO doutorHorarioToDTO(DoutorHorario doutorHorario){
        DoutorHorarioDTO doutorHorarioDTO = new DoutorHorarioDTO();

        doutorHorarioDTO.setId(doutorHorario.getId());
        doutorHorarioDTO.setDiaSemana(doutorHorario.getDiaSemana());
        doutorHorarioDTO.setHorarioInicialManha(doutorHorario.getHorarioInicialManha());
        doutorHorarioDTO.setHorarioFinalManha(doutorHorario.getHorarioFinalManha());
        doutorHorarioDTO.setHorarioInicialTarde(doutorHorario.getHorarioFinalTarde());
        doutorHorarioDTO.setHorarioFinalTarde(doutorHorario.getHorarioFinalTarde());


        if (doutorHorario.getDoutorHorario() != null){
            DoutorDTO doutorDTO = new DoutorDTO();
            doutorDTO.setId(doutorHorario.getDoutorHorario().getId());
            doutorHorarioDTO.setDoutorHorario(doutorDTO);
        }
        return doutorHorarioDTO;
    }

//    public DoutorHorario toDoutorHorario(DoutorHorarioDTO doutorHorarioDTO){
//        DoutorHorario novoDoutorHorario = new DoutorHorario();
//
//        novoDoutorHorario.setId(doutorHorarioDTO.getId());
//        novoDoutorHorario.setDiaSemana(doutorHorarioDTO.getDiaSemana());
//        novoDoutorHorario.setHorarioInicialManha(doutorHorarioDTO.getHorarioInicialManha());
//        novoDoutorHorario.setHorarioFinalManha(doutorHorarioDTO.getHorarioFinalManha());
//        novoDoutorHorario.setHorarioInicialTarde(doutorHorarioDTO.getHorarioInicialTarde());
//        novoDoutorHorario.setHorarioFinalTarde(doutorHorarioDTO.getHorarioFinalTarde());
//
//        if (doutorHorarioDTO.getDoutorHorario() != null) {
//            Doutor doutor = new Doutor();
//            doutor.setId(doutorHorarioDTO.getDoutorHorario().getId());
//            novoDoutorHorario.setDoutorHorario(doutor);
//        }
//        return novoDoutorHorario;
//    }
}
