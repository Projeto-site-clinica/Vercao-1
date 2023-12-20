package clinica.Controller;

import clinica.DTO.SecretariaDTO;
import clinica.DTO.MensagemDTO;
import clinica.Service.SecretariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secretaria")
@CrossOrigin(origins = "http://localhost:4200")
public class SecretariaController {
    @Autowired
    SecretariaService secretariaService;

    @GetMapping("/{id}")
    public ResponseEntity<SecretariaDTO> buscarPorId(@PathVariable Long id) {
        SecretariaDTO secretariaDTO = secretariaService.findSecretariaById(id);
        if (secretariaDTO != null) {
            return ResponseEntity.ok(secretariaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/lista")
    public List<SecretariaDTO> listar(){
        return secretariaService.listar();
    }

    @PostMapping
    public ResponseEntity<MensagemDTO> cadastrarSecretaria(@RequestBody SecretariaDTO secretariaDTO) {
        try{
            return ResponseEntity.ok(secretariaService.cadastrarSecretaria(secretariaDTO));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(), HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensagemDTO> editarSecretaria(@PathVariable Long id, @RequestBody SecretariaDTO secretariaDTO) {
        try {
            return ResponseEntity.ok(secretariaService.editarSecretaria(id, secretariaDTO));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(),HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemDTO> deletar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(secretariaService.deletar(id));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(),HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }
}
