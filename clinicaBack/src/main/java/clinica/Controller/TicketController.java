package clinica.Controller;

import clinica.DTO.TicketDTO;
import clinica.DTO.MensagemDTO;
import clinica.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
@CrossOrigin(origins = "http://localhost:4200")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> buscarPorId(@PathVariable Long id) {
        TicketDTO ticketDTO = ticketService.findTicketById(id);
        if (ticketDTO != null) {
            return ResponseEntity.ok(ticketDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/lista")
    public List<TicketDTO> listar(){
        return ticketService.listar();
    }

    @PostMapping
    public ResponseEntity<MensagemDTO> cadastrarTicket(@RequestBody TicketDTO ticketDTO) {
        try{
            return ResponseEntity.ok(ticketService.cadastrarTicket(ticketDTO));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(), HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensagemDTO> editarTicket(@PathVariable Long id, @RequestBody TicketDTO ticketDTO) {
        try {
            return ResponseEntity.ok(ticketService.editarTicket(id, ticketDTO));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(),HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemDTO> deletar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(ticketService.deletar(id));
        }catch(Exception e){
            MensagemDTO mensagem = new MensagemDTO(e.getMessage(),HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(mensagem);
        }
    }
}
