package com.alita.agendador_horarios.controller;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alita.agendador_horarios.infrastructure.dto.AgendamentoResponse;
import com.alita.agendador_horarios.infrastructure.dto.CriarAgendamentoRequest;
import com.alita.agendador_horarios.infrastructure.entity.Agendamento;
import com.alita.agendador_horarios.infrastructure.entity.StatusAgendamento;
import com.alita.agendador_horarios.services.AgendamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<AgendamentoResponse> salvarAgendamento(@RequestBody @Valid CriarAgendamentoRequest request) {
        return ResponseEntity.accepted().body(AgendamentoResponse.fromEntity(
                agendamentoService.salvarAgendamento(request)));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarAgendamento(
            @PathVariable Long idAgendamento) {

        agendamentoService.deletarAgendamento(idAgendamento);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("buscar-agendamentos-dia")
    public ResponseEntity<List<Agendamento>> buscarAgendamentosDia(@RequestParam LocalDate data) {
        return ResponseEntity.ok().body(agendamentoService.buscarAgendamentosDia(data));
    }

    @GetMapping("buscar-agendamentosProf-dia")
    public ResponseEntity<List<AgendamentoResponse>> buscarAgendamentoProfissionalDia(
            @RequestParam Long profissionalId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data

    ) {

        List<Agendamento> agendamentos = agendamentoService.listarPorProfissionalEDia(profissionalId, data);

        return ResponseEntity.ok(
                agendamentos.stream()
                        .map(AgendamentoResponse::fromEntity)
                        .toList());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusAgendamento status) {

        agendamentoService.atualizarStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/horario")
    public ResponseEntity<Agendamento> atualizarHorario(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime  data) {

        Agendamento atualizado = agendamentoService.atualizarHorario(id, data);
        return ResponseEntity.ok(atualizado);
    }
}