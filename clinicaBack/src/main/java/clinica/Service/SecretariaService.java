package clinica.Service;

import clinica.DTO.MensagemDTO;
import clinica.DTO.SecretariaDTO;
import clinica.Entity.Secretaria;
import clinica.Repository.SecretariaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecretariaService {
    @Autowired
    private SecretariaRepository secretariaRepository;

    public SecretariaDTO findSecretariaById(Long id) {
        Secretaria secretaria = secretariaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Secretaria não encontrado!"));
        return secretariaToDTO(secretaria);
    }

    public List<SecretariaDTO> listar() {
        return secretariaRepository.findSecretariaByAtivo().stream().map(this::secretariaToDTO).toList();
    }

    public MensagemDTO cadastrarSecretaria(SecretariaDTO secretariaDTO) {
        Secretaria secretaria = toSecretaria(secretariaDTO);
        secretariaRepository.save(secretaria);
        return new MensagemDTO("Secretaria cadastrado com sucesso!", HttpStatus.CREATED);
    }
    public MensagemDTO editarSecretaria(Long id, SecretariaDTO secretariaDTO) {
        Secretaria secretaria = toSecretaria(secretariaDTO);
        secretariaRepository.save(secretaria);
        return new MensagemDTO("Secretaria atualizado com sucesso!", HttpStatus.CREATED);
    }

    public MensagemDTO deletar(Long id) {
        Secretaria secretariaBanco = secretariaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Secretaria com ID " + id + " não existe!"));
        desativarSecretaria(secretariaBanco);
        return new MensagemDTO("Secretaria deletado com sucesso!", HttpStatus.CREATED);
    }

    private void desativarSecretaria(Secretaria secretaria) {
        secretaria.setAtivo(false);
        secretariaRepository.save(secretaria);
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

        secretariaDTO.setCpf(secretaria.getCpf());
        secretariaDTO.setRg(secretaria.getRg());
        secretariaDTO.setDataNacimento(secretaria.getDataNacimento());

        return secretariaDTO;
    }

    public Secretaria toSecretaria(SecretariaDTO secretariaDTO){
        Secretaria novoSecretaria = new Secretaria();

        novoSecretaria.setId(secretariaDTO.getId());
        novoSecretaria.setAtivo(secretariaDTO.getAtivo());
        novoSecretaria.setUsername(secretariaDTO.getUsername());
        novoSecretaria.setCelular(secretariaDTO.getCelular());
        novoSecretaria.setEmail(secretariaDTO.getEmail());
        novoSecretaria.setPassword(secretariaDTO.getPassword());
        novoSecretaria.setRole(secretariaDTO.getRole());
        novoSecretaria.setCep(secretariaDTO.getCep());
        novoSecretaria.setEstado(secretariaDTO.getEstado());
        novoSecretaria.setCidade(secretariaDTO.getCidade());
        novoSecretaria.setRua(secretariaDTO.getRua());
        novoSecretaria.setBairro(secretariaDTO.getBairro());
        novoSecretaria.setNumero(secretariaDTO.getNumero());
        novoSecretaria.setComplemento(secretariaDTO.getComplemento());
        novoSecretaria.setReferencia(secretariaDTO.getReferencia());

        novoSecretaria.setCpf(secretariaDTO.getCpf());
        novoSecretaria.setRg(secretariaDTO.getRg());
        novoSecretaria.setDataNacimento(secretariaDTO.getDataNacimento());

        return novoSecretaria;
    }
}