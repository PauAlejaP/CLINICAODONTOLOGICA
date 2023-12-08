package com.odontologica.clinicaodontologica.dto.salida.turno;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.odontologica.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.odontologica.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.odontologica.clinicaodontologica.entity.Odontologo;
import com.odontologica.clinicaodontologica.entity.Paciente;
public class TurnoSalidaDto {
    private Long id;
    private PacienteSalidaDto pacienteSalidaDto;
    private OdontologoSalidaDto odontologoSalidaDto;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime fechaYHora;

    public TurnoSalidaDto() {
    }

    public TurnoSalidaDto(Long id, PacienteSalidaDto pacienteSalidaDto, OdontologoSalidaDto odontologoSalidaDto, LocalDateTime fechaYHora) {
        this.id = id;
        this.pacienteSalidaDto = pacienteSalidaDto;
        this.odontologoSalidaDto = odontologoSalidaDto;
        this.fechaYHora = fechaYHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PacienteSalidaDto getPacienteSalidaDto() {
        return pacienteSalidaDto;
    }

    public void setPacienteSalidaDto(PacienteSalidaDto pacienteSalidaDto) {
        this.pacienteSalidaDto = pacienteSalidaDto;
    }

    public OdontologoSalidaDto getOdontologoSalidaDto() {
        return odontologoSalidaDto;
    }

    public void setOdontologoSalidaDto(OdontologoSalidaDto odontologoSalidaDto) {
        this.odontologoSalidaDto = odontologoSalidaDto;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }


    @Override
    public String toString() {
        return "Id: " + id + " - Paciente: " + pacienteSalidaDto + " - Odontologo: " + odontologoSalidaDto + " - Fecha y hora: " + fechaYHora;
    }


}
