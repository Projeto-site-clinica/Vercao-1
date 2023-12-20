package clinica.Controller;

import clinica.DTO.DoutorDTO;
import clinica.DTO.MensagemDTO;
import clinica.Service.DoutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doutor")
@CrossOrigin(origins = "http://localhost:4200")
public class DoutorController {
    @Autowired
    DoutorService doutorService;

    @GetMapping("/{id}")
    public ResponseEntity<DoutorDTO> buscarPorId(@PathVariable Long id) {
        DoutorDTO doutorDTO = doutorService.findDoutorById(id);
        if (doutorDTO != null) {
            return ResponseEntity.ok(doutorDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/lista")
    public List<DoutorDTO> listar(){
        return doutorService.listar();
    }

    @PostMapping
    public ResponseEntity<MensagemDTO> cadastrarDoutor(@RequestBody DoutorDTO doutorDTO) {
        try{
            return ResponseEntity.ok(doutorService.cadastrarDoutor(doutorDTO));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(), HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensagemDTO> editarDoutor(@PathVariable Long id, @RequestBody DoutorDTO doutorDTO) {
        try {
            return ResponseEntity.ok(doutorService.editarDoutor(id, doutorDTO));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(),HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemDTO> deletar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(doutorService.deletar(id));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(),HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }
}
