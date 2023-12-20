package clinica.Controller;

import clinica.DTO.CalendarioDTO;
import clinica.DTO.MensagemDTO;
import clinica.Service.CalendarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calendario")
@CrossOrigin(origins = "http://localhost:4200")
public class CalendarioController {
    @Autowired
    CalendarioService calendarioService;

    @GetMapping("/{id}")
    public ResponseEntity<CalendarioDTO> buscarPorId(@PathVariable Long id) {
        CalendarioDTO calendarioDTO = calendarioService.findCalendarioById(id);
        if (calendarioDTO != null) {
            return ResponseEntity.ok(calendarioDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/lista")
    public List<CalendarioDTO> listar(){
        return calendarioService.listar();
    }

    @PostMapping
    public ResponseEntity<MensagemDTO> cadastrarCalendario(@RequestBody CalendarioDTO calendarioDTO) {
        try{
            return ResponseEntity.ok(calendarioService.cadastrarCalendario(calendarioDTO));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(), HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensagemDTO> editarCalendario(@PathVariable Long id, @RequestBody CalendarioDTO calendarioDTO) {
        try {
            return ResponseEntity.ok(calendarioService.editarCalendario(id, calendarioDTO));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(),HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemDTO> deletar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(calendarioService.deletar(id));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(),HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }
}