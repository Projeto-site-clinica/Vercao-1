package clinica.Service;

import clinica.DTO.*;
import clinica.Entity.*;
import clinica.Repository.ClinicaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClinicaService {
    @Autowired
    private ClinicaRepository clinicaRepository;

    public ClinicaDTO findClinicaById(Long id) {
        Clinica clinica = clinicaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Clinica não encontrado!"));
        return clinicaToDTO(clinica);
    }

    public List<ClinicaDTO> listar() {
        return clinicaRepository.findClinicaByAtivo().stream().map(this::clinicaToDTO).toList();
    }

    public MensagemDTO cadastrarClinica(ClinicaDTO clinicaDTO) {
        Clinica clinica = toClinica(clinicaDTO);
        clinicaRepository.save(clinica);
        return new MensagemDTO("Clinica cadastrado com sucesso!", HttpStatus.CREATED);
    }
    public MensagemDTO editarClinica(Long id, ClinicaDTO clinicaDTO) {
        Clinica clinica = toClinica(clinicaDTO);
        clinicaRepository.save(clinica);
        return new MensagemDTO("Clinica atualizado com sucesso!", HttpStatus.CREATED);
    }

    public MensagemDTO deletar(Long id) {
        Clinica clinicaBanco = clinicaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Clinica com ID " + id + " não existe!"));
        desativarClinica(clinicaBanco);
        return new MensagemDTO("Clinica deletado com sucesso!", HttpStatus.CREATED);
    }

    private void desativarClinica(Clinica clinica) {
        clinica.setAtivo(false);
        clinicaRepository.save(clinica);
    }
    public ClinicaDTO clinicaToDTO(Clinica clinica){
        ClinicaDTO clinicaDTO = new ClinicaDTO();

        clinicaDTO.setId(clinica.getId());
        clinicaDTO.setAtivo(clinica.getAtivo());
        clinicaDTO.setUsername(clinica.getUsername());
        clinicaDTO.setCelular(clinica.getCelular());
        clinicaDTO.setEmail(clinica.getEmail());
        clinicaDTO.setPassword(clinica.getPassword());
        clinicaDTO.setRole(clinica.getRole());
        clinicaDTO.setCep(clinica.getCep());
        clinicaDTO.setEstado(clinica.getEstado());
        clinicaDTO.setCidade(clinica.getCidade());
        clinicaDTO.setRua(clinica.getRua());
        clinicaDTO.setBairro(clinica.getBairro());
        clinicaDTO.setNumero(clinica.getNumero());
        clinicaDTO.setComplemento(clinica.getComplemento());
        clinicaDTO.setReferencia(clinica.getReferencia());

        clinicaDTO.setCnpj(clinica.getCnpj());
        clinicaDTO.setAvaliacao(clinica.getAvaliacao());
        clinicaDTO.setDiscricao(clinica.getDiscricao());
        clinicaDTO.setSolicitacao(clinica.getSolicitacao());

        List<DoutorDTO> listaDou = new ArrayList<>();
        if(clinica.getDoutores() != null)
            for(int i=0; i<clinica.getDoutores().size(); i++){
                listaDou.add(doutorToDTO(clinica.getDoutores().get(i)));
            }
        clinicaDTO.setDoutores(listaDou);

        List<SecretariaDTO> listaSec = new ArrayList<>();
        if(clinica.getSecretaria() != null)
            for(int i=0; i<clinica.getSecretaria().size(); i++){
                listaSec.add(secretariaToDTO(clinica.getSecretaria().get(i)));
            }
        clinicaDTO.setSecretaria(listaSec);

        return clinicaDTO;
    }

    public SecretariaDTO secretariaToDTO(Secretaria secretaria){
        SecretariaDTO secretariaDTO = new SecretariaDTO();

        secretariaDTO.setId(secretaria.getId());
        secretariaDTO.setAtivo(secretaria.getAtivo());
        secretariaDTO.setUsername(secretaria.getUsername());
        secretariaDTO.setCelular(secretaria.getCelular());
        secretariaDTO.setEmail(secretaria.getEmail());
        secretariaDTO.setPassword(secretaria.getPassword());
        secretariaDTO.setRole(secretaria.getRole());
        secretariaDTO.setCep(secretaria.getCep());
        secretariaDTO.setEstado(secretaria.getEstado());
        secretariaDTO.setCidade(secretaria.getCidade());
        secretariaDTO.setRua(secretaria.getRua());
        secretariaDTO.setBairro(secretaria.getBairro());
        secretariaDTO.setNumero(secretaria.getNumero());
        secretariaDTO.setComplemento(secretaria.getComplemento());
        secretariaDTO.setReferencia(secretaria.getReferencia());

        secretariaDTO.setRg(secretaria.getRg());
        secretariaDTO.setCpf(secretaria.getCpf());
        secretariaDTO.setDataNacimento(secretaria.getDataNacimento());

        return secretariaDTO;
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
        doutorDTO.setDiscricao(doutor.getDiscricao());
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

    public Clinica toClinica(ClinicaDTO clinicaDTO){
        Clinica novoClinica = new Clinica();

        novoClinica.setId(clinicaDTO.getId());
        novoClinica.setAtivo(clinicaDTO.getAtivo());
        novoClinica.setUsername(clinicaDTO.getUsername());
        novoClinica.setCelular(clinicaDTO.getCelular());
        novoClinica.setEmail(clinicaDTO.getEmail());
        novoClinica.setPassword(clinicaDTO.getPassword());
        novoClinica.setRole(clinicaDTO.getRole());
        novoClinica.setCep(clinicaDTO.getCep());
        novoClinica.setEstado(clinicaDTO.getEstado());
        novoClinica.setCidade(clinicaDTO.getCidade());
        novoClinica.setRua(clinicaDTO.getRua());
        novoClinica.setBairro(clinicaDTO.getBairro());
        novoClinica.setNumero(clinicaDTO.getNumero());
        novoClinica.setComplemento(clinicaDTO.getComplemento());
        novoClinica.setReferencia(clinicaDTO.getReferencia());

        novoClinica.setCnpj(clinicaDTO.getCnpj());
        novoClinica.setAvaliacao(clinicaDTO.getAvaliacao());
        novoClinica.setDiscricao(clinicaDTO.getDiscricao());
        novoClinica.setSolicitacao(clinicaDTO.getSolicitacao());

        List<Doutor> listaDou = new ArrayList<>();
        if(clinicaDTO.getDoutores() != null)
            for(int i=0; i<clinicaDTO.getDoutores().size(); i++){
                listaDou.add(toDoutor(novoClinica,clinicaDTO.getDoutores().get(i)));
            }
        novoClinica.setDoutores(listaDou);

        List<Secretaria> listaSec = new ArrayList<>();
        if(clinicaDTO.getSecretaria() != null)
            for(int i=0; i<clinicaDTO.getSecretaria().size(); i++){
                listaSec.add(toSecretaria(novoClinica,clinicaDTO.getSecretaria().get(i)));
            }
        novoClinica.setSecretaria(listaSec);

        return novoClinica;
    }

    public Secretaria toSecretaria(Clinica novoClinica, SecretariaDTO secretariaDTO){
        Secretaria novaSecretaria = new Secretaria();

        novaSecretaria.setId(secretariaDTO.getId());
        novaSecretaria.setAtivo(secretariaDTO.getAtivo());
        novaSecretaria.setUsername(secretariaDTO.getUsername());
        novaSecretaria.setCelular(secretariaDTO.getCelular());
        novaSecretaria.setEmail(secretariaDTO.getEmail());
        novaSecretaria.setPassword(secretariaDTO.getPassword());
        novaSecretaria.setRole(secretariaDTO.getRole());
        novaSecretaria.setCep(secretariaDTO.getCep());
        novaSecretaria.setEstado(secretariaDTO.getEstado());
        novaSecretaria.setCidade(secretariaDTO.getCidade());
        novaSecretaria.setRua(secretariaDTO.getRua());
        novaSecretaria.setBairro(secretariaDTO.getBairro());
        novaSecretaria.setNumero(secretariaDTO.getNumero());
        novaSecretaria.setComplemento(secretariaDTO.getComplemento());
        novaSecretaria.setReferencia(secretariaDTO.getReferencia());

        novaSecretaria.setRg(secretariaDTO.getRg());
        novaSecretaria.setCpf(secretariaDTO.getCpf());
        novaSecretaria.setDataNacimento(secretariaDTO.getDataNacimento());

        return novaSecretaria;
    }

    public Doutor toDoutor(Clinica novoClinica, DoutorDTO doutorDTO){
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
        novoDoutor.setDiscricao(doutorDTO.getDiscricao());
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