package co.edu.javeriana.sv_patients.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.javeriana.sv_patients.Entity.ProcedimientoPacienteEntity;

public interface ProcedimientoPacienteRepository extends JpaRepository<ProcedimientoPacienteEntity, Long> {
    List<ProcedimientoPacienteEntity> findByPacienteId(Long idPaciente);
}
