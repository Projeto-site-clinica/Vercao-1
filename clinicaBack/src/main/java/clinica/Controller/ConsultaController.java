package clinica.Controller;

import clinica.DTO.ConsultaDTO;
import clinica.DTO.MensagemDTO;
import clinica.Service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consulta")
@CrossOrigin(origins = "http://localhost:4200")
public class ConsultaController {
    @Autowired
    ConsultaService consultaService;

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> buscarPorId(@PathVariable Long id) {
        ConsultaDTO consultaDTO = consultaService.findConsultaById(id);
        if (consultaDTO != null) {
            return ResponseEntity.ok(consultaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/lista")
    public List<ConsultaDTO> listar(){
        return consultaService.listar();
    }

    @PostMapping
    public ResponseEntity<MensagemDTO> cadastrarConsulta(@RequestBody ConsultaDTO consultaDTO) {
        try{
            return ResponseEntity.ok(consultaService.cadastrarConsulta(consultaDTO));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(), HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensagemDTO> editarConsulta(@PathVariable Long id, @RequestBody ConsultaDTO consultaDTO) {
        try {
            return ResponseEntity.ok(consultaService.editarConsulta(id, consultaDTO));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(),HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemDTO> deletar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(consultaService.deletar(id));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(),HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }
}
