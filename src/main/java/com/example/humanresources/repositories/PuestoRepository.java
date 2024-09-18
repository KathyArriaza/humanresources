package com.example.humanresources.repositories;

import com.example.humanresources.entities.Puesto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuestoRepository extends CrudRepository<Puesto, Long> {
}
