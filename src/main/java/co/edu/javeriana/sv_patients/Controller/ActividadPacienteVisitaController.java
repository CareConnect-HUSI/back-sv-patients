package co.edu.javeriana.sv_patients.Controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.sv_patients.Entity.ActividadPacienteVisita;

import co.edu.javeriana.sv_patients.Entity.PacienteEntity;
import co.edu.javeriana.sv_patients.Repository.ActividadPacienteVisitaRepository;
import co.edu.javeriana.sv_patients.Repository.PacienteRepository;

import co.edu.javeriana.sv_patients.Service.ActividadPacienteVisitaService;

@RestController
@RequestMapping("/actividad-paciente-visita")
public class ActividadPacienteVisitaController {
    @Autowired
    private ActividadPacienteVisitaService actividadPacienteVisitaService;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ActividadPacienteVisitaRepository actividadPacienteVisitaRepository;

    //http://localhost:8081/actividad-paciente-visita/registrar
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarActividad(@RequestBody Map<String, Object> payload) {
        try {
            ActividadPacienteVisita actividad = actividadPacienteVisitaService.crearDesdePayload(payload);
            return ResponseEntity.status(HttpStatus.CREATED).body(actividad);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //http://localhost:8081/actividad-paciente-visita/listar
    @GetMapping("/listar")
    public ResponseEntity<List<Map<String, Object>>> listarActividadesSoloIds() {
        List<Map<String, Object>> actividades = actividadPacienteVisitaService.obtenerActividadesSoloConIds();
        return ResponseEntity.ok(actividades);
    }


    //http://localhost:8081/actividad-paciente-visita/listar/1
    @GetMapping("/listar/{id}")

public ResponseEntity<?> obtenerActividadPorId(@PathVariable Long id) {
    ActividadPacienteVisita actividad = actividadPacienteVisitaService.obtenerActividadPacienteVisitaPorId(id);
    if (actividad == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Actividad no encontrada con id: " + id);
    }

    Map<String, Object> actividadMap = new HashMap<>();
    actividadMap.put("id", actividad.getId());
    actividadMap.put("dosis", actividad.getDosis());
    actividadMap.put("frecuencia", actividad.getFrecuencia());
    actividadMap.put("diasTratamiento", actividad.getDiasTratamiento());
    actividadMap.put("fechaInicio", actividad.getFechaInicio());
    actividadMap.put("fechaFin", actividad.getFechaFin());
    actividadMap.put("hora", actividad.getHora());
    actividadMap.put("duracionVisita", actividad.getDuracionVisita());

    if (actividad.getActividad() != null) {
        Map<String, Object> actividadInfo = new HashMap<>();
        actividadInfo.put("id", actividad.getActividad().getId());
        actividadInfo.put("name", actividad.getActividad().getName());
        actividadInfo.put("estado", actividad.getActividad().getEstado());
        actividadInfo.put("descripcion", actividad.getActividad().getDescripcion());
        actividadInfo.put("cantidad", actividad.getActividad().getCantidad());
        actividadInfo.put("fechaRegistro", actividad.getActividad().getFechaRegistro());
        actividadInfo.put("usuarioRegistra", actividad.getActividad().getUsuarioRegistra());

        if (actividad.getActividad().getTipoActividad() != null) {
            Map<String, Object> tipoActividadMap = new HashMap<>();
            tipoActividadMap.put("id", actividad.getActividad().getTipoActividad().getId());
            tipoActividadMap.put("name", actividad.getActividad().getTipoActividad().getName());
            actividadInfo.put("tipoActividad", tipoActividadMap);
        }

        actividadMap.put("actividad", actividadInfo);
    }

    return ResponseEntity.ok(actividadMap);
}



    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ActividadPacienteVisita> actualizarActividad(@PathVariable Long id, @RequestBody ActividadPacienteVisita actividadPacienteVisitaActualizada) {
        ActividadPacienteVisita actividadActualizada = actividadPacienteVisitaService.actualizarActividadPacienteVisita(id, actividadPacienteVisitaActualizada);
        if (actividadActualizada != null) {
            return ResponseEntity.ok(actividadActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarActividad(@PathVariable Long id) {
        actividadPacienteVisitaService.eliminarActividadPacienteVisita(id);
        return ResponseEntity.noContent().build();
    }


    //http://localhost:8081/actividad-paciente-visita/por-documento/123456789
    @GetMapping("/por-documento/{documento}")
public ResponseEntity<?> obtenerActividadesPorDocumento(@PathVariable String documento) {
    Optional<PacienteEntity> pacienteOpt = pacienteRepository.findByNumeroIdentificacion(documento);
    if (pacienteOpt.isPresent()) {
        PacienteEntity paciente = pacienteOpt.get();
        List<ActividadPacienteVisita> actividades = actividadPacienteVisitaRepository.findByPacienteId(paciente.getId());

        List<Map<String, Object>> response = actividades.stream().map(a -> Map.of(
            "idRelacion", a.getId(),
            "nombreActividad", (Object) a.getActividad().getName(),
            "dosis", (Object) a.getDosis(),
            "frecuencia", (Object) a.getFrecuencia(),
            "diasTratamiento", (Object) a.getDiasTratamiento(),
            "pacienteNombre", (Object) (paciente.getNombre() + " " + paciente.getApellido()),
            "tipoActividadId", (Object) a.getActividad().getTipoActividad().getId(),
            "duracionVisita", (Object) a.getDuracionVisita()
        )).toList();

        return ResponseEntity.ok(response);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado");
    }
}

}

