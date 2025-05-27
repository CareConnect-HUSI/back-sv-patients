package co.edu.javeriana.sv_patients.Controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.sv_patients.DTO.MedicamentoDTO;
import co.edu.javeriana.sv_patients.Entity.InstalacionInsumosPacienteEntity;
import co.edu.javeriana.sv_patients.Service.InsumosService;

@RestController
@RequestMapping("/insumos")
public class InsumosController {
    
    @Autowired
    private InsumosService insumosService;

    @Autowired
    private co.edu.javeriana.sv_patients.Repository.PacienteRepository pacienteRepository;

    @Autowired
    private co.edu.javeriana.sv_patients.Repository.ActividadPacienteVisitaRepository actividadRepository;

    //http://localhost:8081/insumos/registrar-instalacion
    @PostMapping("/registrar-instalacion")
    public ResponseEntity<InstalacionInsumosPacienteEntity> registrar(@RequestBody InstalacionInsumosPacienteEntity insumos) {
        InstalacionInsumosPacienteEntity insumosGuardados = insumosService.save(insumos);
        return ResponseEntity.status(HttpStatus.CREATED).body(insumosGuardados);
    }

    // http://localhost:8081/insumos/medicamentos/{documento}
    @GetMapping("/medicamentos/{id}")
    public ResponseEntity<List<MedicamentoDTO>> getMedicamentos(@PathVariable Long id) {
        
        List<InstalacionInsumosPacienteEntity> insumos = insumosService.findAllById(id);
    
    List<MedicamentoDTO> medicamentos = insumos.stream()
        .filter(insumo -> "medicamento".equalsIgnoreCase(insumo.getActividad().getTipoActividad().getName()))
        .map(insumo -> new MedicamentoDTO(
            insumo.getId(),
            insumo.getActividad().getName(),
            insumo.getCantidad_disponible()
        ))
        .collect(Collectors.toList());
    return medicamentos.isEmpty() 
           ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
           : ResponseEntity.ok(medicamentos);
    }

    // http://localhost:8081/insumos/agregar
    // @PostMapping("/agregar")
    // public ResponseEntity<?> agregarMedicamento(@RequestBody MedicamentoRequestDTO request) {
    //     // Validate pacienteId
    //     Optional<PacienteEntity> paciente = pacienteRepository.findById(request.getPacienteId());
    //     if (!paciente.isPresent()) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    //                 .body("Paciente con ID " + request.getPacienteId() + " no encontrado.");
    //     }

    //     // Validate actividadId
    //     Optional<ActividadPacienteVisita> actividad = actividadRepository.findById(request.getActividadId());
    //     if (!actividad.isPresent()) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    //                 .body("Actividad con ID " + request.getActividadId() + " no encontrada.");
    //     }

    //     // Create entity
    //     InstalacionInsumosPacienteEntity insumo = new InstalacionInsumosPacienteEntity();
    //     insumo.setPaciente(paciente.get());
    //     insumo.setActividad_id(request.getActividadId());
    //     LocalDate ahora = LocalDate.now();
    //     insumo.setFecha_instalacion(ahora);
    //     insumo.setCantidad_disponible(request.getCantidad());

    //     // Save to database
    //     InstalacionInsumosPacienteEntity savedInsumo = insumosService.save(insumo);

    //     // Create response DTO
    //     MedicamentoDTO response = new MedicamentoDTO(
    //             savedInsumo.getId(),
    //             savedInsumo.getActividad().getName(),
    //             savedInsumo.getCantidad_disponible()
    //     );

    //     return ResponseEntity.status(HttpStatus.CREATED).body(response);
    // }

    @PostMapping("/agregar")
    public ResponseEntity<?> agregarMedicamento(@RequestBody Map<String, Object> payload) {
        try {
            InstalacionInsumosPacienteEntity insumo = insumosService.crearDesdePayload(payload);
            return ResponseEntity.status(HttpStatus.CREATED).body(insumo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // http://localhost:8081/insumos/inventario-paciente/{pacienteId}
    @GetMapping("/inventario-paciente/{pacienteId}")
    public ResponseEntity<List<Map<String, Object>>> getInventarioConConsumo(@PathVariable Long pacienteId) {
        List<Map<String, Object>> inventario = insumosService.obtenerInventarioConConsumoPorPaciente(pacienteId);
        return ResponseEntity.ok(inventario);
    }   

    // http://localhost:8081/insumos/actualizar/{id}
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<InstalacionInsumosPacienteEntity> actualizarCantidad(
        @PathVariable Long id,
        @RequestParam Integer cantidad
    ) {
        InstalacionInsumosPacienteEntity actualizado = insumosService.actualizarInsumo(id, cantidad);
        return ResponseEntity.ok(actualizado);
    }

}
