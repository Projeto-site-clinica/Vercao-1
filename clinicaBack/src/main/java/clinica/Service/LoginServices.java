package clinica.Service;

import clinica.Config.JwtServiceGenerator;
import clinica.DTO.*;
import clinica.Entity.*;
import clinica.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServices {

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private JwtServiceGenerator jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public UsuarioDTO logar(LoginDTO loginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getSenha()
                )
        );

        Usuario usuario = loginRepository.findByUsername(loginDTO.getUsername()).orElse(null);
        if (usuario != null) {
            var jwtToken = jwtService.generateToken(usuario);
            return toUsuarioDTO(usuario, jwtToken);
        }

        throw new UsernameNotFoundException("Usuário não encontrado");
    }

    private UsuarioDTO toUsuarioDTO(Usuario usuario, String token) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setRole(usuario.getRole());
        usuarioDTO.setPassword(null);
        usuarioDTO.setToken(token);
        usuarioDTO.setUsername(usuario.getUsername());
        return usuarioDTO;
    }
}