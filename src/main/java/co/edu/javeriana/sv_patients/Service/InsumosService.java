package co.edu.javeriana.sv_patients.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.javeriana.sv_patients.Entity.ActividadEntity;
import co.edu.javeriana.sv_patients.Entity.InstalacionInsumosPacienteEntity;
import co.edu.javeriana.sv_patients.Entity.PacienteEntity;
import co.edu.javeriana.sv_patients.Repository.ActividadRepository;
import co.edu.javeriana.sv_patients.Repository.InstalacionInsumosPacienteRepository;
import co.edu.javeriana.sv_patients.Repository.InsumosConsumidosRepository;
import co.edu.javeriana.sv_patients.Repository.PacienteRepository;

@Service
public class InsumosService {
    @Autowired
    private InstalacionInsumosPacienteRepository insumosRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ActividadRepository actividadRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InsumosConsumidosRepository insumosConsumidosRepository;

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

    public List<InstalacionInsumosPacienteEntity> findAllById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Documento cannot be null");
        }

        System.out.println("ID: " + id);
        System.out.println("Insumos: " + insumosRepository.findByPacienteId(id));

        Optional<PacienteEntity> pacienteOpt = pacienteRepository.findById(id);
        if (pacienteOpt.isEmpty()) {
            return List.of(); // Return empty list if patient not found
        }

        System.out.println("Paciente: " + pacienteOpt.get());
        System.out.println("Insumos: " + insumosRepository.findByPacienteId(pacienteOpt.get().getId()));

        return insumosRepository.findByPacienteId(pacienteOpt.get().getId());
    }

    public InstalacionInsumosPacienteEntity crearDesdePayload(Map<String, Object> payload) {
        InstalacionInsumosPacienteEntity insumo = objectMapper.convertValue(payload, InstalacionInsumosPacienteEntity.class);

        // Extract and validate pacienteId
        Long pacienteId = payload.get("pacienteId") instanceof Integer
                ? ((Integer) payload.get("pacienteId")).longValue()
                : Long.valueOf(payload.get("pacienteId").toString());

        // Extract and validate actividadId
        Long actividadId = payload.get("actividadId") instanceof Integer
                ? ((Integer) payload.get("actividadId")).longValue()
                : Long.valueOf(payload.get("actividadId").toString());

        // Validate and set paciente
        PacienteEntity paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + pacienteId));
        insumo.setPaciente(paciente);
        insumo.setPaciente_id(pacienteId); // Explicitly set paciente_id

        // Validate and set actividad
        ActividadEntity actividad = actividadRepository.findById(actividadId)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada con ID: " + actividadId));
        insumo.setActividad_id(actividadId);

        // Set additional fields
        insumo.setFecha_instalacion(LocalDate.now());
        if (payload.get("cantidad") != null) {
            insumo.setCantidad_disponible(payload.get("cantidad") instanceof Integer
                    ? (Integer) payload.get("cantidad")
                    : Integer.parseInt(payload.get("cantidad").toString()));
        }

        return insumosRepository.save(insumo);
    }

    //Obtener los insumos consumidos por un paciente (actividad paciente visita)
    public List<Map<String, Object>> obtenerInventarioConConsumoPorPaciente(Long pacienteId) {
        List<InstalacionInsumosPacienteEntity> instalaciones = insumosRepository.findByPacienteId(pacienteId);
        
        List<Map<String, Object>> resultado = new ArrayList<>();

        for (InstalacionInsumosPacienteEntity insumo : instalaciones) {
            Long idInstalacion = insumo.getId();
            Long cantidadInstalada = insumo.getCantidad_disponible() != null ? insumo.getCantidad_disponible().longValue() : 0L;
            Long cantidadUsada = insumosConsumidosRepository.cantidadConsumidaPorInstalacion(idInstalacion);
            if (cantidadUsada == null) {
                cantidadUsada = 0L;
            }

            Map<String, Object> item = new HashMap<>();
            item.put("id", insumo.getId());
            item.put("nombre", insumo.getActividad().getName());
            item.put("cantidadTotal", cantidadInstalada);
            item.put("cantidadUsada", cantidadUsada);
            item.put("cantidadDisponible", cantidadInstalada - cantidadUsada);

            resultado.add(item);
        }

        return resultado;
    }

    public InstalacionInsumosPacienteEntity actualizarInsumo(Long id, Integer nuevaCantidad) {
        InstalacionInsumosPacienteEntity insumo = insumosRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Insumo no encontrado con ID: " + id));

        insumo.setCantidad_disponible(nuevaCantidad);

        return insumosRepository.save(insumo);
    }
}