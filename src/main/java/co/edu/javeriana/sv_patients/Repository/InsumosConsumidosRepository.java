package co.edu.javeriana.sv_patients.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.sv_patients.Entity.InsumosConsumidos;

@Repository
public interface  InsumosConsumidosRepository extends JpaRepository<InsumosConsumidos, Long>{
    
    @Query("SELECT SUM(i.cantidadConsumida) FROM InsumosConsumidos i WHERE i.idInstalacion = :idInstalacion")
    Long cantidadConsumidaPorInstalacion(@Param("idInstalacion") Long idInstalacion);

}
