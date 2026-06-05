package com.portafolio.reservas.controller;

import com.portafolio.reservas.model.Reserva;
import com.portafolio.reservas.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reservas") // Ruta base de la API
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    // GET: http://localhost:8080/api/reservas
    @GetMapping
    public List<Reserva> listarTodas() {
        return reservaService.obtenerTodas();
    }

    // GET por ID: http://localhost:8080/api/reservas/1
   @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscarPorId(@PathVariable Long id) {
        return reservaService.obtenerPorId(id)
            .map(reserva -> new ResponseEntity<>(reserva, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // POST: http://localhost:8080/api/reservas
    @PostMapping
    public ResponseEntity<Reserva> guardarReserva(@Valid@RequestBody Reserva reserva) {
        Reserva nuevaReserva = reservaService.crearReserva(reserva);
        return new ResponseEntity<>(nuevaReserva, HttpStatus.CREATED);
    }

    // PATCH: http://localhost:8080/api/reservas/1/estado?nuevoEstado=confirmada
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Reserva> modificarEstado(
            @PathVariable Long id, 
            @RequestParam String nuevoEstado) {
        try {
            Reserva reservaActualizada = reservaService.actualizarEstado(id, nuevoEstado);
            return new ResponseEntity<>(reservaActualizada, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE: http://localhost:8080/api/reservas/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarReserva(@PathVariable Long id) {
        try {
            reservaService.eliminarReserva(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}