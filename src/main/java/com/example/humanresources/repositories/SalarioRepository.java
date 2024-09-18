package com.example.humanresources.repositories;

import com.example.humanresources.entities.Salario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalarioRepository extends CrudRepository<Salario, Long> {
}
