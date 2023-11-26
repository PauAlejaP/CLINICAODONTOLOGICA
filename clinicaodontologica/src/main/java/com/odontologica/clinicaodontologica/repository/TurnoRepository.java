package com.odontologica.clinicaodontologica.repository;
import com.odontologica.clinicaodontologica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {


}
