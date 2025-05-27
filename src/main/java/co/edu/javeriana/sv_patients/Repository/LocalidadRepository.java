package co.edu.javeriana.sv_patients.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.sv_patients.Entity.LocalidadEntity;

@Repository
public interface LocalidadRepository extends JpaRepository<LocalidadEntity, String> {
    List<LocalidadEntity> findAllByOrderByNombreAsc();

    LocalidadEntity findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}