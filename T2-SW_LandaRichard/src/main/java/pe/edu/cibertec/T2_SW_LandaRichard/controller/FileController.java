package pe.edu.cibertec.T2_SW_LandaRichard.controller;
import pe.edu.cibertec.T2_SW_LandaRichard.model.dto.Archivodto;
import pe.edu.cibertec.T2_SW_LandaRichard.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/files")
public class FileController {
    private final FileService fileService;

    @PostMapping("/uploadMultiple")
    public ResponseEntity<Archivodto> subirArchivos(
            @RequestParam("files") List<MultipartFile> multipartFileList
    ) {
        try {
            fileService.guardarArchivos(multipartFileList);
            return new ResponseEntity<>(Archivodto.builder()
                    .mensaje("Archivos subidos correctamente").build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Archivodto.builder()
                    .mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/uploadSingle")
    public ResponseEntity<Archivodto> subirArchivo(
            @RequestParam("file") MultipartFile multipartFile
    ) {
        try {
            fileService.guardarArchivo(multipartFile);
            return new ResponseEntity<>(Archivodto.builder()
                    .mensaje("Archivo subido correctamente").build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Archivodto.builder()
                    .mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
