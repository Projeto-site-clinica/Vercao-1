package clinica.Controller;

import clinica.DTO.PacienteDTO;
import clinica.DTO.MensagemDTO;
import clinica.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
@CrossOrigin(origins = "http://localhost:4200")
public class PacienteController {
    @Autowired
    PacienteService pacienteService;

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable Long id) {
        PacienteDTO pacienteDTO = pacienteService.findPacienteById(id);
        if (pacienteDTO != null) {
            return ResponseEntity.ok(pacienteDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/lista")
    public List<PacienteDTO> listar(){
        return pacienteService.listar();
    }

    @PostMapping
    public ResponseEntity<MensagemDTO> cadastrarPaciente(@RequestBody PacienteDTO pacienteDTO) {
        try{
            return ResponseEntity.ok(pacienteService.cadastrarPaciente(pacienteDTO));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(), HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensagemDTO> editarPaciente(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) {
        try {
            return ResponseEntity.ok(pacienteService.editarPaciente(id, pacienteDTO));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(),HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemDTO> deletar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(pacienteService.deletar(id));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(),HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }
}
