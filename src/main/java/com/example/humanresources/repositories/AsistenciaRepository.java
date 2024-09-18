package com.example.humanresources.repositories;

import com.example.humanresources.entities.Asistencia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsistenciaRepository extends CrudRepository<Asistencia, Long> {
}
