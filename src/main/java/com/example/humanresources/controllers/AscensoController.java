package com.example.humanresources.controllers;

import com.example.humanresources.Dto.AscensoDTo;
import com.example.humanresources.entities.Ascenso;
import com.example.humanresources.services.AscensoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ascensos")
public class AscensoController {
    @Autowired
    private AscensoService ascensoService;

    @GetMapping
    public ResponseEntity<List<AscensoDTo>> getAllAscensos() {
        List<AscensoDTo> ascensos = ascensoService.findAll();
        return new ResponseEntity<>(ascensos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AscensoDTo> getAscensoById(@PathVariable Long id) {
        Optional<AscensoDTo> ascenso = ascensoService.findById(id);
        return ascenso.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<AscensoDTo> createAscenso(@RequestBody AscensoDTo ascenso) {
        AscensoDTo createdAscenso = ascensoService.save(ascenso);
        return new ResponseEntity<>(createdAscenso, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AscensoDTo> updateAscenso(@PathVariable Long id, @RequestBody AscensoDTo ascenso) {
        Optional<AscensoDTo> updatedAscenso = ascensoService.update(id, ascenso);
        return updatedAscenso.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAscenso(@PathVariable Long id) {
        ascensoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
