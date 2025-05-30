package co.edu.javeriana.sv_patients.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.sv_patients.Entity.BarrioEntity;

@Repository
public interface BarrioRepository extends JpaRepository<BarrioEntity, Long> {
    BarrioEntity findByNombre(String nombre);
    boolean existsByNombre(String nombre);
    boolean existsByLocalidad_Codigo(String codigo);
    List<BarrioEntity> findByLocalidad_Codigo(String codigo);
}