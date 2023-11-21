package com.backend.clinicaodontologica.service.impl;


import com.backend.clinicaodontologica.repository.OdontologoRepository;

public class OdontologoService {
    private OdontologoRepository odontologoRepository;

    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

}
