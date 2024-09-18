package com.example.humanresources.controllers;


import com.example.humanresources.Dto.PuestoDto;
import com.example.humanresources.entities.Puesto;
import com.example.humanresources.services.PuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/puestos")
public class PuestoController {

    @Autowired
    private PuestoService puestoService;
    @GetMapping
    public ResponseEntity<List<PuestoDto>> getAllPuestos() {
        List<PuestoDto> puestos = puestoService.findAll();
        return new ResponseEntity<>(puestos, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PuestoDto> getPuestoById(@PathVariable Long id) {
        Optional<PuestoDto> puesto = puestoService.findById(id);
        return puesto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<PuestoDto> createPuesto(@RequestBody PuestoDto puesto) {
        PuestoDto createdPuesto = puestoService.save(puesto);
        return new ResponseEntity<>(createdPuesto, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<PuestoDto> updatePuesto(@PathVariable Long id, @RequestBody PuestoDto puesto) {
        Optional<PuestoDto> updatedPuesto = puestoService.update(id, puesto);
        return updatedPuesto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePuesto(@PathVariable Long id) {
        puestoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
