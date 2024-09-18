package com.example.humanresources.repositories;

import com.example.humanresources.entities.Permiso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisoRepository extends CrudRepository<Permiso, Long> {
}
