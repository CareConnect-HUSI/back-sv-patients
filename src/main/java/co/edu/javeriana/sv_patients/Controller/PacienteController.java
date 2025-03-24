package co.edu.javeriana.sv_patients.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.sv_patients.Entity.PacienteEntity;
import co.edu.javeriana.sv_patients.Service.PacienteService;


@RestController
@RequestMapping("/paciente")
public class PacienteController {
    
    @Autowired
    private PacienteService pacienteService;

    //http://localhost:8080/paciente/registrar-paciente
    @PostMapping("/registrar-paciente")
    public ResponseEntity<?> registrarPaciente(@RequestBody PacienteEntity paciente){
        if(pacienteService.existePaciente(paciente.getDocumento())){
            return ResponseEntity.badRequest().body("El paciente ya existe");
        }
        pacienteService.registrarPaciente(paciente);
        return ResponseEntity.ok("Paciente registrado");
    }
    
    //http://localhost:8080/paciente?limit=10&page=0
    @GetMapping("")
    public ResponseEntity<?> obtenerPacientes(@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "0") int page)  {
    try {
            Pageable pageable = PageRequest.of(page, limit);
            Page<PacienteEntity> patientsPage = pacienteService.obtenerPacientes(pageable);

            return ResponseEntity.ok(Map.of(
                "content", patientsPage.getContent(),
                "totalElements", patientsPage.getTotalElements()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }
}
