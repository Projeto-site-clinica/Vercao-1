package clinica.Service;

import clinica.DTO.ConsultaDTO;
import clinica.DTO.MensagemDTO;
import clinica.Entity.Consulta;
import clinica.Repository.ConsultaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    public ConsultaDTO findConsultaById(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrado!"));
        return consultaToDTO(consulta);
    }

    public List<ConsultaDTO> listar() {
        return consultaRepository.findConsultaByAtivo().stream().map(this::consultaToDTO).toList();
    }

    public MensagemDTO cadastrarConsulta(ConsultaDTO consultaDTO) {
        Consulta consulta = toConsulta(consultaDTO);
        consultaRepository.save(consulta);
        return new MensagemDTO("Consulta cadastrado com sucesso!", HttpStatus.CREATED);
    }
    public MensagemDTO editarConsulta(Long id, ConsultaDTO consultaDTO) {
        Consulta consulta = toConsulta(consultaDTO);
        consultaRepository.save(consulta);
        return new MensagemDTO("Consulta atualizado com sucesso!", HttpStatus.CREATED);
    }

    public MensagemDTO deletar(Long id) {
        Consulta consultaBanco = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta com ID " + id + " não existe!"));
        desativarConsulta(consultaBanco);
        return new MensagemDTO("Consulta deletado com sucesso!", HttpStatus.CREATED);
    }

    private void desativarConsulta(Consulta consulta) {
        consulta.setAtivo(false);
        consultaRepository.save(consulta);
    }

    public ConsultaDTO consultaToDTO(Consulta Consulta){
        ConsultaDTO ConsultaDTO = new ConsultaDTO();

        ConsultaDTO.setId(Consulta.getId());
        ConsultaDTO.setAtivo(Consulta.getAtivo());
        ConsultaDTO.setNomeConsulta(Consulta.getNomeConsulta());
        ConsultaDTO.setDescricao(Consulta.getDescricao());
        ConsultaDTO.setValor(Consulta.getValor());
        ConsultaDTO.setTempo(Consulta.getTempo());

        return ConsultaDTO;
    }

    public Consulta toConsulta(ConsultaDTO ConsultaDTO){
        Consulta novaConsulta = new Consulta();

        novaConsulta.setId(ConsultaDTO.getId());
        novaConsulta.setAtivo(ConsultaDTO.getAtivo());
        novaConsulta.setNomeConsulta(ConsultaDTO.getNomeConsulta());
        novaConsulta.setDescricao(ConsultaDTO.getDescricao());
        novaConsulta.setValor(ConsultaDTO.getValor());
        novaConsulta.setTempo(ConsultaDTO.getTempo());

        return novaConsulta;
    }
}
