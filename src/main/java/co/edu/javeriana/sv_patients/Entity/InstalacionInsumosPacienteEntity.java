package co.edu.javeriana.sv_patients.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "instalacion_insumos_paciente")
public class InstalacionInsumosPacienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long paciente_id;
    private Long actividad_id;
    private LocalDate fecha_instalacion;
    private Integer cantidad_disponible;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paciente_id", insertable = false, updatable = false)
    private PacienteEntity paciente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "actividad_id", insertable = false, updatable = false)
    private ActividadEntity actividad;

    public InstalacionInsumosPacienteEntity() {
    }

    public InstalacionInsumosPacienteEntity(Long id, Long paciente_id, Long actividad_id, LocalDate fecha_instalacion,
            Integer cantidad_disponible) {
        this.id = id;
        this.paciente_id = paciente_id;
        this.actividad_id = actividad_id;
        this.fecha_instalacion = fecha_instalacion;
        this.cantidad_disponible = cantidad_disponible;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPaciente_id() {
        return paciente_id;
    }

    public void setPaciente_id(Long paciente_id) {
        this.paciente_id = paciente_id;
    }

    public Long getActividad_id() {
        return actividad_id;
    }

    public void setActividad_id(Long actividad_id) {
        this.actividad_id = actividad_id;
    }

    public LocalDate getFecha_instalacion() {
        return fecha_instalacion;
    }

    public void setFecha_instalacion(LocalDate fecha_instalacion) {
        this.fecha_instalacion = fecha_instalacion;
    }

    public Integer getCantidad_disponible() {
        return cantidad_disponible;
    }

    public void setCantidad_disponible(Integer cantidad_disponible) {
        this.cantidad_disponible = cantidad_disponible;
    }

    public PacienteEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }

    public ActividadEntity getActividad() {
        return actividad;
    }

    public void setActividad(ActividadEntity actividad) {
        this.actividad = actividad;
    }
}


/*
 * -- TABLA INSTALACION INSUMOS PACIENTE
CREATE TABLE IF NOT EXISTS instalacion_insumos_paciente(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    actividad_id BIGINT NOT NULL,
    fecha_instalacion DATE,
    cantidad_disponible INT,
    CONSTRAINT fk_instalacion_insumos_paciente_paciente FOREIGN KEY (paciente_id) REFERENCES paciente(id),
    CONSTRAINT fk_instalacion_insumos_paciente_actividad FOREIGN KEY (actividad_id) REFERENCES actividad(id)
);
 */