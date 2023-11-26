package com.odontologica.clinicaodontologica.service.impl;

import com.odontologica.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.odontologica.clinicaodontologica.dto.modificacion.TurnoModificacionEntradaDto;
import com.odontologica.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.odontologica.clinicaodontologica.entity.Turno;
import com.odontologica.clinicaodontologica.repository.TurnoRepository;
import com.odontologica.clinicaodontologica.service.ITurnoService;
import com.odontologica.clinicaodontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private final TurnoRepository turnoRepository;
    private final ModelMapper modelMapper;
    private final PacienteService pacienteService;
    private OdontologoService odontologoService;

    public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turno) {
        LOGGER.info("TurnoEntradaDto: " + JsonPrinter.toString(turno));
        Turno turnoEntidad = modelMapper.map(turno, Turno.class);

        //mandamos a persistir a la capa dao y obtenemos una entidad
        Turno TurnoPersistir = turnoRepository.save(turnoEntidad);
        //transformamos la entidad obtenida en salidaDto
        TurnoSalidaDto turnoSalidaDto = modelMapper.map(TurnoPersistir, TurnoSalidaDto.class);
        LOGGER.info("PacienteSalidaDto: " + JsonPrinter.toString(turnoSalidaDto));
        return turnoSalidaDto;
    }

    public List<TurnoSalidaDto> listarTurnos() {
        List <TurnoSalidaDto> turnosSalidaDto = turnoRepository.findAll()
                .stream()
                .map(turno -> modelMapper.map(turno, TurnoSalidaDto.class))
                .toList();

        if (LOGGER.isInfoEnabled())
            LOGGER.info("Listado de todos los turnos: {}", JsonPrinter.toString(turnosSalidaDto));
        return turnosSalidaDto;
    }

    @Override
    public TurnoSalidaDto buscarTurnoPorId(Long id) {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoEncontrado = null;

        if (turnoBuscado != null) {
            turnoEncontrado = modelMapper.map(turnoBuscado, TurnoSalidaDto.class);
            LOGGER.info("Turno encontrado: {}", JsonPrinter.toString(turnoEncontrado));
        } else LOGGER.error("El id no se encuentra registrado en la base de datos.");
        return turnoEncontrado;
    }

    @Override
    public TurnoSalidaDto actualizarTurno(TurnoModificacionEntradaDto turno) {
        Turno turnoRecibido = modelMapper.map(turno, Turno.class);
        Turno turnoAActualizar = turnoRepository.findById(turnoRecibido.getId()).orElse(null);

        TurnoSalidaDto turnoSalidaDto = null;

        if (turnoAActualizar != null) {
            turnoAActualizar = turnoRecibido;
            turnoRepository.save(turnoAActualizar);

            turnoSalidaDto = modelMapper.map(turnoAActualizar, TurnoSalidaDto.class);
            LOGGER.warn("Turno actualizado: {}", JsonPrinter.toString(turnoSalidaDto));
        } else {
            LOGGER.error("No fue posible actualizar el turno porque no se encuentra en nuestra base de datos.");
            //lanzar excepcion correspondiente
        }

        return turnoSalidaDto;
    }

    @Override
    public void eliminarTurno(Long id) {

    }
}
