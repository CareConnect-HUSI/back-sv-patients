package co.edu.javeriana.sv_patients.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import co.edu.javeriana.sv_patients.Entity.ActividadPacienteVisita;
import co.edu.javeriana.sv_patients.Repository.ActividadPacienteVisitaRepository;
import co.edu.javeriana.sv_patients.Repository.ActividadRepository;
import co.edu.javeriana.sv_patients.Repository.PacienteRepository;

@Service
public class ActividadPacienteVisitaService {

    @Autowired
    private ActividadPacienteVisitaRepository actividadPacienteVisitaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ActividadRepository actividadRepository;

    private final ObjectMapper objectMapper = new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    public ActividadPacienteVisita crearDesdePayload(Map<String, Object> payload) {
        ActividadPacienteVisita actividad = objectMapper.convertValue(payload, ActividadPacienteVisita.class);

        Long pacienteId = ((Map<String, Object>) payload.get("paciente")).get("id") instanceof Integer
                ? ((Integer) ((Map<String, Object>) payload.get("paciente")).get("id")).longValue()
                : Long.valueOf(((Map<String, Object>) payload.get("paciente")).get("id").toString());

        Long actividadId = ((Map<String, Object>) payload.get("actividad")).get("id") instanceof Integer
                ? ((Integer) ((Map<String, Object>) payload.get("actividad")).get("id")).longValue()
                : Long.valueOf(((Map<String, Object>) payload.get("actividad")).get("id").toString());

        actividad.setPaciente(pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + pacienteId)));

        actividad.setActividad(actividadRepository.findById(actividadId)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada con ID: " + actividadId)));

        return actividadPacienteVisitaRepository.save(actividad);
    }
    

        public List<Map<String, Object>> obtenerActividadesSoloConIds() {
        List<ActividadPacienteVisita> actividades = actividadPacienteVisitaRepository.findAll();

        return actividades.stream().map(apv -> {
            Map<String, Object> data = new HashMap<>();
            data.put("id", apv.getId());
            data.put("dosis", apv.getDosis());
            data.put("frecuencia", apv.getFrecuencia());
            data.put("diasTratamiento", apv.getDiasTratamiento());
            data.put("duracionVisita", apv.getDuracionVisita());
            data.put("fechaInicio", apv.getFechaInicio());
            data.put("fechaFin", apv.getFechaFin());
            data.put("hora", apv.getHora());
            data.put("pacienteId", apv.getPaciente() != null ? apv.getPaciente().getId() : null);
            data.put("actividadId", apv.getActividad() != null ? apv.getActividad().getId() : null);
            return data;
        }).toList();
    }


    public ActividadPacienteVisita obtenerActividadPacienteVisitaPorId(Long id) {
        return actividadPacienteVisitaRepository.findById(id).orElse(null);
    }

    public List<ActividadPacienteVisita> obtenerTodasLasActividadesPacienteVisita() {
        return actividadPacienteVisitaRepository.findAll();
    }

    public ActividadPacienteVisita actualizarActividadPacienteVisita(Long id, ActividadPacienteVisita actividadPacienteVisitaActualizada) {
        ActividadPacienteVisita actividadPacienteVisita = obtenerActividadPacienteVisitaPorId(id);
        if (actividadPacienteVisita != null) {
            actividadPacienteVisita.setDosis(actividadPacienteVisitaActualizada.getDosis());
            actividadPacienteVisita.setFrecuencia(actividadPacienteVisitaActualizada.getFrecuencia());
            actividadPacienteVisita.setDiasTratamiento(actividadPacienteVisitaActualizada.getDiasTratamiento());
            actividadPacienteVisita.setFechaInicio(actividadPacienteVisitaActualizada.getFechaInicio());
            actividadPacienteVisita.setFechaFin(actividadPacienteVisitaActualizada.getFechaFin());
            actividadPacienteVisita.setHora(actividadPacienteVisitaActualizada.getHora());
            actividadPacienteVisita.setDuracionVisita(actividadPacienteVisitaActualizada.getDuracionVisita());
            return actividadPacienteVisitaRepository.save(actividadPacienteVisita);
        }
        return null;
    }

    public void eliminarActividadPacienteVisita(Long id) {
        actividadPacienteVisitaRepository.deleteById(id);
    }

}