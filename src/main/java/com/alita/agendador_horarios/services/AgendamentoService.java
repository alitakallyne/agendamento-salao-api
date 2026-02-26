package com.alita.agendador_horarios.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.alita.agendador_horarios.infrastructure.dto.CriarAgendamentoRequest;
import com.alita.agendador_horarios.infrastructure.entity.Agendamento;
import com.alita.agendador_horarios.infrastructure.entity.Cliente;
import com.alita.agendador_horarios.infrastructure.entity.HorarioFuncionamento;
import com.alita.agendador_horarios.infrastructure.entity.Profissional;
import com.alita.agendador_horarios.infrastructure.entity.Servico;
import com.alita.agendador_horarios.infrastructure.entity.StatusAgendamento;
import com.alita.agendador_horarios.infrastructure.repository.AgendamentoRepository;
import com.alita.agendador_horarios.infrastructure.repository.ClienteRepository;
import com.alita.agendador_horarios.infrastructure.repository.HorarioFuncionamentoRepository;
import com.alita.agendador_horarios.infrastructure.repository.ProfissionalRepository;
import com.alita.agendador_horarios.infrastructure.repository.ServicoRepository;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ServicoRepository servicoRepository;
    private final ProfissionalRepository profissionalRepository;
    private final ClienteRepository clienteRepository;
    private final HorarioFuncionamentoRepository horarioFuncionamentoRepository;

    public Agendamento salvarAgendamento(CriarAgendamentoRequest request) {

        Servico servico = servicoRepository.findById(request.getServicoId())
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        Profissional profissional = profissionalRepository.findById(request.getProfissionalId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));
        
        if (!profissional.getAtivo()) {
            throw new RuntimeException(
                "Profissional não está ativo para agendamentos"
            );
        }

        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        LocalDateTime inicio = request.getDataHoraInicio();
        LocalDateTime fim = inicio.plusMinutes(servico.getDuracaoEmMinutos());

        if (inicio.isBefore(LocalDateTime.now())) {
            throw new RuntimeException(
                "Não é possível agendar para um horário no passado"
            );
        }

        validarHorarioFuncionamento(inicio, fim);

        boolean profissionalDisponivel = agendamentoRepository
                .existsByProfissionalAndDataHoraInicioLessThanAndDataHoraFimGreaterThan(
                        profissional, fim, inicio);
        if (profissionalDisponivel) {
            throw new RuntimeException("Profissional não está disponível nesse horário");
        }


        Agendamento agendamento = new Agendamento();

        agendamento.setServico(servico);
        agendamento.setProfissional(profissional);
        agendamento.setCliente(cliente);
        agendamento.setDataHoraInicio(inicio);
        agendamento.setDataHoraFim(fim);
        agendamento.setStatus(StatusAgendamento.CRIADO);
        agendamento.setDataInsercao(LocalDateTime.now());

        return agendamentoRepository.save(agendamento);

    }

    private void validarHorarioFuncionamento(LocalDateTime inicio, LocalDateTime fim) {


         if (!inicio.toLocalDate().equals(fim.toLocalDate())) {
            throw new RuntimeException(
                    "O agendamento não pode ultrapassar o dia atual"
                );
        }

        DayOfWeek diaSemana = inicio.getDayOfWeek();

        HorarioFuncionamento horario = horarioFuncionamentoRepository.findByDiaSemana(diaSemana)
        .orElseThrow(() ->
                new RuntimeException("Horário de funcionamento não encontrado para o dia: " + diaSemana));

        LocalTime horaInicio = inicio.toLocalTime();
        LocalTime horaFim = fim.toLocalTime();

        if (horaInicio.isBefore(horario.getHoraAbertura())
                || horaFim.isAfter(horario.getHoraFechamento())) {

            throw new RuntimeException(
                    "Agendamento fora do horário de funcionamento");
        }

    }

    // public void deletarAgendamento(LocalDateTime dataHoraAgendamento, String
    // cliente){
    // agendamentoRepository.deleteByDataHoraAgendamentoAndCliente(dataHoraAgendamento,
    // cliente);
    // }

    // public List<Agendamento> buscarAgendamentosDia(LocalDate data){
    // LocalDateTime primeiraHoraDia = data.atStartOfDay();
    // LocalDateTime horaFinalDia = data.atTime(23, 59, 59);

    // return
    // agendamentoRepository.findByDataHoraAgendamentoBetween(primeiraHoraDia,
    // horaFinalDia);
    // }

    // public Agendamento alterarAgendamento(Agendamento agendamento, String
    // cliente, LocalDateTime dataHoraAgendamento){
    // Agendamento agenda =
    // agendamentoRepository.findByDataHoraAgendamentoAndCliente(dataHoraAgendamento,
    // cliente);

    // if(Objects.isNull(agenda)){
    // throw new RuntimeException("Horário não está preenchido");
    // }

    // agendamento.setId(agenda.getId());
    // return agendamentoRepository.save(agendamento);
    // }
}
