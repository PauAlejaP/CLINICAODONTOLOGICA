package com.odontologica.clinicaodontologica.service.impl;


import com.odontologica.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.odontologica.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.odontologica.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.odontologica.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.odontologica.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.odontologica.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.odontologica.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;

import com.odontologica.clinicaodontologica.exception.BadRequestException;
import com.odontologica.clinicaodontologica.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
class TurnoServiceTest {


    @Autowired
    private TurnoService turnoService;
    @Autowired
    PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void deberiaVolverUnaListaVaciaDeTurnos() {
        List<TurnoSalidaDto> turnos = turnoService.listarTurnos();

        assertEquals(0, turnos.size());
    }
    @Test
    @Order(2)
    public void registrarTurnoDeberiaAgregarTurno() throws BadRequestException {
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Juan", "Perez", 12345678, LocalDate.of(1990, 1, 1),
                new DomicilioEntradaDto("Calle",  123, "Localidad", "Ciudad"));
        PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("1456a", "Camilo", "Perez");
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);
        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(1L, 1L,LocalDateTime.of(2023, 12, 16, 16, 25, 25));
        TurnoSalidaDto turnoSave = turnoService.registrarTurno(turnoEntradaDto);


        assertNotNull(turnoSave);


            }


    @Test
    @Order(3)
    public void eliminarTurnoDeberiaElimnarTurno() throws BadRequestException, ResourceNotFoundException {
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Juan", "Perez", 12345678, LocalDate.of(1990, 1, 1),
                new DomicilioEntradaDto("Calle",  123, "Localidad", "Ciudad"));
        PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("1456a", "Camilo", "Perez");
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);
        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(1L, 1L,LocalDateTime.of(2023, 12, 16, 16, 25, 25));
        TurnoSalidaDto turnoSave = turnoService.registrarTurno(turnoEntradaDto);

        turnoService.eliminarTurno(turnoSave.getId());
        assertNotNull(turnoSave);


    }
}


