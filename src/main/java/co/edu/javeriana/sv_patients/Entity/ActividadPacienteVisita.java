package co.edu.javeriana.sv_patients.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.type.descriptor.jdbc.LocalTimeJdbcType;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "actividad_paciente_visita")
public class ActividadPacienteVisita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer dosis;
    private Integer frecuencia;
    private Integer diasTratamiento;
    private Integer duracionVisita;


    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private PacienteEntity paciente;

    @ManyToOne
    @JoinColumn(name = "actividad_id", nullable = false)
    private ActividadEntity actividad;


    @Column(name = "hora")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime hora;


    public ActividadPacienteVisita() {
    }

    public ActividadPacienteVisita(Integer dosis, Integer frecuencia, Integer diasTratamiento, LocalDate fechaInicio, LocalDate fechaFin, LocalTime hora, Integer duracionVisita) {
        this.dosis = dosis;
        this.frecuencia = frecuencia;
        this.diasTratamiento = diasTratamiento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.hora = hora;
        this.duracionVisita = duracionVisita;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDosis() {
        return dosis;
    }

    public void setDosis(Integer dosis) {
        this.dosis = dosis;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Integer getDiasTratamiento() {
        return diasTratamiento;
    }

    public void setDiasTratamiento(Integer diasTratamiento) {
        this.diasTratamiento = diasTratamiento;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Integer getDuracionVisita() {
        return duracionVisita;
    }

    public void setDuracionVisita(Integer duracionVisita) {
        this.duracionVisita = duracionVisita;
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
