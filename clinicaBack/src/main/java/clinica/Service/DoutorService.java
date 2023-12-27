package clinica.Service;

import clinica.DTO.ClinicaDTO;
import clinica.DTO.ConsultaDTO;
import clinica.DTO.DoutorDTO;
import clinica.DTO.MensagemDTO;
import clinica.Entity.Clinica;
import clinica.Entity.Consulta;
import clinica.Entity.Doutor;
import clinica.Repository.DoutorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoutorService {
    @Autowired
    private DoutorRepository doutorRepository;

    public DoutorDTO findDoutorById(Long id) {
        Doutor doutor = doutorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doutor não encontrado!"));
        return doutorToDTO(doutor);
    }

    public List<DoutorDTO> listar() {
        return doutorRepository.findDoutorByAtivo().stream().map(this::doutorToDTO).toList();
    }

    public MensagemDTO cadastrarDoutor(DoutorDTO doutorDTO) {
        Doutor doutor = toDoutor(doutorDTO);
        doutorRepository.save(doutor);
        return new MensagemDTO("Doutor cadastrado com sucesso!", HttpStatus.CREATED);
    }
    public MensagemDTO editarDoutor(Long id, DoutorDTO doutorDTO) {
        Doutor doutor = toDoutor(doutorDTO);
        doutorRepository.save(doutor);
        return new MensagemDTO("Doutor atualizado com sucesso!", HttpStatus.CREATED);
    }

    public MensagemDTO deletar(Long id) {
        Doutor doutorBanco = doutorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doutor com ID " + id + " não existe!"));
        desativarDoutor(doutorBanco);
        return new MensagemDTO("Doutor deletado com sucesso!", HttpStatus.CREATED);
    }

    private void desativarDoutor(Doutor doutor) {
        doutor.setAtivo(false);
        doutorRepository.save(doutor);
    }
    public DoutorDTO doutorToDTO(Doutor doutor){
        DoutorDTO doutorDTO = new DoutorDTO();

        doutorDTO.setId(doutor.getId());
        doutorDTO.setAtivo(doutor.getAtivo());
        doutorDTO.setUsername(doutor.getUsername());
        doutorDTO.setCelular(doutor.getCelular());
        doutorDTO.setEmail(doutor.getEmail());
        doutorDTO.setPassword(doutor.getPassword());
        doutorDTO.setRole(doutor.getRole());
        doutorDTO.setCep(doutor.getCep());
        doutorDTO.setEstado(doutor.getEstado());
        doutorDTO.setCidade(doutor.getCidade());
        doutorDTO.setRua(doutor.getRua());
        doutorDTO.setBairro(doutor.getBairro());
        doutorDTO.setNumero(doutor.getNumero());
        doutorDTO.setComplemento(doutor.getComplemento());
        doutorDTO.setReferencia(doutor.getReferencia());

        doutorDTO.setRg(doutor.getRg());
        doutorDTO.setCpf(doutor.getCpf());
        doutorDTO.setDataNacimento(doutor.getDataNacimento());
        doutorDTO.setFormacao(doutor.getFormacao());
        doutorDTO.setAvaliacao(doutor.getAvaliacao());
        doutorDTO.setDescricao(doutor.getDescricao());
        doutorDTO.setSolicitacao(doutor.getSolicitacao());
        doutorDTO.setHorarioStart(doutor.getHorarioStart());
        doutorDTO.setHorarioEnd(doutor.getHorarioEnd());

        ClinicaDTO clinicaDTO = new ClinicaDTO();
        clinicaDTO.setId(doutor.getClinicaId().getId());
        doutorDTO.setClinicaId(clinicaDTO);

        List<ConsultaDTO> listaCons = new ArrayList<>();
        if(doutor.getConsulta() != null)
            for(int i=0; i<doutor.getConsulta().size(); i++){
                listaCons.add(consultaToDTO(doutor.getConsulta().get(i)));
            }
        doutorDTO.setConsulta(listaCons);

        return doutorDTO;
    }

    public ConsultaDTO consultaToDTO(Consulta consulta){
        ConsultaDTO consultaDTO = new ConsultaDTO();

        consultaDTO.setId(consulta.getId());
        consultaDTO.setAtivo(consulta.getAtivo());
        consultaDTO.setNomeConsulta(consulta.getNomeConsulta());
        consultaDTO.setDescricao(consulta.getDescricao());
        consultaDTO.setValor(consulta.getValor());
        consultaDTO.setTempo(consulta.getTempo());

        return consultaDTO;
    }

    public Doutor toDoutor(DoutorDTO doutorDTO){
        Doutor novoDoutor = new Doutor();

        novoDoutor.setId(doutorDTO.getId());
        novoDoutor.setAtivo(doutorDTO.getAtivo());
        novoDoutor.setUsername(doutorDTO.getUsername());
        novoDoutor.setCelular(doutorDTO.getCelular());
        novoDoutor.setEmail(doutorDTO.getEmail());
        novoDoutor.setPassword(doutorDTO.getPassword());
        novoDoutor.setRole(doutorDTO.getRole());
        novoDoutor.setCep(doutorDTO.getCep());
        novoDoutor.setEstado(doutorDTO.getEstado());
        novoDoutor.setCidade(doutorDTO.getCidade());
        novoDoutor.setRua(doutorDTO.getRua());
        novoDoutor.setBairro(doutorDTO.getBairro());
        novoDoutor.setNumero(doutorDTO.getNumero());
        novoDoutor.setComplemento(doutorDTO.getComplemento());
        novoDoutor.setReferencia(doutorDTO.getReferencia());

        novoDoutor.setRg(doutorDTO.getRg());
        novoDoutor.setCpf(doutorDTO.getCpf());
        novoDoutor.setDataNacimento(doutorDTO.getDataNacimento());
        novoDoutor.setFormacao(doutorDTO.getFormacao());
        novoDoutor.setAvaliacao(doutorDTO.getAvaliacao());
        novoDoutor.setDescricao(doutorDTO.getDescricao());
        novoDoutor.setSolicitacao(doutorDTO.getSolicitacao());
        novoDoutor.setHorarioStart(doutorDTO.getHorarioStart());
        novoDoutor.setHorarioEnd(doutorDTO.getHorarioEnd());

        Clinica clinica = new Clinica();
        clinica.setId(doutorDTO.getClinicaId().getId());
        novoDoutor.setClinicaId(clinica);

        List<Consulta> listaCons = new ArrayList<>();
        if(doutorDTO.getConsulta() != null)
            for(int i=0; i<doutorDTO.getConsulta().size(); i++){
                listaCons.add(toConsulta(novoDoutor,doutorDTO.getConsulta().get(i)));
            }
        novoDoutor.setConsulta(listaCons);

        return novoDoutor;
    }

    public Consulta toConsulta(Doutor novoDoutor,ConsultaDTO consultaDTO){
        Consulta novaConsulta = new Consulta();

        novaConsulta.setId(consultaDTO.getId());
        novaConsulta.setAtivo(consultaDTO.getAtivo());
        novaConsulta.setNomeConsulta(consultaDTO.getNomeConsulta());
        novaConsulta.setDescricao(consultaDTO.getDescricao());
        novaConsulta.setValor(consultaDTO.getValor());
        novaConsulta.setTempo(consultaDTO.getTempo());

        return novaConsulta;
    }
}
