package com.alita.agendador_horarios.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alita.agendador_horarios.infrastructure.entity.HorarioFuncionamento;

public interface HorarioFuncionamentoRepository extends JpaRepository<HorarioFuncionamento, Long> {

}
