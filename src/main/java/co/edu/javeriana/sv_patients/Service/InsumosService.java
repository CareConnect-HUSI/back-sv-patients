package co.edu.javeriana.sv_patients.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.sv_patients.Entity.ActividadEntity;
import co.edu.javeriana.sv_patients.Entity.InstalacionInsumosPacienteEntity;
import co.edu.javeriana.sv_patients.Entity.PacienteEntity;
import co.edu.javeriana.sv_patients.Repository.ActividadRepository;
import co.edu.javeriana.sv_patients.Repository.InstalcionInsumosPacienteRepository;
import co.edu.javeriana.sv_patients.Repository.PacienteRepository;

@Service
public class InsumosService {
    @Autowired
    private InstalcionInsumosPacienteRepository insumosRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ActividadRepository actividadRepository;


    public InstalacionInsumosPacienteEntity save(InstalacionInsumosPacienteEntity insumo) {

    Optional<PacienteEntity> pacienteOpt = pacienteRepository.findById(insumo.getPaciente_id());
    Optional<ActividadEntity> actividadOpt = actividadRepository.findById(insumo.getActividad_id());

    if (pacienteOpt.isEmpty() || actividadOpt.isEmpty()) {
        throw new IllegalArgumentException("Paciente o Actividad no encontrada");
    }

    insumo.setPaciente(pacienteOpt.get());
    insumo.setActividad(actividadOpt.get());

    return insumosRepository.save(insumo);
    }

}
