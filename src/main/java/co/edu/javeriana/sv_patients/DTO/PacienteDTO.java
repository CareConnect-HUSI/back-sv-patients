package co.edu.javeriana.sv_patients.DTO;

import java.util.List;

public class PacienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String numeroIdentificacion;
    private String nombreAcudiente;
    private String parentezcoAcudiente;
    private String telefonoAcudiente;
    private String barrio;
    private String conjunto;
    private String localidad;
    private Double latitud;
    private Double longitud;
    private String estado;
    private TipoIdentificacionDTO tipoIdentificacion;
    private List<ActividadDTO> actividades;

    public PacienteDTO() {
    }   
    
    public PacienteDTO(Long id, String nombre, String apellido, String direccion, String telefono,
            String numeroIdentificacion, String nombreAcudiente, String parentezcoAcudiente,
            String telefonoAcudiente, String barrio, String conjunto, String localidad,
            Double latitud, Double longitud, TipoIdentificacionDTO tipoIdentificacion,
            List<ActividadDTO> actividades, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.numeroIdentificacion = numeroIdentificacion;
        this.nombreAcudiente = nombreAcudiente;
        this.parentezcoAcudiente = parentezcoAcudiente;
        this.telefonoAcudiente = telefonoAcudiente;
        this.barrio = barrio;
        this.conjunto = conjunto;
        this.localidad = localidad;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tipoIdentificacion = tipoIdentificacion;
        this.actividades = actividades;
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

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombreAcudiente() {
        return nombreAcudiente;
    }

    public void setNombreAcudiente(String nombreAcudiente) {
        this.nombreAcudiente = nombreAcudiente;
    }

    public String getParentezcoAcudiente() {
        return parentezcoAcudiente;
    }

    public void setParentezcoAcudiente(String parentezcoAcudiente) {
        this.parentezcoAcudiente = parentezcoAcudiente;
    }

    public String getTelefonoAcudiente() {
        return telefonoAcudiente;
    }

    public void setTelefonoAcudiente(String telefonoAcudiente) {
        this.telefonoAcudiente = telefonoAcudiente;
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

    public TipoIdentificacionDTO getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacionDTO tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public List<ActividadDTO> getActividades() {
        return actividades;
    }

    public void setActividades(List<ActividadDTO> actividades) {
        this.actividades = actividades;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
