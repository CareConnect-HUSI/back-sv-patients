package co.edu.javeriana.sv_patients.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "paciente")
public class PacienteEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String documento;
    private String tipoDocumento;
    private String direccion;
    private String telefono;
    private String nombreFamiliar;
    private String telefonoFamiliar;
    private String parentescoFamiliar;
    private String barrio;
    private String conjunto;
    private String latitud;
    private String longitud;


    public PacienteEntity() {
    }

    public PacienteEntity(Long id) {
        this.id = id;   
    }

    public PacienteEntity(Long id, String nombre, String apellido, String documento, String tipoDocumento, String direccion, String telefono, String nombreFamiliar, String telefonoFamiliar, String parentescoFamiliar, String barrio, String conjunto, String latitud, String longitud) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.tipoDocumento = tipoDocumento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.nombreFamiliar = nombreFamiliar;
        this.telefonoFamiliar = telefonoFamiliar;
        this.parentescoFamiliar = parentescoFamiliar;
        this.barrio = barrio;
        this.conjunto = conjunto;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public String getDocumento() {
        return this.documento;
    }

    public String getTipoDocumento() {
        return this.tipoDocumento;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public String getNombreFamiliar() {
        return this.nombreFamiliar;
    }

    public String getTelefonoFamiliar() {
        return this.telefonoFamiliar;
    }

    public String getParentescoFamiliar() {
        return this.parentescoFamiliar;
    }

    public String getBarrio() {
        return this.barrio;
    }

    public String getConjunto() {
        return this.conjunto;
    }

    public String getLatitud() {
        return this.latitud;
    }

    public String getLongitud() {
        return this.longitud;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setNombreFamiliar(String nombreFamiliar) {
        this.nombreFamiliar = nombreFamiliar;
    }

    public void setTelefonoFamiliar(String telefonoFamiliar) {
        this.telefonoFamiliar = telefonoFamiliar;
    }

    public void setParentescoFamiliar(String parentescoFamiliar) {
        this.parentescoFamiliar = parentescoFamiliar;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public void setConjunto(String conjunto) {
        this.conjunto = conjunto;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
