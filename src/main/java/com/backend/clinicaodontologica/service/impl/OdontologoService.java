package com.backend.clinicaodontologica.service.impl;


import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.OdontologoModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.PacienteModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.entity.Paciente;
import com.backend.clinicaodontologica.repository.OdontologoRepository;
import com.backend.clinicaodontologica.service.IOdontologoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.clinicaodontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;

import java.util.List;


@Service
public class OdontologoService implements IOdontologoService {
    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private OdontologoRepository odontologoRepository;

    private ModelMapper modelMapper;
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    public OdontologoSalidaDto registrarPaciente(OdontologoEntradaDto odontologo) {
        //convertimos mediante el mapper de dtoEntrada a entidad
        LOGGER.info("OdontologoEntradaDto: " + JsonPrinter.toString(odontologo));
        Odontologo odonlogoEntidad = modelMapper.map(odontologo, Odontologo.class);

        //mandamos a persistir a la capa dao y obtenemos una entidad
        Odontologo odontologoAPersistir = odontologoRepository.save(odonlogoEntidad);
        //transformamos la entidad obtenida en salidaDto
        OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odontologoAPersistir, OdontologoSalidaDto.class);
        LOGGER.info("OdontologoSalidaDto: " + JsonPrinter.toString(odontologoSalidaDto));
        return odontologoSalidaDto;
    }

    public List<OdontologoSalidaDto> listarOdontologos() {
        List<OdontologoSalidaDto> odontologosSalidaDto = odontologoRepository.findAll()
                .stream()
                .map(odontologo -> modelMapper.map(odontologo, OdontologoSalidaDto.class))
                .toList();


        if (LOGGER.isInfoEnabled())
            LOGGER.info("Listado de todos los odontologos: {}", JsonPrinter.toString(odontologosSalidaDto));
        return odontologosSalidaDto;
    }

    @Override
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo) {
        return null;
    }

    @Override
    public OdontologoSalidaDto buscarOdontologoPorId(Long id) {
        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);
        OdontologoSalidaDto odontologoEncontrado = null;

        if (odontologoBuscado != null) {
            odontologoEncontrado = modelMapper.map(odontologoBuscado, OdontologoSalidaDto.class);
            LOGGER.info("Odontologo encontrado: {}", JsonPrinter.toString(odontologoEncontrado));
        } else LOGGER.error("El id no se encuentra registrado en la base de datos");

        return odontologoEncontrado;
    }

    @Override
    public OdontologoSalidaDto actualizarOdontologo(OdontologoModificacionEntradaDto odontologo) {
        Odontologo odontologoRecibido = modelMapper.map(odontologo, Odontologo.class);
        Odontologo odontologoAActulizar = odontologoRepository.findById(odontologoRecibido.getId()).orElse(null);

        OdontologoSalidaDto odontologoSalidaDto = null;

        if (odontologoAActulizar != null) {
            odontologoAActulizar = odontologoRecibido;
            odontologoRepository.save(odontologoAActulizar);

            odontologoSalidaDto = modelMapper.map(odontologoAActulizar, OdontologoSalidaDto.class);
            LOGGER.warn("Odontologo actualizado: {}", JsonPrinter.toString(odontologoSalidaDto));

        } else {
            LOGGER.error("No fue posible actualizar el odontologo porque no se encuentra en nuestra base de datos");
            //lanzar excepcion correspondiente
        }


        return odontologoSalidaDto;
    }

    @Override
    public void eliminarOdontologo(Long id) {
        if (odontologoRepository.findById(id).orElse(null) != null) {
            odontologoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el odontologo con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el odontologo con id {}", id);

        }

    }

    private void configureMapping() {
        modelMapper.typeMap(OdontologoEntradaDto.class, Odontologo.class)
                .addMappings(mapper -> mapper.map(OdontologoEntradaDto::getMatricula, Odontologo::setMatricula));
        modelMapper.typeMap(Odontologo.class, OdontologoSalidaDto.class)
                .addMappings(modelMapper -> modelMapper.map(Odontologo::getMatricula, OdontologoSalidaDto::setMatricula));
        modelMapper.typeMap(OdontologoModificacionEntradaDto.class, Odontologo.class)
                .addMappings(mapper -> mapper.map(OdontologoModificacionEntradaDto::getMatricula, Odontologo::setMatricula));

    }

}
