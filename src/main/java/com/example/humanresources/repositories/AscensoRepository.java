package com.example.humanresources.repositories;

import com.example.humanresources.entities.Ascenso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AscensoRepository extends CrudRepository<Ascenso, Long> {

}
