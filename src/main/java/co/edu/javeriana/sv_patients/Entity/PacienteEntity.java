package co.edu.javeriana.sv_patients.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "paciente")
public class PacienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String numeroIdentificacion;
    private String nombre_acudiente;
    private String parentezco_acudiente;
    private String telefono_acudiente;
    private String barrio;
    private String conjunto;
    private String localidad;
    private Double latitud;
    private Double longitud;
    private String estado;

    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_identificacion_id")
    private TipoIdentificacionEntity tipoIdentificacion;

    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ActividadPacienteVisita> actividades;

    public PacienteEntity() {
    }

    public PacienteEntity(Long id, String nombre, String apellido, String direccion, String telefono,
            Long tipo_identificacion_id, String numero_identificacion, String nombre_acudiente,
            String parentezco_acudiente, String telefono_acudiente, String barrio, String conjunto,
            String localidad, Double latitud, Double longitud, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.numeroIdentificacion = numero_identificacion;
        this.nombre_acudiente = nombre_acudiente;
        this.parentezco_acudiente = parentezco_acudiente;
        this.telefono_acudiente = telefono_acudiente;
        this.barrio = barrio;
        this.conjunto = conjunto;
        this.localidad = localidad;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado = estado;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TipoIdentificacionEntity getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacionEntity tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumero_identificacion() {
        return numeroIdentificacion;
    }

    public void setNumero_identificacion(String numero_identificacion) {
        this.numeroIdentificacion = numero_identificacion;
    }

    public String getNombre_acudiente() {
        return nombre_acudiente;
    }

    public void setNombre_acudiente(String nombre_acudiente) {
        this.nombre_acudiente = nombre_acudiente;
    }

    public String getParentezco_acudiente() {
        return parentezco_acudiente;
    }

    public void setParentezco_acudiente(String parentezco_acudiente) {
        this.parentezco_acudiente = parentezco_acudiente;
    }

    public String getTelefono_acudiente() {
        return telefono_acudiente;
    }

    public void setTelefono_acudiente(String telefono_acudiente) {
        this.telefono_acudiente = telefono_acudiente;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getConjunto() {
        return conjunto;
    }

    public void setConjunto(String conjunto) {
        this.conjunto = conjunto;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public List<ActividadPacienteVisita> getActividades() {
        return actividades;
    }

    public void setActividades(List<ActividadPacienteVisita> actividades) {
        this.actividades = actividades;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}