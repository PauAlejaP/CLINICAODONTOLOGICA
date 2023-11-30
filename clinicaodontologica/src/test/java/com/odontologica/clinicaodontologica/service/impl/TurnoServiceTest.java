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