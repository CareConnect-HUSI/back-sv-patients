package co.edu.javeriana.sv_patients.DTO;

import co.edu.javeriana.sv_patients.Entity.ActividadPacienteVisita;

public class ActividadPacienteDTO {
    public Long id;
    public String nombreActividad;
    public Integer dosis;
    public Integer frecuencia;
    public Integer diasTratamiento;
    public String hora;
    public String fechaInicio;
    public String fechaFin;
    public Long actividadId;
    public Long tipoActividadId;

    public ActividadPacienteDTO(ActividadPacienteVisita apv) {
        this.id = apv.getId();
        this.nombreActividad = apv.getActividad().getName();
        this.dosis = apv.getDosis();
        this.frecuencia = apv.getFrecuencia();
        this.diasTratamiento = apv.getDiasTratamiento();
        this.hora = apv.getHora() != null ? apv.getHora().toString() : null;
        this.fechaInicio = apv.getFechaInicio() != null ? apv.getFechaInicio().toString() : null;
        this.fechaFin = apv.getFechaFin() != null ? apv.getFechaFin().toString() : null;
        this.actividadId = apv.getActividad().getId();
        this.tipoActividadId = apv.getActividad().getTipoActividad().getId();
    }

    public ActividadPacienteDTO() {
    }

    public ActividadPacienteDTO(String nombreActividad, Integer dosis, Integer frecuencia, Integer diasTratamiento,
            String fechaInicio, String fechaFin, String hora) {
        this.nombreActividad = nombreActividad;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
        this.diasTratamiento = diasTratamiento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.hora = hora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Long getActividadId() {
        return actividadId;
    }

    public void setActividadId(Long actividadId) {
        this.actividadId = actividadId;
    }

    public Long getTipoActividadId() {
        return tipoActividadId;
    }

    public void setTipoActividadId(Long tipoActividadId) {
        this.tipoActividadId = tipoActividadId;
    }
}

