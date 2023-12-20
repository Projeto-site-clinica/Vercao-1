//package clinica.Service;
//
//import clinica.Config.JwtServiceGenerator;
//import clinica.DTO.LoginDTO;
//import clinica.DTO.MensagemDTO;
//import clinica.DTO.PacienteDTO;
//import clinica.Entity.Paciente;
//import clinica.Repository.LoginRepository;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//
//@Service
//public class LoginServices {
//
//    @Autowired
//    private LoginRepository loginRepository;
//    @Autowired
//    private JwtServiceGenerator jwtService;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//
//    public PacienteDTO logar(LoginDTO loginDTO) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginDTO.getUsername(),
//                        loginDTO.getPassword()
//                )
//        );
//        Paciente paciente = loginRepository.findByUsername(loginDTO.getUsername()).orElseThrow();
//        var jwtToken = jwtService.generateToken(paciente);
//
//        return toPacienteDTO(paciente, jwtToken);
//    }
//
//    public List<PacienteDTO> listar() {
//        return loginRepository.findUserByAtivo().stream().map(this::pacienteToDTO).toList();
//    }
//
//    public MensagemDTO cadastrarUser(PacienteDTO pacienteDTO) {
//        Paciente paciente = toUser(pacienteDTO);
//        paciente.setPassword(passwordEncoder.encode(paciente.getPassword()));
//        loginRepository.save(paciente);
//        return new MensagemDTO("Paciente cadastrado com sucesso!", HttpStatus.CREATED);
//    }
//    public MensagemDTO editarUser(Long id, PacienteDTO pacienteDTO) {
//        Paciente paciente = toUser(pacienteDTO);
//        String senha= loginRepository.findSenhaById(paciente.getId());
//        paciente.setPassword(senha);
//        loginRepository.save(paciente);
//        return new MensagemDTO("Paciente atualizado com sucesso!", HttpStatus.CREATED);
//    }
//
//    public MensagemDTO deletar(Long id) {
//        Paciente pacienteBanco = loginRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Paciente com ID " + id + " não existe!"));
//        desativarUser(pacienteBanco);
//
//        return new MensagemDTO("Paciente deletado com sucesso!", HttpStatus.CREATED);
//    }
//
//    private void desativarUser(Paciente paciente) {
//        paciente.setAtivo(false);
//        loginRepository.save(paciente);
//    }
//
//    public PacienteDTO pacienteToDTO(Paciente paciente){
//        PacienteDTO pacienteDTO = new PacienteDTO();
//
//        pacienteDTO.setId(paciente.getId());
//        pacienteDTO.setAtivo(paciente.getAtivo());
//        pacienteDTO.setUsername(paciente.getUsername());
//       // pacienteDTO.setPassword(paciente.getPassword());
//        pacienteDTO.setRole(paciente.getRole());
//
//        return pacienteDTO;
//    }
//    public Paciente toUser(PacienteDTO pacienteDTO){
//        Paciente novoUser = new Paciente();
//
//        novoUser.setId(pacienteDTO.getId());
//        novoUser.setAtivo(pacienteDTO.getAtivo());
//        novoUser.setUsername(pacienteDTO.getUsername());
//        novoUser.setPassword(pacienteDTO.getPassword());
//        novoUser.setRole(pacienteDTO.getRole());
//
//        return novoUser;
//    }
//
//
//    private PacienteDTO toPacienteDTO(Paciente paciente, String token) {
//        PacienteDTO pacienteDTO = new PacienteDTO();
//        pacienteDTO.setId(paciente.getId());
//        pacienteDTO.setRole(paciente.getRole());
//        pacienteDTO.setPassword(token);
//        pacienteDTO.setUsername(paciente.getUsername());
//        return pacienteDTO;
//    }
//
//}