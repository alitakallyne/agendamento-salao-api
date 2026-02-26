package com.alita.agendador_horarios.infrastructure.dto;

import java.time.LocalDateTime;

import com.alita.agendador_horarios.infrastructure.entity.Agendamento;
import com.alita.agendador_horarios.infrastructure.entity.StatusAgendamento;


import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class AgendamentoResponse {

    private Long id;
    private String servico;
    private String profissional;
    private String cliente;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private StatusAgendamento status;

    public static AgendamentoResponse fromEntity(Agendamento agendamento) {
            return AgendamentoResponse.builder()
                .id(agendamento.getId())
                .servico(agendamento.getServico().getNome())
                .profissional(agendamento.getProfissional().getNome())
                .cliente(agendamento.getCliente().getNome())
                .inicio(agendamento.getDataHoraInicio())
                .fim(agendamento.getDataHoraFim())
                .status(agendamento.getStatus())
                .build();
    }
}
