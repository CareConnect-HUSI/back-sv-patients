package co.edu.javeriana.sv_patients.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "procedimiento")
public class ProcedimientoEntity {
    @Id @GeneratedValue
    private Long id;
    private String nombre;
    private String dosis;
    private String frecuencia;
    private int diasTratamiento;
    private String fechaInicio;
    private String fechaFin;
    private String duracionVisita;
    private String horaMedicamento;

    @OneToMany(mappedBy = "procedimiento", cascade = CascadeType.ALL)
    private List<ProcedimientoPacienteEntity> pacientes;

    public ProcedimientoEntity() {
    }

    public ProcedimientoEntity(Long id, String nombre, String codigo, String dosis, String frecuencia, int diasTratamiento, String fechaInicio, String fechaFin, String duracionVisita, String horaMedicamento, List<ProcedimientoPacienteEntity> pacientes) {
        this.id = id;
        this.nombre = nombre;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
        this.diasTratamiento = diasTratamiento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.duracionVisita = duracionVisita;
        this.horaMedicamento = horaMedicamento;
        this.pacientes = pacientes;
    }

    public ProcedimientoEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDosis() {
        return this.dosis;
    }

    public String getFrecuencia() {
        return this.frecuencia;
    }

    public int getDiasTratamiento() {
        return this.diasTratamiento;
    }

    public String getFechaInicio() {
        return this.fechaInicio;
    }

    public String getFechaFin() {
        return this.fechaFin;
    }

    public String getDuracionVisita() {
        return this.duracionVisita;
    }

    public String getHoraMedicamento() {
        return this.horaMedicamento;
    }

    public List<ProcedimientoPacienteEntity> getPacientes() {
        return this.pacientes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public void setDiasTratamiento(int diasTratamiento) {
        this.diasTratamiento = diasTratamiento;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setDuracionVisita(String duracionVisita) {
        this.duracionVisita = duracionVisita;
    }

    public void setHoraMedicamento(String horaMedicamento) {
        this.horaMedicamento = horaMedicamento;
    }

    public void setPacientes(List<ProcedimientoPacienteEntity> pacientes) {
        this.pacientes = pacientes;
    }
}

