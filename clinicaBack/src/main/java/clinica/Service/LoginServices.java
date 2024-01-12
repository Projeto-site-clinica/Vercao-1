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
//
//        Clinica clinica = loginRepository.findByUsernameClinica(loginDTO.getUsername()).orElse(null);
//        if (clinica != null) {
//            var jwtTokenClinica = jwtService.generateTokenClinica(clinica);
//            return toClinicaDTO(clinica, jwtTokenClinica);
//        }
//
//        Doutor doutor = loginRepository.findByUsernameDoutor(loginDTO.getUsername()).orElse(null);
//        if (doutor != null) {
//            var jwtTokenDoutor = jwtService.generateTokenDoutor(doutor);
//            return toDoutorDTO(doutor, jwtTokenDoutor);
//        }
//
//        Secretaria secretaria = loginRepository.findByUsernameSecretaria(loginDTO.getUsername()).orElse(null);
//        if (secretaria != null) {
//            var jwtTokenSecretaria = jwtService.generateTokenSecretaria(secretaria);
//            return toSecretariaDTO(secretaria, jwtTokenSecretaria);
//        }

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

//    private ClinicaDTO toClinicaDTO(Clinica clinica, String token) {
//        ClinicaDTO clinicaDTO = new ClinicaDTO();
//
//        clinicaDTO.setId(clinica.getId());
//        clinicaDTO.setRole(clinica.getRole());
//        clinicaDTO.setPassword(null);
//        clinicaDTO.setToken(token);
//        clinicaDTO.setUsername(clinica.getUsername());
//
//        return clinicaDTO;
//    }
//
//    private DoutorDTO toDoutorDTO(Doutor doutor, String token) {
//        DoutorDTO doutorDTO = new DoutorDTO();
//
//        doutorDTO.setId(doutor.getId());
//        doutorDTO.setRole(doutor.getRole());
//        doutorDTO.setPassword(null);
//        doutorDTO.setToken(token);
//        doutorDTO.setUsername(doutor.getUsername());
//
//        return doutorDTO;
//    }
//
//    private SecretariaDTO toSecretariaDTO(Secretaria secretaria, String token) {
//        SecretariaDTO secretariaDTO = new SecretariaDTO();
//
//        secretariaDTO.setId(secretaria.getId());
//        secretariaDTO.setRole(secretaria.getRole());
//        secretariaDTO.setPassword(null);
//        secretariaDTO.setToken(token);
//        secretariaDTO.setUsername(secretaria.getUsername());
//
//        return secretariaDTO;
//    }
}