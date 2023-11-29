package com.odontologica.clinicaodontologica.dto.modificacion;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.odontologica.clinicaodontologica.entity.Odontologo;
import com.odontologica.clinicaodontologica.entity.Paciente;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;


@JsonIgnoreProperties(ignoreUnknown = true)

public class TurnoModificacionEntradaDto {

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

    public TurnoModificacionEntradaDto() {

    }

    public TurnoModificacionEntradaDto(Long id, LocalDateTime fechayHora, Odontologo odontologo, Paciente paciente) {

        this.fechayHora = fechayHora;
        this.odontologo = odontologo;
        this.paciente = paciente;
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

    public TurnoModificacionEntradaDto getOdontologoEntradaDto() {
        return null;
    }
}
