package com.odontologica.clinicaodontologica.IntegradorTest;

import com.odontologica.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest

public class OdontologoEntradaTestIntegrado {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void guardarOdontologo() throws Exception {
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto();
        odontologoEntradaDto.setMatricula("123");
        odontologoEntradaDto.setNombre("Juan");
        odontologoEntradaDto.setApellido("Perez");

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/odontologos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(odontologoEntradaDto.toString()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        assertEquals(odontologoEntradaDto.getMatricula(), responseBody);
        assertNotNull(odontologoEntradaDto.getApellido());
    }
    @Test
    public void actualizarOdontologo() throws Exception {
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto();
        odontologoEntradaDto.setMatricula("645");
        odontologoEntradaDto.setNombre("Ernesto");
        odontologoEntradaDto.setApellido("Diaz");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/odontologos/actualizar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(odontologoEntradaDto.toString()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        assertEquals(odontologoEntradaDto.getMatricula(), responseBody);

        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/{id}")
                .param("id", odontologoEntradaDto.getMatricula()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        responseBody = mvcResult.getResponse().getContentAsString();

        assertEquals(odontologoEntradaDto.getMatricula(), responseBody);

    }
    @Test

    public void getAll() throws Exception {
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto();
        odontologoEntradaDto.setMatricula("654645");
        odontologoEntradaDto.setNombre("Ernesto");
        odontologoEntradaDto.setApellido("Gutierrez");

        OdontologoEntradaDto odontologoEntradaDto1 = new OdontologoEntradaDto();
        odontologoEntradaDto1.setMatricula("644645");
        odontologoEntradaDto1.setNombre("Ernstina");
        odontologoEntradaDto1.setApellido("Hernandez");

        List<OdontologoEntradaDto> odontologoEntradaDtoList = new ArrayList<>();
        odontologoEntradaDtoList.add(odontologoEntradaDto);
        odontologoEntradaDtoList.add(odontologoEntradaDto1);


        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/dentista/listar")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(odontologoEntradaDto.toString()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        assertEquals(odontologoEntradaDto.getMatricula(), responseBody);
        assertEquals(odontologoEntradaDto.getApellido(), responseBody);

        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/dentista/create")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(odontologoEntradaDto.toString()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        String responseBody1 = mvcResult.getResponse().getContentAsString();

        assertEquals(odontologoEntradaDto.getMatricula(), responseBody1);
        assertEquals(odontologoEntradaDto.getApellido(), responseBody1);

    }

    @Test
        public void deleteOdontologo() throws Exception {
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto();
        odontologoEntradaDto.setMatricula("654654654");
        odontologoEntradaDto.setNombre("Ramiro");
        odontologoEntradaDto.setApellido("Rodriguez");


        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/dentista/registrar")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(odontologoEntradaDto.toString()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

       assertEquals(odontologoEntradaDto.getMatricula(), responseBody);

        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/dentista/{id}", odontologoEntradaDto.getMatricula())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).
                        content(odontologoEntradaDto.toString()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        String responseBody1 = mvcResult.getResponse().getContentAsString();
        assertEquals(responseBody1, "Odontologo " + odontologoEntradaDto.getMatricula() + " eliminado");

    }


}


