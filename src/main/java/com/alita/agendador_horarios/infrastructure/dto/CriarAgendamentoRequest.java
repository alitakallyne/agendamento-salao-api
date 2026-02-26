package com.alita.agendador_horarios.infrastructure.dto;

import java.time.LocalDateTime;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.validation.constraints.Future;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriarAgendamentoRequest {
    @NotNull
    private Long servicoId;

    @NotNull
    private Long profissionalId;

    @NotNull
    private Long clienteId;
    
    @NotNull
    @Future
    private LocalDateTime dataHoraInicio;
}
