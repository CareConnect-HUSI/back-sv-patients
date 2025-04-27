package co.edu.javeriana.sv_patients.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.sv_patients.Entity.ActividadPacienteVisita;

@Repository
public interface ActividadPacienteVisitaRepository extends JpaRepository<ActividadPacienteVisita, Long>{
    List<ActividadPacienteVisita> findByPacienteId(Long pacienteId);
    List<ActividadPacienteVisita> findByActividadId(Long actividadId);

}