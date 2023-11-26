package com.odontologica.clinicaodontologica.service;

import com.odontologica.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.odontologica.clinicaodontologica.dto.modificacion.PacienteModificacionEntradaDto;
import com.odontologica.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.odontologica.clinicaodontologica.exception.ResourceNotFoundException;


import java.util.List;

public interface IPacienteService {
    PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente);

    List<PacienteSalidaDto> listarPacientes();

    PacienteSalidaDto buscarPacientePorId(Long id);

    PacienteSalidaDto actualizarPaciente(PacienteModificacionEntradaDto paciente);

    void eliminarPaciente(Long id) throws ResourceNotFoundException;

    PacienteSalidaDto buscarPacientePorDni(int dni);
}