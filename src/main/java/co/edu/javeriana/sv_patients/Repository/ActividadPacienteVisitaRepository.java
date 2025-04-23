package co.edu.javeriana.sv_patients.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.sv_patients.Entity.ActividadPacienteVisita;

@Repository
public interface ActividadPacienteVisitaRepository extends JpaRepository<ActividadPacienteVisita, Long>{
    ActividadPacienteVisita findById(long id);
    ActividadPacienteVisita findByPacienteId(long id);
    ActividadPacienteVisita findByActividadId(long id);
}
