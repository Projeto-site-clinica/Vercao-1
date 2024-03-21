package clinica.Controller;

import clinica.DTO.DoutorHorarioDTO;
import clinica.DTO.MensagemDTO;
import clinica.Service.DoutorHorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doutorHorario")
@CrossOrigin(origins = "http://localhost:4200")
public class DoutorHorarioController {
    @Autowired
    DoutorHorarioService doutorHorarioService;

    @GetMapping("/{id}")
    public ResponseEntity<DoutorHorarioDTO> buscarPorId(@PathVariable Long id) {
        DoutorHorarioDTO doutorHorarioDTO = doutorHorarioService.findDoutorHorarioById(id);
        if (doutorHorarioDTO != null) {
            return ResponseEntity.ok(doutorHorarioDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/lista")
    public List<DoutorHorarioDTO> listar(){
        return doutorHorarioService.listar();
    }
    @PostMapping
    public ResponseEntity<MensagemDTO> cadastrarDoutorHorario(@RequestBody DoutorHorarioDTO doutorHorarioDTO) {
        try{
            return ResponseEntity.ok(doutorHorarioService.cadastrarDoutorHorario(doutorHorarioDTO));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(), HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensagemDTO> editarDoutorHorario(@PathVariable Long id, @RequestBody DoutorHorarioDTO doutorHorarioDTO) {
        try {
            return ResponseEntity.ok(doutorHorarioService.editarDoutorHorario(id, doutorHorarioDTO));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(),HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemDTO> deletarDoutorHorario(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(doutorHorarioService.deletarDoutorHorario(id));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(),HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }
}