package co.edu.javeriana.sv_patients.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.sv_patients.Entity.InstalacionInsumosPacienteEntity;

@Repository
public interface InstalacionInsumosPacienteRepository extends JpaRepository<InstalacionInsumosPacienteEntity, Long>{
    Optional <InstalacionInsumosPacienteEntity> findById(Long id);
    List<InstalacionInsumosPacienteEntity> findByPacienteId(Long pacienteId);

}