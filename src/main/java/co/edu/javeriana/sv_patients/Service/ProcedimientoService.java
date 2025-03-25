package co.edu.javeriana.sv_patients.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.sv_patients.Entity.PacienteEntity;
import co.edu.javeriana.sv_patients.Entity.ProcedimientoEntity;
import co.edu.javeriana.sv_patients.Entity.ProcedimientoPacienteEntity;
import co.edu.javeriana.sv_patients.Repository.ProcedimientoPacienteRepository;

@Service
public class ProcedimientoService {
    
    @Autowired
    private ProcedimientoPacienteRepository repo;

    public List<ProcedimientoPacienteEntity> obtenerPorPaciente(Long idPaciente) {
        return repo.findByPacienteId(idPaciente);
    }

    public ProcedimientoPacienteEntity asignarProcedimiento(Long pacienteId, Long procedimientoId) {
        ProcedimientoPacienteEntity pp = new ProcedimientoPacienteEntity();
        pp.setPaciente(new PacienteEntity(pacienteId));
        pp.setProcedimiento(new ProcedimientoEntity(procedimientoId));
        return repo.save(pp);
    }
}
