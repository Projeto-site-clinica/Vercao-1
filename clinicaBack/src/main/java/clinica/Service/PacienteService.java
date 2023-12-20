package clinica.Service;

import clinica.DTO.PacienteDTO;
import clinica.DTO.MensagemDTO;
import clinica.Entity.Paciente;
import clinica.Repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public PacienteDTO findPacienteById(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado!"));
        return pacienteToDTO(paciente);
    }

    public List<PacienteDTO> listar() {
        return pacienteRepository.findPacienteByAtivo().stream().map(this::pacienteToDTO).toList();
    }

    public MensagemDTO cadastrarPaciente(PacienteDTO pacienteDTO) {
        Paciente paciente = toPaciente(pacienteDTO);
        pacienteRepository.save(paciente);
        return new MensagemDTO("Paciente cadastrado com sucesso!", HttpStatus.CREATED);
    }
    public MensagemDTO editarPaciente(Long id, PacienteDTO pacienteDTO) {
        Paciente paciente = toPaciente(pacienteDTO);
        pacienteRepository.save(paciente);
        return new MensagemDTO("Paciente atualizado com sucesso!", HttpStatus.CREATED);
    }

    public MensagemDTO deletar(Long id) {
        Paciente pacienteBanco = pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente com ID " + id + " não existe!"));
        desativarPaciente(pacienteBanco);
        return new MensagemDTO("Paciente deletado com sucesso!", HttpStatus.CREATED);
    }

    private void desativarPaciente(Paciente paciente) {
        paciente.setAtivo(false);
        pacienteRepository.save(paciente);
    }
    public PacienteDTO pacienteToDTO(Paciente paciente){
        PacienteDTO pacienteDTO = new PacienteDTO();

        pacienteDTO.setId(paciente.getId());
        pacienteDTO.setAtivo(paciente.getAtivo());
        pacienteDTO.setUsername(paciente.getUsername());
        pacienteDTO.setCelular(paciente.getCelular());
        pacienteDTO.setEmail(paciente.getEmail());
        pacienteDTO.setPassword(paciente.getPassword());
        pacienteDTO.setRole(paciente.getRole());
        pacienteDTO.setCep(paciente.getCep());
        pacienteDTO.setEstado(paciente.getEstado());
        pacienteDTO.setCidade(paciente.getCidade());
        pacienteDTO.setRua(paciente.getRua());
        pacienteDTO.setBairro(paciente.getBairro());
        pacienteDTO.setNumero(paciente.getNumero());
        pacienteDTO.setComplemento(paciente.getComplemento());
        pacienteDTO.setReferencia(paciente.getReferencia());

        pacienteDTO.setCpf(paciente.getCpf());
        pacienteDTO.setRg(paciente.getRg());
        pacienteDTO.setDataNacimento(paciente.getDataNacimento());

        return pacienteDTO;
    }

    public Paciente toPaciente(PacienteDTO pacienteDTO){
        Paciente novoPaciente = new Paciente();

        novoPaciente.setId(pacienteDTO.getId());
        novoPaciente.setAtivo(pacienteDTO.getAtivo());
        novoPaciente.setUsername(pacienteDTO.getUsername());
        novoPaciente.setCelular(pacienteDTO.getCelular());
        novoPaciente.setEmail(pacienteDTO.getEmail());
        novoPaciente.setPassword(pacienteDTO.getPassword());
        novoPaciente.setRole(pacienteDTO.getRole());
        novoPaciente.setCep(pacienteDTO.getCep());
        novoPaciente.setEstado(pacienteDTO.getEstado());
        novoPaciente.setCidade(pacienteDTO.getCidade());
        novoPaciente.setRua(pacienteDTO.getRua());
        novoPaciente.setBairro(pacienteDTO.getBairro());
        novoPaciente.setNumero(pacienteDTO.getNumero());
        novoPaciente.setComplemento(pacienteDTO.getComplemento());
        novoPaciente.setReferencia(pacienteDTO.getReferencia());

        novoPaciente.setCpf(pacienteDTO.getCpf());
        novoPaciente.setRg(pacienteDTO.getRg());
        novoPaciente.setDataNacimento(pacienteDTO.getDataNacimento());

        return novoPaciente;
    }
}