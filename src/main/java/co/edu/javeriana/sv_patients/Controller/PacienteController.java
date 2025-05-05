package co.edu.javeriana.sv_patients.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.client.RestTemplate;

import co.edu.javeriana.sv_patients.DTO.ActividadDTO;
import co.edu.javeriana.sv_patients.DTO.PacienteDTO;
import co.edu.javeriana.sv_patients.DTO.TipoIdentificacionDTO;
import co.edu.javeriana.sv_patients.Entity.ActividadEntity;
import co.edu.javeriana.sv_patients.Entity.ActividadPacienteVisita;
import co.edu.javeriana.sv_patients.Entity.PacienteEntity;
import co.edu.javeriana.sv_patients.Entity.TipoIdentificacionEntity;
import co.edu.javeriana.sv_patients.Repository.ActividadPacienteVisitaRepository;
import co.edu.javeriana.sv_patients.Repository.ActividadRepository;
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

    @Autowired
    private ActividadRepository actividadRepository;

    @Autowired
    private ActividadPacienteVisitaRepository actividadPacienteVisitaRepository;

    // http://localhost:8081/pacientes/registrar-paciente
    @PostMapping("/registrar-paciente")
    public ResponseEntity<?> registrar(@RequestBody PacienteEntity paciente) {
        if (paciente.getTipoIdentificacion() == null || paciente.getTipoIdentificacion().getId() == null) {
            return ResponseEntity.badRequest().body("El campo 'tipoIdentificacion.id' es requerido");
        }

        TipoIdentificacionEntity tipo = tipoIdentificacionRepository.findById(paciente.getTipoIdentificacion().getId())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de identificación no válido"));
        paciente.setTipoIdentificacion(tipo);

        boolean yaExiste = pacienteRepository.existsByTipoIdentificacionAndNumeroIdentificacion(
                tipo, paciente.getNumero_identificacion());
        if (yaExiste) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ya existe un paciente con ese número y tipo de identificación");
        }

        // Enviar la solicitud HTTP al servicio de geocodificación
        try {
            RestTemplate restTemplate = new RestTemplate();

            // Crear el JSON de la solicitud
            Map<String, String> geocodeRequest = Map.of(
                    "direccion", paciente.getDireccion(),
                    "conjunto", paciente.getConjunto(),
                    "barrio", paciente.getBarrio(),
                    "ciudad", "Bogotá");

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            HttpEntity<Map<String, String>> entity = new HttpEntity<>(geocodeRequest, headers);

            // Hacer la solicitud POST a la URL geocode
            ResponseEntity<Map> response = restTemplate.exchange(
                    "http://0.0.0.0:8001/geocode", HttpMethod.POST, entity, Map.class);

            // Verificar si la respuesta tiene latitud y longitud
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                Map<String, Object> responseBody = response.getBody();
                Double latitud = (Double) responseBody.get("latitud");
                Double longitud = (Double) responseBody.get("longitud");

                // Actualizar la entidad enfermera con los valores de latitud y longitud
                paciente.setLatitud(latitud);
                paciente.setLongitud(longitud);
            } else {
                throw new IllegalArgumentException("No se pudo obtener latitud y longitud de la geocodificación.");
            }
        } catch (Exception e) {
            // Manejo de excepciones si la solicitud falla
            System.err.println("Error al hacer la solicitud de geocodificación: " + e.getMessage());
            throw new IllegalArgumentException("Error al obtener latitud y longitud.");
        }

        PacienteEntity pacienteGuardado = pacienteRepository.save(paciente);

        if (paciente.getActividades() != null) {
            for (ActividadPacienteVisita actividad : paciente.getActividades()) {
                actividad.setPaciente(pacienteGuardado);

                ActividadEntity actividadEntity = actividadRepository.findById(
                        actividad.getActividad().getId()
                ).orElseThrow(()
                        -> new RuntimeException("Actividad con ID " + actividad.getActividad().getId() + " no encontrada")
                );

                actividad.setActividad(actividadEntity);

                actividadPacienteVisitaRepository.save(actividad);
            }
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteGuardado);
    }

    //http://localhost:8081/pacientes
    @GetMapping
    public ResponseEntity<Page<PacienteEntity>> obtenerPacientesPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PacienteEntity> pacientes = pacienteRepository.findAll(pageable);
        return ResponseEntity.ok(pacientes);
    }

    //http://localhost:8081/pacientes/1
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPacientePorId(@PathVariable Long id) {
        Optional<PacienteEntity> pacienteOpt = pacienteRepository.findById(id);
        if (pacienteOpt.isPresent()) {
            PacienteEntity paciente = pacienteOpt.get();
            paciente.getActividades().size();

            PacienteDTO pacienteDTO = new PacienteDTO();
            pacienteDTO.setId(paciente.getId());
            pacienteDTO.setNombre(paciente.getNombre());
            pacienteDTO.setApellido(paciente.getApellido());
            pacienteDTO.setDireccion(paciente.getDireccion());
            pacienteDTO.setTelefono(paciente.getTelefono());
            pacienteDTO.setNumeroIdentificacion(paciente.getNumero_identificacion());
            pacienteDTO.setNombreAcudiente(paciente.getNombre_acudiente());
            pacienteDTO.setTelefonoAcudiente(paciente.getTelefono_acudiente());
            pacienteDTO.setTelefonoAcudiente2(paciente.getTelefono_acudiente2());
            pacienteDTO.setBarrio(paciente.getBarrio());
            pacienteDTO.setConjunto(paciente.getConjunto());
            pacienteDTO.setLocalidad(paciente.getLocalidad());
            pacienteDTO.setLatitud(paciente.getLatitud());
            pacienteDTO.setLongitud(paciente.getLongitud());
            pacienteDTO.setEstado(paciente.getEstado());

            TipoIdentificacionDTO tipoDTO = new TipoIdentificacionDTO();
            tipoDTO.setId(paciente.getTipoIdentificacion().getId());
            tipoDTO.setName(paciente.getTipoIdentificacion().getName());
            pacienteDTO.setTipoIdentificacion(tipoDTO);

            List<ActividadDTO> actividades = paciente.getActividades().stream().map(actividad -> {
                ActividadDTO act = new ActividadDTO();
                act.setNombreActividad(actividad.getActividad().getName());
                act.setDosis(actividad.getDosis());
                act.setFrecuencia(actividad.getFrecuencia());
                act.setDiasTratamiento(actividad.getDiasTratamiento());
                act.setFechaInicio(actividad.getFechaInicio());
                act.setFechaFin(actividad.getFechaFin());
                act.setHora(actividad.getHora());
                return act;
            }).toList();

            pacienteDTO.setActividades(actividades);

            return ResponseEntity.ok(pacienteDTO);

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado con id: " + id);
        }
    }

    //http://localhost:8081/pacientes/actualizar-paciente/1
    @PutMapping("/actualizar-paciente/{id}")
    public ResponseEntity<?> actualizarPaciente(@PathVariable Long id, @RequestBody PacienteEntity paciente) {
        Optional<PacienteEntity> pacienteOpt = pacienteRepository.findById(id);
        if (pacienteOpt.isPresent()) {
            PacienteEntity pacienteExistente = pacienteOpt.get();

            pacienteExistente.setNombre(paciente.getNombre());
            pacienteExistente.setApellido(paciente.getApellido());
            pacienteExistente.setDireccion(paciente.getDireccion());
            pacienteExistente.setTelefono(paciente.getTelefono());
            pacienteExistente.setNumero_identificacion(paciente.getNumero_identificacion());
            pacienteExistente.setNombre_acudiente(paciente.getNombre_acudiente());
            pacienteExistente.setTelefono_acudiente(paciente.getTelefono_acudiente());
            pacienteExistente.setTelefono_acudiente2(paciente.getTelefono_acudiente2());
            pacienteExistente.setBarrio(paciente.getBarrio());
            pacienteExistente.setConjunto(paciente.getConjunto());
            pacienteExistente.setLocalidad(paciente.getLocalidad());
            pacienteExistente.setLatitud(paciente.getLatitud());
            pacienteExistente.setLongitud(paciente.getLongitud());
            pacienteExistente.setEstado(paciente.getEstado());

            if (paciente.getTipoIdentificacion() != null && paciente.getTipoIdentificacion().getId() != null) {
                TipoIdentificacionEntity tipo = tipoIdentificacionRepository.findById(
                        paciente.getTipoIdentificacion().getId()
                ).orElseThrow(() -> new IllegalArgumentException("Tipo de identificación no válido"));

                pacienteExistente.setTipoIdentificacion(tipo);
            }

            PacienteEntity pacienteActualizado = pacienteRepository.save(pacienteExistente);
            return ResponseEntity.ok(pacienteActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado con id: " + id);
        }
    }

    @GetMapping("/tipos-identificacion")
    public ResponseEntity<List<TipoIdentificacionDTO>> obtenerTiposIdentificacion() {
        List<TipoIdentificacionEntity> tipos = tipoIdentificacionRepository.findAll();
        List<TipoIdentificacionDTO> tiposDTO = tipos.stream()
                .map(t -> new TipoIdentificacionDTO(t.getId(), t.getName()))
                .toList();
        return ResponseEntity.ok(tiposDTO);
    }

}
