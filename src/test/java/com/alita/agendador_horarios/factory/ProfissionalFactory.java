package com.alita.agendador_horarios.factory;

import com.alita.agendador_horarios.infrastructure.entity.Profissional;

public class ProfissionalFactory {
public static Profissional profissionalAtivo() {
        Profissional profissional = new Profissional();
        profissional.setNome("João da Silva");
        profissional.setEspecialidade("Corte Masculino");
        profissional.setTelefone("85988888888");
        profissional.setEmail("joao@email.com");
        profissional.setCpf("98765432100");
        profissional.setAtivo(true);
        return profissional;
    }
}
