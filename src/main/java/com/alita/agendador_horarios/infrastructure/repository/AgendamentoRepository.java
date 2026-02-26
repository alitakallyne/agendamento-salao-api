package com.alita.agendador_horarios.infrastructure.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alita.agendador_horarios.infrastructure.entity.Agendamento;
import com.alita.agendador_horarios.infrastructure.entity.Profissional;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    // Método para encontrar agendamentos de um profissional em um intervalo de datas
    List<Agendamento> findByProfissionalIdAndDataAgendamentoBetween( Profissional profissional, LocalDateTime start, LocalDateTime end);

    // Método para verificar se existe um agendamento que se sobrepõe a um intervalo específico para um profissional
     boolean existsByProfissionalAndDataHoraInicioLessThanAndDataHoraFimGreaterThan(
            Profissional profissional,
            LocalDateTime fim,
            LocalDateTime inicio
    );

    // Método para encontrar agendamentos que se sobrepõem a um intervalo específico
      List<Agendamento> findByDataHoraInicioBetween(
            LocalDateTime inicio,
            LocalDateTime fim
    );
}
