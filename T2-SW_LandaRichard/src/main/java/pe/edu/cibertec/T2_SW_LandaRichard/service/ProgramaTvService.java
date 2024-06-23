package pe.edu.cibertec.T2_SW_LandaRichard.service;
import pe.edu.cibertec.T2_SW_LandaRichard.model.bd.ProgramaTv;
import pe.edu.cibertec.T2_SW_LandaRichard.repository.ProgramaTvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramaTvService {

    @Autowired
    private ProgramaTvRepository programaTvRepository;

    public List<ProgramaTv> listarProgramas() {
        return programaTvRepository.findAll();
    }

    public Optional<ProgramaTv> obtenerProgramaPorId(Integer id) {
        return programaTvRepository.findById(id);
    }

    public ProgramaTv guardarPrograma(ProgramaTv programaTv) {
        return programaTvRepository.save(programaTv);
    }

    public ProgramaTv actualizarPrograma(ProgramaTv programaTv) {
        return programaTvRepository.save(programaTv);
    }
}
