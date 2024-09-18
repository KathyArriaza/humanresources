package com.example.humanresources.services.Implements;

import com.example.humanresources.Dto.EvaluacionDto;
import com.example.humanresources.entities.Evaluacion;
import com.example.humanresources.repositories.EvaluacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EvaluacionesImplTest {


    @Mock
    private EvaluacionRepository evaluacionRepository;

    @InjectMocks
    private EvaluacionesImpl evaluacionesImpl;

    private Evaluacion evaluacion;
    private EvaluacionDto evaluacionDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        evaluacion = new Evaluacion();
        evaluacion.setId(1L);
        evaluacion.setFecha("2023-10-01");
        evaluacion.setCompetencias("Competencia 1");
        evaluacion.setCalificacion(5.9);
        evaluacion.setComentarios("Comentarios 1");

        evaluacionDto = new EvaluacionDto();
        evaluacionDto.setId(1L);
        evaluacionDto.setFecha("2023-10-01");
        evaluacionDto.setCompetencias("Competencia 1");
        evaluacionDto.setCalificacion(5.9);
        evaluacionDto.setComentarios("Comentarios 1");
    }

    @Test
    void testFindAll() {
        List<Evaluacion> evaluaciones = Arrays.asList(evaluacion);
        when(evaluacionRepository.findAll()).thenReturn(evaluaciones);

        List<EvaluacionDto> result = evaluacionesImpl.findAll();
        assertEquals(1, result.size());
        verify(evaluacionRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(evaluacionRepository.findById(1L)).thenReturn(Optional.of(evaluacion));

        Optional<EvaluacionDto> result = evaluacionesImpl.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("Competencia 1", result.get().getCompetencias());
        verify(evaluacionRepository, times(1)).findById(1L);
    }

    @Test
    void testSave() {
        when(evaluacionRepository.save(any(Evaluacion.class))).thenReturn(evaluacion);

        EvaluacionDto result = evaluacionesImpl.save(evaluacionDto);
        assertNotNull(result);
        assertEquals("Competencia 1", result.getCompetencias());
        verify(evaluacionRepository, times(1)).save(any(Evaluacion.class));
    }

    @Test
    void testUpdate() {
        when(evaluacionRepository.findById(1L)).thenReturn(Optional.of(evaluacion));
        when(evaluacionRepository.save(any(Evaluacion.class))).thenReturn(evaluacion);

        Optional<EvaluacionDto> result = evaluacionesImpl.update(1L, evaluacionDto);
        assertTrue(result.isPresent());
        assertEquals("Competencia 1", result.get().getCompetencias());
        verify(evaluacionRepository, times(1)).findById(1L);
        verify(evaluacionRepository, times(1)).save(any(Evaluacion.class));
    }

    @Test
    void testDeleteById() {
        doNothing().when(evaluacionRepository).deleteById(1L);

        evaluacionesImpl.deleteById(1L);
        verify(evaluacionRepository, times(1)).deleteById(1L);
    }
}