package com.odontologica.clinicaodontologica.DtoTest;

import com.odontologica.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OdontologoEntradaDtoTest {
    @SpringBootTest
    @ExtendWith(SpringExtension.class)
    @WebAppConfiguration
    public class OdontologoTest {
        @Autowired
        private OdontologoEntradaDto odontologoEntradaDto;

        @Test
        public void OdontologoTest(){
            OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto();
            odontologoEntradaDto.setMatricula("123");
            odontologoEntradaDto.setNombre("Juan");
            odontologoEntradaDto.setApellido("Perez");

            assertEquals("123", odontologoEntradaDto.getMatricula());
            assertEquals("Juan", odontologoEntradaDto.getNombre());
            assertEquals("Perez", odontologoEntradaDto.getApellido());
        }

    }
}
