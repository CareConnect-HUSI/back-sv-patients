package co.edu.javeriana.sv_patients.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.sv_patients.Entity.TipoIdentificacionEntity;

@Repository
public interface  TipoIdentificacionRepository  extends JpaRepository<TipoIdentificacionEntity, Long> {
    TipoIdentificacionEntity findByName(String name);
    
}
