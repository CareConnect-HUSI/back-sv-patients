package co.edu.javeriana.sv_patients.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TratamientoPacienteEntity {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idPaciente")
    private PacienteEntity paciente;

    @ManyToOne
    @JoinColumn(name = "idTratamiento")
    private TratamientoEntity tratamiento;


    public TratamientoPacienteEntity() {
    }

    public TratamientoPacienteEntity(Long id, PacienteEntity paciente, TratamientoEntity tratamiento) {
        this.id = id;
        this.paciente = paciente;
        this.tratamiento = tratamiento;
    }

    public Long getId() {
        return this.id;
    }

    public PacienteEntity getPaciente() {
        return this.paciente;
    }

    public TratamientoEntity getTratamiento() {
        return tratamiento;
    }
    
    public void setTratamiento(TratamientoEntity tratamiento) {
        this.tratamiento = tratamiento;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }

}

