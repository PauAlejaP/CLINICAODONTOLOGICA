package com.odontologica.clinicaodontologica.service.impl;

import com.odontologica.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.odontologica.clinicaodontologica.dto.modificacion.TurnoModificacionEntradaDto;
import com.odontologica.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.odontologica.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.odontologica.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.odontologica.clinicaodontologica.entity.Turno;
import com.odontologica.clinicaodontologica.exception.BadRequestException;
import com.odontologica.clinicaodontologica.exception.GlobalExceptionHandler;
import com.odontologica.clinicaodontologica.exception.ResourceNotFoundException;
import com.odontologica.clinicaodontologica.repository.TurnoRepository;
import com.odontologica.clinicaodontologica.service.ITurnoService;
import com.odontologica.clinicaodontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class  TurnoService implements ITurnoService {

    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private final TurnoRepository turnoRepository;
    private final ModelMapper modelMapper;
    private final PacienteService pacienteService;
    private OdontologoService odontologoService;


    @Autowired
    public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;

    }


    @Override
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turno) throws BadRequestException {

        OdontologoSalidaDto odontologo = odontologoService.buscarOdontologoPorId(turno.getOdontologoId());
        PacienteSalidaDto paciente = pacienteService.buscarPacientePorId(turno.getPacienteId());

        if (odontologo == null && paciente == null) {
            throw new BadRequestException("No se encuentra ni un odontólogo ni un paciente con los ID proporcionados.");
        } else if (odontologo == null) {
            throw new BadRequestException("No se encuentra un odontólogo con el ID proporcionado.");
        } else if (paciente == null) {
            throw new BadRequestException("No se encuentra un paciente con el ID proporcionado.");
        }

        Turno turnoEntidad = modelMapper.map(turno, Turno.class);
        LOGGER.info("Entidad: " + JsonPrinter.toString(turnoEntidad));


        Turno turnoAPersistir = turnoRepository.save(turnoEntidad);
        LOGGER.info("Turno a persistir: " + JsonPrinter.toString(turnoAPersistir));

        TurnoSalidaDto turnoSalidaDto = modelMapper.map(turnoAPersistir, TurnoSalidaDto.class);
        LOGGER.info("TurnoSalidaDto: " + JsonPrinter.toString(turnoSalidaDto));


        turnoSalidaDto.setId(turno.getOdontologoId());
        turnoSalidaDto.setId(turno.getPacienteId());

        return turnoSalidaDto;
    }

    private PacienteSalidaDto pacienteSalidaDtoASalidaTurnoDto(Long id) {
        return modelMapper.map(pacienteService.buscarPacientePorId(id), PacienteSalidaDto.class);
    }

    private OdontologoSalidaDto odontologoSalidaDtoASalidaTurnoDto(Long id) {
        return modelMapper.map(odontologoService.buscarOdontologoPorId(id), OdontologoSalidaDto.class);
    }

    private TurnoSalidaDto entidadADto(Turno turno) {
        TurnoSalidaDto turnoSalidaDto = modelMapper.map(turno, TurnoSalidaDto.class);
        turnoSalidaDto.setPacienteSalidaDto(pacienteSalidaDtoASalidaTurnoDto(turno.getPaciente().getId()));
        turnoSalidaDto.setOdontologoSalidaDto(odontologoSalidaDtoASalidaTurnoDto(turno.getOdontologo().getId()));
        return turnoSalidaDto;
    }

    public List<TurnoSalidaDto> listarTurnos() {
        List<Turno> turnos = turnoRepository.findAll();
        return turnos.stream().map(this::entidadADto).collect(Collectors.toList());
    }

    @Override
    public TurnoSalidaDto buscarTurnoPorId(Long id) {
        Optional<Turno> turnoOptional = turnoRepository.findById(id);

        if (turnoOptional.isPresent()) {
            Turno turno = turnoOptional.get();
            return entidadADto(turno);
        } else {
            System.out.println("No se encuentra el id");
            ;
        }
        return null;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        Optional<Turno> turnoOptional;
        turnoOptional = turnoRepository.findById(id);

        if (turnoOptional.isPresent()) {
            turnoRepository.delete(turnoOptional.get());
            LOGGER.info("Turno eliminado correctamente. ID: {}", id);
        } else {
            throw new ResourceNotFoundException("No se encuentra el turno con el id: " + id);
        }
    }

    @Override
    public TurnoSalidaDto modificarTurno(TurnoModificacionEntradaDto turnoModificacionEntradaDto) throws ResourceNotFoundException {
        Long turnoId = turnoModificacionEntradaDto.getId();
        Optional<Turno> turnoOptional = turnoRepository.findById(turnoId);

        if (turnoOptional.isPresent()) {
            Turno turnoExistente = turnoOptional.get();


            turnoRepository.save(turnoExistente);

            LOGGER.info("Turno modificado correctamente. ID: {}", turnoId);
        } else {
            throw new ResourceNotFoundException("No se encuentra el turno con el id: " + turnoId);
        }
        return null;
    }
}