package co.edu.javeriana.sv_patients.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.sv_patients.Entity.PacienteEntity;

@Repository
public interface  PacienteRepository extends JpaRepository<PacienteEntity, Long> {
    Optional<PacienteEntity> findById(Long id);
}
