package pe.edu.cibertec.T2_SW_LandaRichard.controller;
import pe.edu.cibertec.T2_SW_LandaRichard.model.bd.Personaje;
import pe.edu.cibertec.T2_SW_LandaRichard.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/personajes")
public class PersonajeController {
    @Autowired
    private PersonajeService personajeService;

    @GetMapping("")
    public ResponseEntity<List<Personaje>> listarPersonajes() {
        List<Personaje> personajes = personajeService.listarPersonajes();
        return new ResponseEntity<>(personajes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personaje> obtenerPersonajePorId(@PathVariable Integer id) {
        Optional<Personaje> personaje = personajeService.obtenerPersonajePorId(id);
        return personaje.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<Personaje> guardarPersonaje(@RequestBody Personaje personaje) {
        Personaje nuevoPersonaje = personajeService.guardarPersonaje(personaje);
        return new ResponseEntity<>(nuevoPersonaje, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personaje> actualizarPersonaje(@PathVariable Integer id, @RequestBody Personaje personaje) {
        Optional<Personaje> optionalPersonaje = personajeService.obtenerPersonajePorId(id);
        if (optionalPersonaje.isPresent()) {
            Personaje personajeExistente = optionalPersonaje.get();
            personajeExistente.setNomPersonaje(personaje.getNomPersonaje());
            personajeExistente.setApePersonaje(personaje.getApePersonaje());
            personajeExistente.setFechNacPersonaje(personaje.getFechNacPersonaje());
            Personaje personajeActualizado = personajeService.actualizarPersonaje(personajeExistente);
            return new ResponseEntity<>(personajeActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
