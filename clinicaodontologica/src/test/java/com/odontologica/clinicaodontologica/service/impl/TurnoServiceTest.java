package com.odontologica.clinicaodontologica.service.impl;

import com.odontologica.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.odontologica.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.odontologica.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.odontologica.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.odontologica.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.odontologica.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.odontologica.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.odontologica.clinicaodontologica.entity.Turno;
import com.odontologica.clinicaodontologica.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.odontologica.clinicaodontologica.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import com.odontologica.clinicaodontologica.utils.LocalDateTimeAdapter;

@SpringBootTest
class TurnoServiceTest {


    @Autowired
    private TurnoService turnoService;
    @Autowired PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Test
    @Order(1)
    public void registrarTurno_deberiaAgregarTurno() throws BadRequestException {


        OdontologoEntradaDto odontologo1 = new OdontologoEntradaDto();
        odontologo1.setMatricula("123");
        odontologo1.setNombre("John");
        odontologo1.setApellido("Doe");
        OdontologoSalidaDto odontologoGuardado = odontologoService.registrarOdontologo(odontologo1);

        PacienteEntradaDto paciente1 = new PacienteEntradaDto();
        paciente1.setNombre("Alice");
        paciente1.setApellido("Smith");
        PacienteSalidaDto pacienteGuardado = pacienteService.registrarPaciente(paciente1);


        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto();
        turnoEntradaDto.setPacienteId(pacienteGuardado.getId());
        turnoEntradaDto.setOdontologoId(odontologoGuardado.getId());
        LocalDateTime localDateTime = LocalDateTime.now();
        String fechayHoraString = localDateTime.toString();
        turnoEntradaDto.setFechaYHora(LocalDateTime.parse(fechayHoraString));
        turnoEntradaDto.setOdontologoId(odontologoGuardado.getId());
        turnoEntradaDto.setPacienteId(pacienteGuardado.getId());

        TurnoSalidaDto turnoSalidaDto = turnoService.registrarTurno(turnoEntradaDto);


        assertNotNull(turnoSalidaDto);
        assertNotNull(turnoSalidaDto.getId());


        List<TurnoSalidaDto> turns = turnoService.listarTurnos();
        assertTrue(turns.contains(turnoSalidaDto));
    }

    @Test
    public void listarTurnos_deberiaListarTurnos() throws BadRequestException {


        TurnoEntradaDto turno1 = new TurnoEntradaDto();
        turno1.setFechaYHora(LocalDateTime.parse("2023-12-01 10:00:00"));
        turno1.setOdontologoId(1L);
        turno1.setPacienteId(1L);
        turnoService.registrarTurno(turno1);

        TurnoEntradaDto turno2 = new TurnoEntradaDto();
        turno2.setFechaYHora(LocalDateTime.parse("2023-12-02 14:30:00"));
        turno2.setOdontologoId(2L);
        turno2.setPacienteId(2L);
        turnoService.registrarTurno(turno2);


        List<TurnoSalidaDto> turnos = turnoService.listarTurnos();

        // Assert
        assertNotNull(turnos);
        assertEquals(2, turnos.size()); // Assuming two turns were registered


        assertEquals("2023-12-01 10:00:00", turnos.get(0).getFechaYHora());
        assertEquals(1L, turnos.get(0).getOdontologoSalidaDto().getId());
        assertEquals(1L, turnos.get(0).getPacienteSalidaDto().getId());
    }

    @Test
    public void buscarTurnoPorId_deberiaEncontrarTurno() throws BadRequestException {

        // Register a turn
        TurnoEntradaDto turno1 = new TurnoEntradaDto();
        turno1.setFechaYHora(LocalDateTime.parse("2023-12-0110 00:00"));
        turno1.setOdontologoId(1L);
        turno1.setPacienteId(1L);
        TurnoSalidaDto turnoGuardado = turnoService.registrarTurno(turno1);


        TurnoSalidaDto turnoEncontrado = turnoService.buscarTurnoPorId(turnoGuardado.getId());


        assertNotNull(turnoEncontrado);
        assertEquals(turnoGuardado.getId(), turnoEncontrado.getId());
        assertEquals(turnoGuardado.getFechaYHora(), turnoEncontrado.getFechaYHora());
        assertEquals(turnoGuardado.getOdontologoSalidaDto().getId(), turnoEncontrado.getOdontologoSalidaDto().getId());
        assertEquals(turnoGuardado.getPacienteSalidaDto().getId(), turnoEncontrado.getPacienteSalidaDto().getId());
    }
}