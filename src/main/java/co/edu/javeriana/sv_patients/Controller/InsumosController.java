package co.edu.javeriana.sv_patients.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.sv_patients.Entity.InstalacionInsumosPacienteEntity;
import co.edu.javeriana.sv_patients.Service.InsumosService;

@RestController
@RequestMapping("/insumos")
public class InsumosController {
    
    @Autowired
    private InsumosService insumosService;

    //http://localhost:8081/insumos/registrar-instalacion
    @PostMapping("/registrar-instalacion")
    public ResponseEntity<InstalacionInsumosPacienteEntity> registrar(@RequestBody InstalacionInsumosPacienteEntity insumos) {
        InstalacionInsumosPacienteEntity insumosGuardados = insumosService.save(insumos);
        return ResponseEntity.status(HttpStatus.CREATED).body(insumosGuardados);
    }
}
