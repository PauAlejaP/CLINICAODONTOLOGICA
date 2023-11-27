package com.odontologica.clinicaodontologica.DtoTest;


import com.odontologica.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class PacienteEntradaDtoTest {
    @SpringBootTest
    @ExtendWith(SpringExtension.class)
    @WebAppConfiguration
    public static class PacienteTest {
        @Autowired
        private PacienteEntradaDto pacienteEntradaDto;

        @Test
        public void PacienteTest() {
            PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto();
            pacienteEntradaDto.setDni(Integer.valueOf("454566546"));
            pacienteEntradaDto.setNombre("Emilio");
            pacienteEntradaDto.setApellido("Juarez");

            assertEquals("454566546", pacienteEntradaDto.getDni());
            assertEquals("Emilio", pacienteEntradaDto.getNombre());
            assertEquals("Juarez", pacienteEntradaDto.getApellido());
        }
    }
}
