package com.example.humanresources.repositories;

import com.example.humanresources.entities.Capacitacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapacitacionRepository extends CrudRepository<Capacitacion, Long> {
}
