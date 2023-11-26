package com.odontologica.clinicaodontologica.controller;

import com.odontologica.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.odontologica.clinicaodontologica.dto.modificacion.TurnoModificacionEntradaDto;
import com.odontologica.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.odontologica.clinicaodontologica.service.ITurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<TurnoSalidaDto> registrarTurno(@RequestBody @Valid TurnoEntradaDto turno) {
        return new ResponseEntity<>(turnoService.registrarTurno(turno), HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<TurnoSalidaDto> buscarTurnoPorId(@PathVariable Long id) {
        return new ResponseEntity<>(turnoService.buscarTurnoPorId(id), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TurnoSalidaDto>> listarTurnos() {
        return new ResponseEntity<>(turnoService.listarTurnos(), HttpStatus.OK);
    }
    @PutMapping("/actualizar")
    public ResponseEntity<TurnoSalidaDto> actualizarTurno(@RequestBody TurnoModificacionEntradaDto turno) {
        return new ResponseEntity<>(turnoService.actualizarTurno(turno), HttpStatus.OK);
    }
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id) {
        turnoService.eliminarTurno(id);
        return new ResponseEntity<>("Turno eliminado correctamente", HttpStatus.OK);
    }
}

