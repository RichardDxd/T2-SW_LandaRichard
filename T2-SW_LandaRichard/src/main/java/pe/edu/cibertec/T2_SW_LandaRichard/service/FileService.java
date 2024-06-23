package pe.edu.cibertec.T2_SW_LandaRichard.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FileService implements IFileService {
    private final Path pathFolder = Paths.get("uploads");

    @Override
    public void guardarArchivo(MultipartFile archivo) throws Exception {
        validarArchivo(archivo);
        Files.copy(archivo.getInputStream(), this.pathFolder.resolve(archivo.getOriginalFilename()));
    }

    @Override
    public void guardarArchivos(List<MultipartFile> archivosList) throws Exception {
        for (MultipartFile archivo : archivosList) {
            validarArchivo(archivo);
            Files.copy(archivo.getInputStream(), this.pathFolder.resolve(archivo.getOriginalFilename()));
        }
    }

    private void validarArchivo(MultipartFile archivo) throws Exception {
        String fileName = archivo.getOriginalFilename();
        if (fileName == null || fileName.isEmpty()) {
            throw new Exception("El nombre del archivo no puede estar vacío");
        }

        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (!extension.equals("pdf") && !extension.equals("png") && !extension.equals("docx")) {
            throw new Exception("Extensión de archivo no permitida: " + extension);
        }

        if (archivo.getSize() > 25 * 1024 * 1024) { // 25MB
            throw new Exception("El archivo excede el tamaño máximo permitido de 25MB");
        }
    }
}
