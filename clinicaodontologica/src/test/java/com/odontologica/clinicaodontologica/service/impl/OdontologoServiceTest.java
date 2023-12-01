package com.odontologica.clinicaodontologica.service.impl;

import com.odontologica.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.odontologica.clinicaodontologica.dto.modificacion.OdontologoModificacionEntradaDto;
import com.odontologica.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.odontologica.clinicaodontologica.exception.ResourceNotFoundException;
import com.odontologica.clinicaodontologica.repository.OdontologoRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
    public void guardarOdontologo_deberiaGuardarOdontologo() {


        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto();
        odontologoEntradaDto.setMatricula("325");
        odontologoEntradaDto.setNombre("Adolfo");
        odontologoEntradaDto.setApellido("Guemes");

        OdontologoSalidaDto odontologoGuardado = odontologoService.registrarOdontologo(odontologoEntradaDto);

        assertNotNull(odontologoGuardado);
        assertEquals(1, odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    public void modificarOdontologo_deberiaModificarOdontologoExistente() throws ResourceNotFoundException {

        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("1234", "Juan", "Perez");
        OdontologoSalidaDto odontologoGuardado = odontologoService.registrarOdontologo(odontologoEntradaDto);

        OdontologoModificacionEntradaDto nuevoOdontologo1 = new OdontologoModificacionEntradaDto(1L, "5678", "Carlos", "Gomez");


        OdontologoSalidaDto odontologoModificado = odontologoService.actualizarOdontologo(nuevoOdontologo1);


        assertEquals(nuevoOdontologo1.getMatricula(), odontologoModificado.getMatricula());
        assertEquals(nuevoOdontologo1.getNombre(), odontologoModificado.getNombre());
        assertEquals(nuevoOdontologo1.getApellido(), odontologoModificado.getApellido());
    }

    @Test
    @Order(3)
    public void listarTodosLosOdontologos_deberiaListarOdontologos() {


        OdontologoEntradaDto odontologo1 = new OdontologoEntradaDto();
        odontologo1.setMatricula("123");
        odontologo1.setNombre("John");
        odontologo1.setApellido("Doe");
        odontologoService.registrarOdontologo(odontologo1);

        OdontologoEntradaDto odontologo2 = new OdontologoEntradaDto();
        odontologo2.setMatricula("456");
        odontologo2.setNombre("Jane");
        odontologo2.setApellido("Smith");
        odontologoService.registrarOdontologo(odontologo2);


        List<OdontologoSalidaDto> odontologos = odontologoService.listarOdontologos();


        assertNotNull(odontologos);
        assertEquals(2, odontologos.size()); // Assuming two odontologos were registered


        assertEquals("123", odontologos.get(0).getMatricula());
        assertEquals("John", odontologos.get(0).getNombre());
        assertEquals("Doe", odontologos.get(0).getApellido());
    }
}


