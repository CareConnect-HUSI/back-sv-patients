package co.edu.javeriana.sv_patients.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "tratamiento")
public class TratamientoEntity {
    @Id @GeneratedValue
    private Long id;

    private String nombre;
    private String descripcion;
    private String dosis;
    private String frecuencia;
    private int diasTratamiento;
    private String horaInicioTratamiento;
    private String fechaInicio;
    private String fechaFin;
    private String duracionVisita;

    @OneToMany(mappedBy = "tratamiento", cascade = CascadeType.ALL)
    private List<TratamientoPacienteEntity> pacientes;

    public TratamientoEntity() {
    }

    public TratamientoEntity(Long id, String nombre, String descripcion, String dosis, String frecuencia, int diasTratamiento, String horaInicioTratamiento, String fechaInicio, String fechaFin, String duracionVisita, List<TratamientoPacienteEntity> pacientes) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
        this.diasTratamiento = diasTratamiento;
        this.horaInicioTratamiento = horaInicioTratamiento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.duracionVisita = duracionVisita;
        this.pacientes = pacientes;
    }

    public TratamientoEntity(Long id) {
        this.id = id;
    }
    

    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
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

    public String getHoraInicioTratamiento() {
        return this.horaInicioTratamiento;
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

    public List<TratamientoPacienteEntity> getPacientes() {
        return this.pacientes;
    }

    public void setPacientes(List<TratamientoPacienteEntity> pacientes) {
        this.pacientes = pacientes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public void setHoraInicioTratamiento(String horaInicioTratamiento) {
        this.horaInicioTratamiento = horaInicioTratamiento;
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
}



