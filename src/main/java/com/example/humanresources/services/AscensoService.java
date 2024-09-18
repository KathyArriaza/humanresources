package com.example.humanresources.services;

import com.example.humanresources.Dto.AscensoDTo;
import com.example.humanresources.entities.Ascenso;

import java.util.List;
import java.util.Optional;

public interface AscensoService {

  List<AscensoDTo> findAll();
  Optional<AscensoDTo> findById(Long id);
  AscensoDTo save(AscensoDTo ascensoDTo);
  Optional<AscensoDTo> update(Long id, AscensoDTo ascensoDTo);
    void deleteById(Long id);
}
