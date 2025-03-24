package co.edu.javeriana.sv_patients.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.sv_patients.Entity.PacienteEntity;

@Repository
public interface  PacienteRepository extends JpaRepository<PacienteEntity, Long> {
    Boolean existsByDocumento(String documento);
}
