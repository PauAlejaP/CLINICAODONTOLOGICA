package com.backend.clinicaodontologica.dto.entrada.turno;

import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TurnoEntradaDto {

    @NotNull(message = "El campo fecha no puede ser nulo")
    private Long id;

    @NotNull(message = "El campo fecha no puede ser nulo")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    private LocalDateTime fechayHora;

    @NotNull(message = "El odontologo no puede ser nulo")
    @Valid
    private Odontologo odontologo;

    @NotNull(message = "El paciente no puede ser nulo")
    @Valid
    private Paciente paciente;

    public TurnoEntradaDto() {

    }

    public TurnoEntradaDto(Long id, LocalDateTime fechayHora, Odontologo odontologo, Paciente paciente) {
        this.id = id;
        this.fechayHora = fechayHora;
        this.odontologo = odontologo;
        this.paciente = paciente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechayHora() {
        return fechayHora;
    }

    public void setFechayHora(LocalDateTime fechayHora) {
        this.fechayHora = fechayHora;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Object getPacienteEntradaDto() {
    return null;
    }
}
