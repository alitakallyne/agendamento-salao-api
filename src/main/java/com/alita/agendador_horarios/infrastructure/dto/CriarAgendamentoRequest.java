package com.alita.agendador_horarios.infrastructure.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriarAgendamentoRequest {

     private Long servicoId;
    private Long profissionalId;
    private Long clienteId;

    private LocalDateTime dataHoraInicio;
}
