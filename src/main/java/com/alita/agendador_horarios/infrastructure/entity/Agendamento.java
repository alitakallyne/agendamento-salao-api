package com.alita.agendador_horarios.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agendamento")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Servico servico;

    @ManyToOne
    private Profissional profissional;

    private LocalDateTime dataHoraInicio;

    private LocalDateTime dataHoraFim;

    @ManyToOne
    private Cliente cliente;

    private LocalDateTime dataInsercao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusAgendamento status;


}
