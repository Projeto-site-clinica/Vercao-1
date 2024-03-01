package clinica.Controller;

import clinica.DTO.DoutorHorarioDTO;
import clinica.Service.DoutorHorarioService;
import org.springframework.beans.factory.annotation.Autowired;
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
}