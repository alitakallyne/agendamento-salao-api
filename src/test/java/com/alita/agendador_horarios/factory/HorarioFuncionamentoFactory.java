package com.alita.agendador_horarios.factory;

import java.time.DayOfWeek;
import java.time.LocalTime;

import com.alita.agendador_horarios.infrastructure.entity.HorarioFuncionamento;

public class HorarioFuncionamentoFactory {

    public static HorarioFuncionamento horario(
            DayOfWeek dia,
            LocalTime abertura,
            LocalTime fechamento
    ) {
        HorarioFuncionamento hf = new HorarioFuncionamento();
        hf.setDiaSemana(dia);
        hf.setHoraAbertura(abertura);
        hf.setHoraFechamento(fechamento);
        return hf;
    }

    public static HorarioFuncionamento horarioPadrao(DayOfWeek dia) {
        return horario(
            dia,
            LocalTime.of(8, 0),
            LocalTime.of(18, 0)
        );
    }
}
