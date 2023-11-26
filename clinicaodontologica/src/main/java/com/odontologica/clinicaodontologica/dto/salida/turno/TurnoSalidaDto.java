package com.odontologica.clinicaodontologica.dto.salida.turno;

import java.time.LocalDateTime;
import com.odontologica.clinicaodontologica.entity.Odontologo;
import com.odontologica.clinicaodontologica.entity.Paciente;
public class TurnoSalidaDto {
    private Long id;
    private LocalDateTime fechaYHora;
    private Odontologo odontologo;
    private Paciente paciente;

    public TurnoSalidaDto() {

    }

    public TurnoSalidaDto(Long id, LocalDateTime fechaYHora, Odontologo odontologo, Paciente paciente) {
        this.id = id;
        this.fechaYHora = fechaYHora;
        this.odontologo = odontologo;
        this.paciente = paciente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
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

    public void setPacienteSalidaDto(Object o) {
    }
}
