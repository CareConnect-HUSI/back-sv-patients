package co.edu.javeriana.sv_patients.DTO;

import java.util.List;

import co.edu.javeriana.sv_patients.Entity.ProcedimientoEntity;
import co.edu.javeriana.sv_patients.Entity.TratamientoEntity;

public class PacienteRegistroDTO {
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

    private List<TratamientoEntity> tratamientos;
    private List<ProcedimientoEntity> procedimientos;

    public PacienteRegistroDTO() {
    }

    public PacienteRegistroDTO(String nombre, String apellido, String documento, String tipoDocumento, String direccion,
            String telefono, String nombreFamiliar, String telefonoFamiliar, String parentescoFamiliar, String barrio,
            String conjunto, String latitud, String longitud, List<TratamientoEntity> tratamientos,
            List<ProcedimientoEntity> procedimientos) {
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
        this.tratamientos = tratamientos;
        this.procedimientos = procedimientos;
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
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

    public String getNombreFamiliar() {
        return nombreFamiliar;
    }

    public void setNombreFamiliar(String nombreFamiliar) {
        this.nombreFamiliar = nombreFamiliar;
    }

    public String getTelefonoFamiliar() {
        return telefonoFamiliar;
    }

    public void setTelefonoFamiliar(String telefonoFamiliar) {
        this.telefonoFamiliar = telefonoFamiliar;
    }

    public String getParentescoFamiliar() {
        return parentescoFamiliar;
    }

    public void setParentescoFamiliar(String parentescoFamiliar) {
        this.parentescoFamiliar = parentescoFamiliar;
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

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public List<TratamientoEntity> getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(List<TratamientoEntity> tratamientos) {
        this.tratamientos = tratamientos;
    }

    public List<ProcedimientoEntity> getProcedimientos() {
        return procedimientos;
    }

    public void setProcedimientos(List<ProcedimientoEntity> procedimientos) {
        this.procedimientos = procedimientos;
    }
}
