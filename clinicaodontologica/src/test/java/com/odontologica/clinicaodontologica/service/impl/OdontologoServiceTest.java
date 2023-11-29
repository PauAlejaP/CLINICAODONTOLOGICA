package com.odontologica.clinicaodontologica.service.impl;

import com.odontologica.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.odontologica.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.odontologica.clinicaodontologica.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OdontologoServiceTest {


    @Autowired
    private OdontologoService odontologoService;


    @Test
    @Order(1)
    void deberiaRegistrarUnOdontologoDeNombreJuanYRetornarElApellido() {
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("2134", "Juan", "Perez");

        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);

        assertNotNull(odontologoEntradaDto.getApellido());
        assertEquals("Perez", odontologoSalidaDto.getApellido());
    }


    @Test
    @Order(2)
    void alIntentarEliminarUnOdontologoConId1YaEliminado_deberiaLanzarUnaResourceNotFoundException() {

        try {
            odontologoService.eliminarOdontologo(1L);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        assertThrows(ResourceNotFoundException.class, () -> odontologoService.eliminarOdontologo(1L));
    }

    @Test
    @Order(3)
    void deberiaDevolverUnaListaVaciaDeOdontologos() {

        List<OdontologoSalidaDto> odontologoDto = odontologoService.listarOdontologos();

        assertTrue(odontologoDto.isEmpty());

    }


}