package com.example.humanresources.repositories;

import com.example.humanresources.entities.Evaluacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluacionRepository extends CrudRepository<Evaluacion, Long> {
}
