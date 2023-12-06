package com.odontologica.clinicaodontologica.service;

import com.odontologica.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.odontologica.clinicaodontologica.dto.modificacion.TurnoModificacionEntradaDto;
import com.odontologica.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.odontologica.clinicaodontologica.exception.BadRequestException;
import com.odontologica.clinicaodontologica.exception.ResourceNotFoundException;

import java.util.List;
public interface ITurnoService {
    TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) throws BadRequestException;

    List<TurnoSalidaDto> listarTurnos();

    TurnoSalidaDto buscarTurnoPorId(Long id);

    void eliminarTurno(Long id) throws ResourceNotFoundException;

    TurnoSalidaDto modificarTurno(TurnoModificacionEntradaDto turnoModificacionEntradaDto) throws ResourceNotFoundException;
}
