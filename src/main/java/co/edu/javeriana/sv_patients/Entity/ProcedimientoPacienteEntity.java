package co.edu.javeriana.sv_patients.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProcedimientoPacienteEntity {
    @Id @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "idPaciente")
    private PacienteEntity paciente;

    @ManyToOne
    @JoinColumn(name = "idProcedimiento")
    private ProcedimientoEntity procedimiento;

    public ProcedimientoPacienteEntity() {
    }

    public ProcedimientoPacienteEntity(Long id, PacienteEntity paciente, ProcedimientoEntity procedimiento) {
        this.id = id;
        this.paciente = paciente;
        this.procedimiento = procedimiento;
    }

    public Long getId() {
        return this.id;
    }

    public PacienteEntity getPaciente() {
        return this.paciente;
    }

    public ProcedimientoEntity getProcedimiento() {
        return this.procedimiento;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }

    public void setProcedimiento(ProcedimientoEntity procedimiento) {
        this.procedimiento = procedimiento;
    }

}
