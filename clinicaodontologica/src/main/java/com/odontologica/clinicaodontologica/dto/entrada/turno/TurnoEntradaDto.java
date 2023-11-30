package com.odontologica.clinicaodontologica.dto.entrada.turno;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.odontologica.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.odontologica.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.odontologica.clinicaodontologica.entity.Odontologo;
import com.odontologica.clinicaodontologica.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoEntradaDto {


    @NotNull(message = "El campo fecha no puede ser nulo")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    private LocalDateTime fechaYHora;

    @NotNull(message = "El odontologo no puede ser nulo")
    @Valid
    private Long odontologo;

    @NotNull(message = "El paciente no puede ser nulo")
    @Valid
    private Long paciente;

    public TurnoEntradaDto(LocalDateTime localDateTime, OdontologoEntradaDto odontologoEntradaDto, PacienteEntradaDto pacienteEntradaDto) {

    }

    public TurnoEntradaDto() {

    }

    public TurnoEntradaDto( LocalDateTime fechaYHora, Long odontologo, Long paciente) {
        this.fechaYHora = fechaYHora;
        this.odontologo = odontologo;
        this.paciente = paciente;
    }



    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public Long getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Long odontologo) {
        this.odontologo = odontologo;
    }

    public Long getPaciente() {
        return paciente;
    }

    public void setPaciente(Long paciente) {
        this.paciente = paciente;
    }

    public Object getPacienteEntradaDto() {
        return null;
    }
}



