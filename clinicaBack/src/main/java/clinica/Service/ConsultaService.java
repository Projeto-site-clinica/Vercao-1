package clinica.Service;

import clinica.DTO.ConsultaDTO;
import clinica.DTO.DoutorDTO;
import clinica.DTO.MensagemDTO;
import clinica.Entity.Consulta;
import clinica.Entity.Doutor;
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
        consultaDTO.setId(id);
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

    public ConsultaDTO consultaToDTO(Consulta consulta){
        ConsultaDTO consultaDTO = new ConsultaDTO();

        consultaDTO.setId(consulta.getId());
        consultaDTO.setAtivo(consulta.getAtivo());
        consultaDTO.setNomeConsulta(consulta.getNomeConsulta());
        consultaDTO.setDescricao(consulta.getDescricao());
        consultaDTO.setValor(consulta.getValor());
        consultaDTO.setTempo(consulta.getTempo());

        DoutorDTO doutorDTO = new DoutorDTO();
        if (consulta.getDoutor() != null){
            doutorDTO.setId(consulta.getDoutor().getId());
            consultaDTO.setDoutor(doutorDTO);
        }

        return consultaDTO;
    }

    public Consulta toConsulta(ConsultaDTO consultaDTO){
        Consulta novaConsulta = new Consulta();

        novaConsulta.setId(consultaDTO.getId());
        novaConsulta.setAtivo(consultaDTO.getAtivo());
        novaConsulta.setNomeConsulta(consultaDTO.getNomeConsulta());
        novaConsulta.setDescricao(consultaDTO.getDescricao());
        novaConsulta.setValor(consultaDTO.getValor());
        novaConsulta.setTempo(consultaDTO.getTempo());

        Doutor doutor = new Doutor();
        if (consultaDTO.getDoutor() != null){
            doutor.setId(consultaDTO.getDoutor().getId());
            novaConsulta.setDoutor(doutor);
        }

        return novaConsulta;
    }
}
