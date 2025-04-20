package co.edu.javeriana.sv_patients.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.sv_patients.Entity.PacienteEntity;
import co.edu.javeriana.sv_patients.Entity.TipoIdentificacionEntity;
import co.edu.javeriana.sv_patients.Repository.PacienteRepository;
import co.edu.javeriana.sv_patients.Repository.TipoIdentificacionRepository;
import co.edu.javeriana.sv_patients.Service.PacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    
    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private TipoIdentificacionRepository tipoIdentificacionRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    //http://localhost:8081/pacientes/registrar-paciente
    @PostMapping("/registrar-paciente")
    public ResponseEntity<PacienteEntity> registrar(@RequestBody PacienteEntity paciente) {

        if (paciente.getTipoIdentificacion() == null || paciente.getTipoIdentificacion().getName() == null) {
            throw new IllegalArgumentException("El campo 'tipoIdentificacion.nombre' es requerido");
        }

        TipoIdentificacionEntity tipo = tipoIdentificacionRepository.findByName(paciente.getTipoIdentificacion().getName());
        
        if (tipo == null) throw new IllegalArgumentException("Tipo de identificación no válido");

        paciente.setTipoIdentificacion(tipo);

        PacienteEntity pacienteGuardado = pacienteRepository.save(paciente);
        return ResponseEntity.ok(pacienteGuardado);
    }

    //http://localhost:8081/pacientes
    @GetMapping()
    public ResponseEntity<List<PacienteEntity>> obtenerTodosLosPacientes() {
        List<PacienteEntity> pacientes = pacienteRepository.findAll();
        return ResponseEntity.ok(pacientes);
    }

    //http://localhost:8081/pacientes/1
    @GetMapping("/pacientes/{id}")
    public ResponseEntity<?> obtenerPacientePorId(@PathVariable Long id) {
        Optional<PacienteEntity> paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()) {
            return ResponseEntity.ok(paciente.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado con id: " + id);
        }
    }

}

