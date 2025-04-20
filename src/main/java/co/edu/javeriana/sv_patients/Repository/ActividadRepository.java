package co.edu.javeriana.sv_patients.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.sv_patients.Entity.ActividadEntity;

@Repository
public interface ActividadRepository extends JpaRepository<ActividadEntity, Long> {
    ActividadEntity findByName(String name);
    Optional <ActividadEntity> findById(Long id);
    
}
