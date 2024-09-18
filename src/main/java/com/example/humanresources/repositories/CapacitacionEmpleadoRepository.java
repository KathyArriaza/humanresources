package com.example.humanresources.repositories;

import com.example.humanresources.entities.CapacitacionEmpleado;
import com.example.humanresources.entities.Empleado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapacitacionEmpleadoRepository extends CrudRepository<CapacitacionEmpleado, Long> {
}
