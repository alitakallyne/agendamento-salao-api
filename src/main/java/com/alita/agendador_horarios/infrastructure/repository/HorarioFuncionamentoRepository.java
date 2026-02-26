package com.alita.agendador_horarios.infrastructure.repository;

import java.time.DayOfWeek;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alita.agendador_horarios.infrastructure.entity.HorarioFuncionamento;

public interface HorarioFuncionamentoRepository extends JpaRepository<HorarioFuncionamento, Long> {

    Optional<HorarioFuncionamento> findByDiaSemana(DayOfWeek diaSemana);
    
}
