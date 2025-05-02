package co.edu.javeriana.sv_patients.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.sv_patients.Entity.PacienteEntity;
import co.edu.javeriana.sv_patients.Entity.TipoIdentificacionEntity;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

    Optional<PacienteEntity> findById(Long id);

    boolean existsByTipoIdentificacionAndNumeroIdentificacion(
            TipoIdentificacionEntity tipoIdentificacion,
            String numeroIdentificacion
    );

    Optional<PacienteEntity> findByNumeroIdentificacion(String numeroIdentificacion);

}
