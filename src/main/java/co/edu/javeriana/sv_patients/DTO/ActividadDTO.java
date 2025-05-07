package co.edu.javeriana.sv_patients.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class ActividadDTO {
    private String nombreActividad;
    private Integer dosis;
    private Integer frecuencia;
    private Integer diasTratamiento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalTime hora;
    private Long id;
    private Long actividadId;

    public ActividadDTO() {
    }

    public ActividadDTO(String nombreActividad, Integer dosis, Integer frecuencia, Integer diasTratamiento,
            LocalDate fechaInicio, LocalDate fechaFin, LocalTime hora) {
        this.nombreActividad = nombreActividad;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
        this.diasTratamiento = diasTratamiento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.hora = hora;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActividadId(){
        return actividadId;
    }

    public void setActividadId(Long id){
        this.actividadId = id;
    }
}
