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

import co.edu.javeriana.sv_patients.Entity.TratamientoPacienteEntity;
import co.edu.javeriana.sv_patients.Service.TratamientoService;

@RestController
@RequestMapping("/tratamientos")
public class TratamientoController {

    @Autowired 
    private TratamientoService service;

    //http://localhost:8081/tratamientos/asignar?pacienteId=1&tratamientoId=1
    @PostMapping("/asignar")
    public ResponseEntity<TratamientoPacienteEntity> asignar(@RequestParam Long pacienteId, @RequestParam Long tratamientoId) {
        return ResponseEntity.ok(service.asignarTratamiento(pacienteId, tratamientoId));
    }

    //http://localhost:8081/tratamientos/paciente/1
    @GetMapping("/paciente/{id}")
    public ResponseEntity<List<TratamientoPacienteEntity>> porPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorPaciente(id));
    }
}

