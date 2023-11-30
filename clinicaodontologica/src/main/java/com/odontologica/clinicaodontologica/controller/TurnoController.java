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
@CrossOrigin
public class TurnoController {
    private ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }


    @PostMapping("/registrar")
    public ResponseEntity<TurnoSalidaDto> registrarTurno(@Valid @RequestBody TurnoEntradaDto turno) {
        TurnoSalidaDto turnoSalida = turnoService.registrarTurno(turno);
        return new ResponseEntity<>(turnoSalida, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoSalidaDto> buscarTurnoPorId(@PathVariable Long id) {
        TurnoSalidaDto turnoSalida = turnoService.buscarTurnoPorId(id);
        return new ResponseEntity<>(turnoSalida, HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TurnoSalidaDto>> listarTurnos() {
        List<TurnoSalidaDto> listaTurnos = turnoService.listarTurnos();
        return new ResponseEntity<>(listaTurnos, HttpStatus.OK);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<TurnoSalidaDto> actualizarTurno(@RequestBody TurnoModificacionEntradaDto turno) {
        TurnoSalidaDto turnoSalida = turnoService.actualizarTurno(turno);
        return new ResponseEntity<>(turnoSalida, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) {
        turnoService.eliminarTurno(id);
        return new ResponseEntity<>("Turno eliminado correctamente", HttpStatus.OK);
    }
}

