//package clinica.Controller;
//
//import clinica.DTO.LoginDTO;
//import clinica.DTO.MensagemDTO;
//import clinica.DTO.PacienteDTO;
//import clinica.Service.LoginServices;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/login")
//@CrossOrigin(origins = "http://localhost:4200")
//public class LoginController {
//
//    @Autowired
//    private LoginServices loginService;
//
//    @PostMapping
//    public ResponseEntity<PacienteDTO> logar(@RequestBody LoginDTO loginDTO) {
//        try {
//            return ResponseEntity.ok(loginService.logar(loginDTO));
//        }catch(AuthenticationException ex) {
//            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
//        }catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @GetMapping("deslogar")
//    public ResponseEntity<HttpStatus> logout() {
//
//        SecurityContextHolder.clearContext();
//        return new ResponseEntity<>(null, HttpStatus.OK);
//
//    }
//
//    @GetMapping("/lista")
//    public List<PacienteDTO> listar(){
//        return loginService.listar();
//    }
//
//    @PostMapping("/user")
//    public ResponseEntity<MensagemDTO> cadastrarUser(@RequestBody PacienteDTO userDTO) {
//        try{
//            return ResponseEntity.ok(loginService.cadastrarUser(userDTO));
//        }catch(Exception e){
//            MensagemDTO mensagem = new MensagemDTO(e.getMessage(),HttpStatus.BAD_REQUEST);
//            return ResponseEntity.badRequest().body(mensagem);
//        }
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<MensagemDTO> editarUser(@PathVariable Long id, @RequestBody PacienteDTO userDTO) {
//        try {
//            return ResponseEntity.ok(loginService.editarUser(id, userDTO));
//        }catch(Exception e){
//            MensagemDTO mensagem = new MensagemDTO(e.getMessage(),HttpStatus.BAD_REQUEST);
//            return ResponseEntity.badRequest().body(mensagem);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<MensagemDTO> deletar(@PathVariable Long id) {
//        try {
//            return ResponseEntity.ok(loginService.deletar(id));
//        }catch(Exception e){
//            MensagemDTO mensagem = new MensagemDTO(e.getMessage(),HttpStatus.BAD_REQUEST);
//            return ResponseEntity.badRequest().body(mensagem);
//        }
//    }
//}