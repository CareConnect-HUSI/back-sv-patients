package co.edu.javeriana.sv_patients.DTO;

import java.util.Date;

public class MedicamentoRequestDTO {
    private Long pacienteId;
    private Long actividadId;
    private Integer cantidad;

    // Getters and Setters
    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getActividadId() {
        return actividadId;
    }

    public void setActividadId(Long actividadId) {
        this.actividadId = actividadId;
    }
    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
