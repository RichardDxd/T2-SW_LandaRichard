package pe.edu.cibertec.T2_SW_LandaRichard.service;
import pe.edu.cibertec.T2_SW_LandaRichard.model.bd.Personaje;
import pe.edu.cibertec.T2_SW_LandaRichard.repository.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonajeService {
    @Autowired
    private PersonajeRepository personajeRepository;

    public List<Personaje> listarPersonajes() {
        return personajeRepository.findAll();
    }

    public Optional<Personaje> obtenerPersonajePorId(Integer id) {
        return personajeRepository.findById(id);
    }

    public Personaje guardarPersonaje(Personaje personaje) {
        return personajeRepository.save(personaje);
    }

    public Personaje actualizarPersonaje(Personaje personaje) {
        return personajeRepository.save(personaje);
    }
}
