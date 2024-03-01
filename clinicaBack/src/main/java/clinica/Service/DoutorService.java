package clinica.Service;

import clinica.DTO.*;
import clinica.Entity.Clinica;
import clinica.Entity.Consulta;
import clinica.Entity.Doutor;
import clinica.Repository.DoutorRepository;
import clinica.Repository.LoginRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoutorService {
    @Autowired
    private DoutorRepository doutorRepository;
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
        doutor.setRole("DOUTOR");
        doutor.setPassword(passwordEncoder.encode(doutor.getPassword()));
        doutorRepository.save(doutor);
        return new MensagemDTO("Doutor cadastrado com sucesso!", HttpStatus.CREATED);
    }
    public MensagemDTO editarDoutor(Long id, DoutorDTO doutorDTO) {
        doutorDTO.setId(id);
        Doutor doutor = toDoutor(doutorDTO);
        String senha= loginRepository.findSenhaById(doutor.getId());
        doutor.setPassword(senha);
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
        doutorDTO.setNome(doutor.getNome());
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

        if (doutor.getClinica() != null){
            ClinicaDTO clinicaDTO = new ClinicaDTO();
            clinicaDTO.setId(doutor.getClinica().getId());
            doutorDTO.setClinica(clinicaDTO);
        }

        List<ConsultaDTO> listaCons = new ArrayList<>();
        if(doutor.getConsultas() != null)
            for(int i=0; i<doutor.getConsultas().size(); i++){
                listaCons.add(consultaToDTO(doutor.getConsultas().get(i)));
            }
        doutorDTO.setConsultas(listaCons);

        return doutorDTO;
    }

    public ConsultaDTO consultaToDTO(Consulta consulta){
        ConsultaDTO consultaDTO = new ConsultaDTO();

        consultaDTO.setId(consulta.getId());
        consultaDTO.setAtivo(consulta.getAtivo());
        consultaDTO.setNomeConsulta(consulta.getNomeConsulta());
        consultaDTO.setDescricao(consulta.getDescricao());
        consultaDTO.setValor(consulta.getValor());
        consultaDTO.setDuracao(consulta.getDuracao());

        if (consulta.getDoutorConsulta() != null){
            DoutorDTO doutorDTO = new DoutorDTO();
            consultaDTO.setId(consulta.getDoutorConsulta().getId());
            consultaDTO.setDoutorConsulta(doutorDTO);
        }
        return consultaDTO;
    }

    public Doutor toDoutor(DoutorDTO doutorDTO){
        Doutor novoDoutor = new Doutor();

        novoDoutor.setId(doutorDTO.getId());
        novoDoutor.setAtivo(doutorDTO.getAtivo());
        novoDoutor.setNome(doutorDTO.getNome());
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

        if (doutorDTO.getClinica() != null) {
            Clinica clinica = new Clinica();
            clinica.setId(doutorDTO.getClinica().getId());
            novoDoutor.setClinica(clinica);
        }

        List<Consulta> listaCons = new ArrayList<>();
        if(doutorDTO.getConsultas() != null)
            for(int i=0; i<doutorDTO.getConsultas().size(); i++){
                listaCons.add(toConsulta(novoDoutor,doutorDTO.getConsultas().get(i)));
            }
        novoDoutor.setConsultas(listaCons);

        return novoDoutor;
    }

    public Consulta toConsulta(Doutor novoDoutor,ConsultaDTO consultaDTO){
        Consulta novaConsulta = new Consulta();

        novaConsulta.setId(consultaDTO.getId());
        novaConsulta.setAtivo(consultaDTO.getAtivo());
        novaConsulta.setNomeConsulta(consultaDTO.getNomeConsulta());
        novaConsulta.setDescricao(consultaDTO.getDescricao());
        novaConsulta.setValor(consultaDTO.getValor());
        novaConsulta.setDuracao(consultaDTO.getDuracao());

        if (consultaDTO.getDoutorConsulta() != null){
            Doutor doutor = new Doutor();
            novaConsulta.setId(consultaDTO.getDoutorConsulta().getId());
            novaConsulta.setDoutorConsulta(doutor);
        }
        return novaConsulta;
    }
}