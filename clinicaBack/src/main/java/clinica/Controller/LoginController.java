package clinica.Controller;

import clinica.DTO.*;
import clinica.Service.LoginServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    @Autowired
    private LoginServices loginService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> logar(@RequestBody LoginDTO loginDTO) {
        try {
            return ResponseEntity.ok(loginService.logar(loginDTO));
        }catch(AuthenticationException ex) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("deslogar")
    public ResponseEntity<HttpStatus> logout() {

        SecurityContextHolder.clearContext();
        return new ResponseEntity<>(null, HttpStatus.OK);

    }
}