package com.odontologica.clinicaodontologica.service.impl;

import com.odontologica.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.odontologica.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.odontologica.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.odontologica.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.odontologica.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.odontologica.clinicaodontologica.exception.ResourceNotFoundException;
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

    @Test
    @Order(1)
    void deberiaRegistrarUnTurnoDeVolverNombrePaciente(){
        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(LocalDateTime.of(2023, 9, 9, 9, 9),
                new OdontologoEntradaDto("1L", "Juan", "Perez"),
                new PacienteEntradaDto("Paula", "Palacios", 654564654, LocalDate.of(2023, 9, 9),
        new DomicilioEntradaDto("calle", 1234, "Localidad", "Provincia")));

        TurnoSalidaDto turnoSalidaDto = turnoService.registrarTurno(turnoEntradaDto);
        assertNotNull(turnoSalidaDto.getPaciente());
        assertEquals("Paula", turnoSalidaDto.getPaciente().getNombre());
    }
    @Test
    @Order(2)
    void alIntentarEliminarUnTurnoYaEliminado_deberiaLanzarUnaResourceNotFoundException(){

        try{
            turnoService.eliminarTurno(1L);
        } catch (Exception exception){
            exception.printStackTrace();
        }

        assertThrows(ResourceNotFoundException.class, () -> turnoService.eliminarTurno(1L));
    }

    @Test
    @Order(3)
    void deberiaDevolverUnaListaVaciaDeturno(){

        List<TurnoSalidaDto> turnoDto = turnoService.listarTurnos();

        assertTrue(turnoDto.isEmpty());

    }



}