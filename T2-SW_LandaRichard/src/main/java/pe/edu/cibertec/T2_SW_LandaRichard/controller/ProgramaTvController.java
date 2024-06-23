package pe.edu.cibertec.T2_SW_LandaRichard.controller;

import pe.edu.cibertec.T2_SW_LandaRichard.model.bd.ProgramaTv;
import pe.edu.cibertec.T2_SW_LandaRichard.service.ProgramaTvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/programas")
public class ProgramaTvController {
    @Autowired
    private ProgramaTvService programaTvService;

    @GetMapping("")
    public ResponseEntity<List<ProgramaTv>> listarProgramas() {
        List<ProgramaTv> programas = programaTvService.listarProgramas();
        return new ResponseEntity<>(programas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramaTv> obtenerProgramaPorId(@PathVariable Integer id) {
        Optional<ProgramaTv> programa = programaTvService.obtenerProgramaPorId(id);
        return programa.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<ProgramaTv> guardarPrograma(@RequestBody ProgramaTv programaTv) {
        ProgramaTv nuevoPrograma = programaTvService.guardarPrograma(programaTv);
        return new ResponseEntity<>(nuevoPrograma, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgramaTv> actualizarPrograma(@PathVariable Integer id, @RequestBody ProgramaTv programaTv) {
        Optional<ProgramaTv> optionalPrograma = programaTvService.obtenerProgramaPorId(id);
        if (optionalPrograma.isPresent()) {
            ProgramaTv programaExistente = optionalPrograma.get();
            programaExistente.setTitulo(programaTv.getTitulo());
            programaExistente.setResumen(programaTv.getResumen());
            programaExistente.setFechaInicio(programaTv.getFechaInicio());
            programaExistente.setPersonaje(programaTv.getPersonaje());
            ProgramaTv programaActualizado = programaTvService.actualizarPrograma(programaExistente);
            return new ResponseEntity<>(programaActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
