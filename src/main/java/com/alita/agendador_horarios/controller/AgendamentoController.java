package com.alita.agendador_horarios.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alita.agendador_horarios.infrastructure.dto.AgendamentoResponse;
import com.alita.agendador_horarios.infrastructure.dto.CriarAgendamentoRequest;
import com.alita.agendador_horarios.infrastructure.entity.Agendamento;
import com.alita.agendador_horarios.services.AgendamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<AgendamentoResponse> salvarAgendamento(@RequestBody  @Valid CriarAgendamentoRequest request) {
        return ResponseEntity.accepted().body(AgendamentoResponse.fromEntity(
            agendamentoService.salvarAgendamento(request))
        );
    }

    // @DeleteMapping
    // public ResponseEntity<Void> deletarAgendamento(@RequestParam String cliente,
    //                                                @RequestParam LocalDateTime dataHoraAgendamento) {

    //     agendamentoService.deletarAgendamento(dataHoraAgendamento, cliente);
    //     return ResponseEntity.noContent().build();
    // }

    // @GetMapping
    // public ResponseEntity<List<Agendamento>> buscarAgendamentosDia(@RequestParam LocalDate data) {
    //     return ResponseEntity.ok().body(agendamentoService.buscarAgendamentosDia(data));
    // }

    // @PutMapping
    // public ResponseEntity<Agendamento> alterarAgendamentos(@RequestBody Agendamento agendamento,
    //                                                        @RequestParam String cliente,
    //                                                        @RequestParam LocalDateTime dataHoraAgendamento) {
    //     return ResponseEntity.accepted().body(agendamentoService.alterarAgendamento(agendamento,
    //             cliente, dataHoraAgendamento));
    // }
}