package co.edu.javeriana.sv_patients.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.edu.javeriana.sv_patients.Entity.PacienteEntity;
import co.edu.javeriana.sv_patients.Repository.PacienteRepository;

@Service
public class PacienteService {
    
    @Autowired
    private PacienteRepository pacienteRepository;

    public void registrarPaciente(PacienteEntity paciente) {
        pacienteRepository.save(paciente);
    }

    public Boolean existePaciente(String documento) {
        return pacienteRepository.existsByDocumento(documento);
    }

    public Page<PacienteEntity> obtenerPacientes(Pageable pageable) {
        return pacienteRepository.findAll(pageable);
    }
}
