package co.edu.javeriana.sv_patients.Controller;

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
    public ResponseEntity<ActividadPacienteVisita> obtenerActividadPorId(@PathVariable Long id) {
        ActividadPacienteVisita actividad = actividadPacienteVisitaService.obtenerActividadPacienteVisitaPorId(id);
        if (actividad != null) {
            return ResponseEntity.ok(actividad);
        } else {
            return ResponseEntity.notFound().build();
        }
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

    @GetMapping("/por-documento/{documento}")
public ResponseEntity<?> obtenerActividadesPorDocumento(@PathVariable String documento) {
    Optional<PacienteEntity> pacienteOpt = pacienteRepository.findByNumeroIdentificacion(documento);
    if (pacienteOpt.isPresent()) {
        PacienteEntity paciente = pacienteOpt.get();
        List<ActividadPacienteVisita> actividades = actividadPacienteVisitaRepository.findByPacienteId(paciente.getId());

        List<Map<String, Object>> response = actividades.stream().map(a -> Map.of(
            "nombreActividad", (Object) a.getActividad().getName(),
            "dosis", (Object) a.getDosis(),
            "frecuencia", (Object) a.getFrecuencia(),
            "diasTratamiento", (Object) a.getDiasTratamiento(),
            "pacienteNombre", (Object) (paciente.getNombre() + " " + paciente.getApellido())
        )).toList();

        return ResponseEntity.ok(response);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado");
    }
}

}