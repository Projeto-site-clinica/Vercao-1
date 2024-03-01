//package clinica.Service;
//
//import clinica.DTO.CalendarioDTO;
//import clinica.DTO.MensagemDTO;
//import clinica.Entity.Calendario;
//import clinica.Repository.CalendarioRepository;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//
//@Service
//public class CalendarioService {
//    @Autowired
//    private CalendarioRepository calendarioRepository;
//
//    public CalendarioDTO findCalendarioById(Long id) {
//        Calendario calendario = calendarioRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Calendario não encontrado!"));
//        return calendarioToDTO(calendario);
//    }
//
//    public List<CalendarioDTO> listar() {
//        return calendarioRepository.findCalendarioByAtivo().stream().map(this::calendarioToDTO).toList();
//    }
//
//    public MensagemDTO cadastrarCalendario(CalendarioDTO calendarioDTO) {
//        Calendario calendario = toCalendario(calendarioDTO);
//        calendarioRepository.save(calendario);
//        return new MensagemDTO("Calendario cadastrado com sucesso!", HttpStatus.CREATED);
//    }
//    public MensagemDTO editarCalendario(Long id, CalendarioDTO calendarioDTO) {
//        calendarioDTO.setId(id);
//        Calendario calendario = toCalendario(calendarioDTO);
//        calendarioRepository.save(calendario);
//        return new MensagemDTO("Calendario atualizado com sucesso!", HttpStatus.CREATED);
//    }
//
//    public MensagemDTO deletar(Long id) {
//        Calendario calendarioBanco = calendarioRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Calendario com ID " + id + " não existe!"));
//            desativarCalendario(calendarioBanco);
//        return new MensagemDTO("Calendario deletado com sucesso!", HttpStatus.CREATED);
//    }
//
//    private void desativarCalendario(Calendario calendario) {
//        calendario.setAtivo(false);
//        calendarioRepository.save(calendario);
//    }
//    public CalendarioDTO calendarioToDTO(Calendario calendario){
//        CalendarioDTO calendarioDTO = new CalendarioDTO();
//
//        calendarioDTO.setId(calendario.getId());
//        calendarioDTO.setAtivo(calendario.getAtivo());
//        calendarioDTO.setData(calendario.getData());
//
//        return calendarioDTO;
//    }
//    public Calendario toCalendario(CalendarioDTO calendarioDTO){
//        Calendario novoCalendario = new Calendario();
//
//        novoCalendario.setId(calendarioDTO.getId());
//        novoCalendario.setAtivo(calendarioDTO.getAtivo());
//        novoCalendario.setData(calendarioDTO.getData());
//
//        return novoCalendario;
//    }
//}