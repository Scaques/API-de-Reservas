package com.portafolio.reservas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del cliente no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String cliente;

    @NotNull(message = "La fecha y hora de la reserva es obligatoria")
    @Future(message = "La fecha de la reserva debe ser en el futuro")
    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @NotBlank(message = "El servicio es obligatorio")
    @Column(nullable = false, length = 50)
    private String servicio;

    @Column(nullable = false)
    private String estado;
    
    // Constructor vacío (Obligatorio para JPA)
    public Reserva() {
    }

    // Constructor completo
    public Reserva(Long id, String cliente, LocalDateTime fechaHora, String servicio, String estado) {
        this.id = id;
        this.cliente = cliente;
        this.fechaHora = fechaHora;
        this.servicio = servicio;
        this.estado = estado;
    }

    // --- GETTERS Y SETTERS MANUALES ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}