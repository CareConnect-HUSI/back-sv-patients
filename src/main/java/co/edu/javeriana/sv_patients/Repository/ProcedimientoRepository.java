package co.edu.javeriana.sv_patients.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.javeriana.sv_patients.Entity.ProcedimientoEntity;


public interface ProcedimientoRepository extends JpaRepository<ProcedimientoEntity, Long> {}
