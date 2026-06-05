package com.portafolio.reservas.service;

import com.portafolio.reservas.model.Reserva;
import com.portafolio.reservas.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    // Obtener reservas
    public List<Reserva> obtenerTodas() {
        return reservaRepository.findAll();
    }

    // Buscar reserva por ID
    public Optional<Reserva> obtenerPorId(Long id) {
        return reservaRepository.findById(id);
    }

    // Crear reserva nueva
    public Reserva crearReserva(Reserva reserva) {
        // Regla de negocio: Toda reserva nueva inicia en estado PENDIENTE
        reserva.setEstado("PENDIENTE");
        return reservaRepository.save(reserva);
    }

    // Actualizar el estado de una reserva existente
    public Reserva actualizarEstado(Long id, String nuevoEstado) {
        Optional<Reserva> reservaExistente = reservaRepository.findById(id);
        
        if (reservaExistente.isPresent()) {
            Reserva reserva = reservaExistente.get();
            reserva.setEstado(nuevoEstado.toUpperCase());
            return reservaRepository.save(reserva);
        } else {
            throw new RuntimeException("La reserva con ID " + id + " no existe.");
        }
    }

    // Eliminar una reserva
    public void eliminarReserva(Long id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
        } else {
            throw new RuntimeException("No se puede eliminar. La reserva con ID " + id + " no existe.");
        }
    }
}