package co.edu.javeriana.sv_patients.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.sv_patients.Entity.BarrioEntity;
import co.edu.javeriana.sv_patients.Entity.LocalidadEntity;
import co.edu.javeriana.sv_patients.Repository.BarrioRepository;
import co.edu.javeriana.sv_patients.Repository.LocalidadRepository;

@Service
public class PacienteService {

    @Autowired
    private BarrioRepository barrioRepository;

    @Autowired
    private LocalidadRepository localidadRepository;

    public List<LocalidadEntity> getLocalidades() {
        return localidadRepository.findAll();
    }

    public List<BarrioEntity> getBarrios(String codigoLocalidad) {
        List<BarrioEntity> barrios = barrioRepository.findByLocalidad_Codigo(codigoLocalidad);
        if (barrios.isEmpty()) {
            throw new RuntimeException("No se encontraron barrios para la localidad especificada");
        }
        return barrios;
    }
}
