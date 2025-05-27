package co.edu.javeriana.sv_patients.DTO;


public class MedicamentoDTO {
    private Long id;
    private String nombre;
    private Integer cantidad;

    public MedicamentoDTO(Long id, String nombre, Integer cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    // Getters and setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setID(Long id) {
        this.id = id;
    }


    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
} 
