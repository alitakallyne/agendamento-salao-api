package com.alita.agendador_horarios.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alita.agendador_horarios.infrastructure.entity.Profissional;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long>  {

}
