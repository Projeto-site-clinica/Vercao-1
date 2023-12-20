package clinica.Controller;

import clinica.DTO.ClinicaDTO;
import clinica.DTO.MensagemDTO;
import clinica.Service.ClinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clinica")
@CrossOrigin(origins = "http://localhost:4200")
public class ClinicaController {
    @Autowired
    ClinicaService clinicaService;

    @GetMapping("/{id}")
    public ResponseEntity<ClinicaDTO> buscarPorId(@PathVariable Long id) {
        ClinicaDTO clinicaDTO = clinicaService.findClinicaById(id);
        if (clinicaDTO != null) {
            return ResponseEntity.ok(clinicaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/lista")
    public List<ClinicaDTO> listar(){
        return clinicaService.listar();
    }

    @PostMapping
    public ResponseEntity<MensagemDTO> cadastrarClinica(@RequestBody ClinicaDTO clinicaDTO) {
        try{
            return ResponseEntity.ok(clinicaService.cadastrarClinica(clinicaDTO));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(), HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensagemDTO> editarClinica(@PathVariable Long id, @RequestBody ClinicaDTO clinicaDTO) {
        try {
            return ResponseEntity.ok(clinicaService.editarClinica(id, clinicaDTO));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(),HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemDTO> deletar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(clinicaService.deletar(id));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(),HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }
}
