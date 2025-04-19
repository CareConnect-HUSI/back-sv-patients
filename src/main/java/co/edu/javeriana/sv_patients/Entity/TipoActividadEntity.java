/*package co.edu.javeriana.sv_patients.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo_actividad")
public class TipoActividadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "tipo_actividad_id")
    private ActividadEntity actividadEntity;

    public TipoActividadEntity() {
    }

    public TipoActividadEntity(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ActividadEntity getActividadEntity() {
        return actividadEntity;
    }

    public void setActividadEntity(ActividadEntity actividadEntity) {
        this.actividadEntity = actividadEntity;
    }
}
*/