package co.edu.javeriana.sv_patients.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.sv_patients.Entity.ProcedimientoPacienteEntity;
import co.edu.javeriana.sv_patients.Service.ProcedimientoService;

@RestController
@RequestMapping("/procedimientos")
public class ProcedimientoController {

    @Autowired
    private ProcedimientoService service;

    //http://localhost:8081/procedimientos/asignar?pacienteId=1&procedimientoId=1
    @PostMapping("/asignar")
    public ResponseEntity<ProcedimientoPacienteEntity> asignar(@RequestParam Long pacienteId, @RequestParam Long procedimientoId) {
        return ResponseEntity.ok(service.asignarProcedimiento(pacienteId, procedimientoId));
    }

    //http://localhost:8081/procedimientos/paciente/1
    @GetMapping("/paciente/{id}")
    public ResponseEntity<List<ProcedimientoPacienteEntity>> porPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorPaciente(id));
    }
}
