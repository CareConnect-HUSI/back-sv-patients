package co.edu.javeriana.sv_patients.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.edu.javeriana.sv_patients.DTO.PacienteRegistroDTO;
import co.edu.javeriana.sv_patients.Entity.PacienteEntity;
import co.edu.javeriana.sv_patients.Entity.ProcedimientoEntity;
import co.edu.javeriana.sv_patients.Entity.ProcedimientoPacienteEntity;
import co.edu.javeriana.sv_patients.Entity.TratamientoEntity;
import co.edu.javeriana.sv_patients.Entity.TratamientoPacienteEntity;
import co.edu.javeriana.sv_patients.Repository.PacienteRepository;
import co.edu.javeriana.sv_patients.Repository.ProcedimientoPacienteRepository;
import co.edu.javeriana.sv_patients.Repository.ProcedimientoRepository;
import co.edu.javeriana.sv_patients.Repository.TratamientoPacienteRepository;
import co.edu.javeriana.sv_patients.Repository.TratamientoRepository;

@Service
public class PacienteService {
    
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private TratamientoRepository tratamientoRepository;

    @Autowired
    private ProcedimientoRepository procedimientoRepository;

    @Autowired
    private TratamientoPacienteRepository tratamientoPacienteRepository;

    @Autowired
    private ProcedimientoPacienteRepository procedimientoPacienteRepository;

    public Boolean existePaciente(String documento) {
        return pacienteRepository.existsByDocumento(documento);
    }

    public Page<PacienteEntity> obtenerPacientes(Pageable pageable) {
        return pacienteRepository.findAll(pageable);
    }

    public void registrarConTratamientosYProcedimientos(PacienteRegistroDTO dto) {
        PacienteEntity paciente = new PacienteEntity();
        paciente.setNombre(dto.getNombre());
        paciente.setApellido(dto.getApellido());
        paciente.setDocumento(dto.getDocumento());
        paciente.setTipoDocumento(dto.getTipoDocumento());
        paciente.setDireccion(dto.getDireccion());
        paciente.setTelefono(dto.getTelefono());
        paciente.setNombreFamiliar(dto.getNombreFamiliar());
        paciente.setTelefonoFamiliar(dto.getTelefonoFamiliar());
        paciente.setParentescoFamiliar(dto.getParentescoFamiliar());
        paciente.setBarrio(dto.getBarrio());
        paciente.setConjunto(dto.getConjunto());
        paciente.setLatitud(dto.getLatitud());
        paciente.setLongitud(dto.getLongitud());

        pacienteRepository.save(paciente);

        if (dto.getTratamientos() != null) {
            for (TratamientoEntity t : dto.getTratamientos()) {
                tratamientoRepository.save(t);
                TratamientoPacienteEntity tp = new TratamientoPacienteEntity();
                tp.setPaciente(paciente);
                tp.setTratamiento(t);
                tratamientoPacienteRepository.save(tp);
            }
        }

        if (dto.getProcedimientos() != null) {
            for (ProcedimientoEntity p : dto.getProcedimientos()) {
                procedimientoRepository.save(p);
                ProcedimientoPacienteEntity pp = new ProcedimientoPacienteEntity();
                pp.setPaciente(paciente);
                pp.setProcedimiento(p);
                procedimientoPacienteRepository.save(pp);
            }
        }
    }

    public Page<PacienteRegistroDTO> obtenerPacientesConDetalles(Pageable pageable) {
    Page<PacienteEntity> pacientes = pacienteRepository.findAll(pageable);

    return pacientes.map(paciente -> {
        PacienteRegistroDTO dto = new PacienteRegistroDTO();
        dto.setNombre(paciente.getNombre());
        dto.setApellido(paciente.getApellido());
        dto.setDocumento(paciente.getDocumento());
        dto.setTipoDocumento(paciente.getTipoDocumento());
        dto.setDireccion(paciente.getDireccion());
        dto.setTelefono(paciente.getTelefono());
        dto.setNombreFamiliar(paciente.getNombreFamiliar());
        dto.setTelefonoFamiliar(paciente.getTelefonoFamiliar());
        dto.setParentescoFamiliar(paciente.getParentescoFamiliar());
        dto.setBarrio(paciente.getBarrio());
        dto.setConjunto(paciente.getConjunto());
        dto.setLatitud(paciente.getLatitud());
        dto.setLongitud(paciente.getLongitud());

        // Añadir tratamientos
        List<TratamientoPacienteEntity> tpList = tratamientoPacienteRepository.findByPacienteId(paciente.getId());
        dto.setTratamientos(tpList.stream()
            .map(TratamientoPacienteEntity::getTratamiento)
            .collect(Collectors.toList()));

        // Añadir procedimientos
        List<ProcedimientoPacienteEntity> ppList = procedimientoPacienteRepository.findByPacienteId(paciente.getId());
        dto.setProcedimientos(ppList.stream()
            .map(ProcedimientoPacienteEntity::getProcedimiento)
            .collect(Collectors.toList()));

        return dto;
    });
}


}
