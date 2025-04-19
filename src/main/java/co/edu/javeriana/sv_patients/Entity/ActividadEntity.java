/*package co.edu.javeriana.sv_patients.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "actividad")
public class ActividadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Long tipo_actividad_id;
    
    private String descripcion;

    private Integer cantidad;

    private String fecha_registro;

    private String usuario_registra;

    public ActividadEntity() {
    }

    public ActividadEntity(Long id, String nombre, Long tipo_actividad_id, String descripcion, Integer cantidad,
            String fecha_registro, String usuario_registra) {
        this.id = id;
        this.nombre = nombre;
        this.tipo_actividad_id = tipo_actividad_id;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.fecha_registro = fecha_registro;
        this.usuario_registra = usuario_registra;
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

    public Long getTipo_actividad_id() {
        return tipo_actividad_id;
    }

    public void setTipo_actividad_id(Long tipo_actividad_id) {
        this.tipo_actividad_id = tipo_actividad_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getUsuario_registra() {
        return usuario_registra;
    }
}
*/