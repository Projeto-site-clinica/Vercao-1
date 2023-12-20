package clinica.Service;

import clinica.DTO.*;
import clinica.Entity.*;
import clinica.Repository.TicketRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public TicketDTO findTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket não encontrado!"));
        return ticketToDTO(ticket);
    }

    public List<TicketDTO> listar() {
        return ticketRepository.findTicketByAtivo().stream().map(this::ticketToDTO).toList();
    }

    public MensagemDTO cadastrarTicket(TicketDTO ticketDTO) {
        Ticket ticket = toTicket(ticketDTO);
        ticketRepository.save(ticket);
        return new MensagemDTO("Ticket cadastrado com sucesso!", HttpStatus.CREATED);
    }
    public MensagemDTO editarTicket(Long id, TicketDTO ticketDTO) {
        Ticket ticket = toTicket(ticketDTO);
        ticketRepository.save(ticket);
        return new MensagemDTO("Ticket atualizado com sucesso!", HttpStatus.CREATED);
    }

    public MensagemDTO deletar(Long id) {
        Ticket ticketBanco = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket com ID " + id + " não existe!"));
        desativarTicket(ticketBanco);
        return new MensagemDTO("Ticket deletado com sucesso!", HttpStatus.CREATED);
    }

    private void desativarTicket(Ticket ticket) {
        ticket.setAtivo(false);
        ticketRepository.save(ticket);
    }
    public TicketDTO ticketToDTO(Ticket ticket){
        TicketDTO ticketDTO = new TicketDTO();

        ticketDTO.setId(ticket.getId());
        ticketDTO.setAtivo(ticket.getAtivo());
        ticketDTO.setLogMarcar(ticket.getLogMarcar());

        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(ticket.getPacienteId().getId());
        ticketDTO.setPacienteId(pacienteDTO);

        DoutorDTO doutorDTO = new DoutorDTO();
        doutorDTO.setId(ticket.getDoutorId().getId());
        ticketDTO.setDoutorId(doutorDTO);

        CalendarioDTO calendarioDTO = new CalendarioDTO();
        calendarioDTO.setId(ticket.getData().getId());
        ticketDTO.setData(calendarioDTO);

        ConsultaDTO consultaDTO = new ConsultaDTO();
        consultaDTO.setId(ticket.getConsulta().getId());
        ticketDTO.setConsulta(consultaDTO);

        return ticketDTO;
    }

    public Ticket toTicket(TicketDTO ticketDTO){
        Ticket novoTicket = new Ticket();

        novoTicket.setId(ticketDTO.getId());
        novoTicket.setAtivo(ticketDTO.getAtivo());
        novoTicket.setLogMarcar(ticketDTO.getLogMarcar());

        Paciente paciente = new Paciente();
        paciente.setId(ticketDTO.getPacienteId().getId());
        novoTicket.setPacienteId(paciente);

        Doutor doutor = new Doutor();
        doutor.setId(ticketDTO.getDoutorId().getId());
        novoTicket.setDoutorId(doutor);

        Calendario calendario = new Calendario();
        calendario.setId(ticketDTO.getData().getId());
        novoTicket.setData(calendario);

        Consulta consulta = new Consulta();
        consulta.setId(ticketDTO.getConsulta().getId());
        novoTicket.setConsulta(consulta);

        return novoTicket;
    }
}
