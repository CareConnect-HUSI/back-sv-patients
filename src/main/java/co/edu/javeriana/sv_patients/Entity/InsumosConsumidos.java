package co.edu.javeriana.sv_patients.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class InsumosConsumidos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    @Column(name = "instalacion_insumos_paciente_id")
    private Long idInstalacion;

    @Column(name = "visita_id")
    private Long idVisita;

    @Column(name = "cantidad_consumida")
    private Long cantidadConsumida;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdInstalacion() {
        return idInstalacion;
    }

    public void setIdInstalacion(Long idInstalacion) {
        this.idInstalacion = idInstalacion;
    }

    public Long getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(Long idVisita) {
        this.idVisita = idVisita;
    }

    public Long getCantidadConsumida() {
        return cantidadConsumida;
    }

    public void setCantidadConsumida(Long cantidadConsumida) {
        this.cantidadConsumida = cantidadConsumida;
    }

    public InsumosConsumidos(Long idInstalacion, Long idVisita, Long cantidadConsumida) {
        this.idInstalacion = idInstalacion;
        this.idVisita = idVisita;
        this.cantidadConsumida = cantidadConsumida;
    }

    public InsumosConsumidos() {
    }
}
