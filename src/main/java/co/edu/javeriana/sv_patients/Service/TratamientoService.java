package co.edu.javeriana.sv_patients.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.sv_patients.Entity.PacienteEntity;
import co.edu.javeriana.sv_patients.Entity.TratamientoEntity;
import co.edu.javeriana.sv_patients.Entity.TratamientoPacienteEntity;
import co.edu.javeriana.sv_patients.Repository.TratamientoPacienteRepository;

@Service
public class TratamientoService {
    @Autowired 
    private TratamientoPacienteRepository repo;

    public List<TratamientoPacienteEntity> obtenerPorPaciente(Long idPaciente) {
        return repo.findByPacienteId(idPaciente);
    }

    public TratamientoPacienteEntity asignarTratamiento(Long pacienteId, Long tratamientoId) {
        TratamientoPacienteEntity tp = new TratamientoPacienteEntity();
        tp.setPaciente(new PacienteEntity(pacienteId));
        tp.setTratamiento(new TratamientoEntity(tratamientoId));
        return repo.save(tp);
    }
}

