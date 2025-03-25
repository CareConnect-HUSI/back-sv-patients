package co.edu.javeriana.sv_patients.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.javeriana.sv_patients.Entity.TratamientoPacienteEntity;

public interface TratamientoPacienteRepository extends JpaRepository<TratamientoPacienteEntity, Long> {
    List<TratamientoPacienteEntity> findByPacienteId(Long idPaciente);
}
