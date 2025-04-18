package it.epicode.Gestione_viaggi_aziendali.azienda.service;

import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String uploadFile(MultipartFile file) throws IOException {
        // Verifica che la cartella di destinazione esista, altrimenti la crea
        Path path = Paths.get(uploadDir);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        // Nome del file (aggiunge timestamp per evitare conflitti)
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        // Percorso finale del file
        Path targetLocation = path.resolve(filename);

        // Salva il file
        Files.copy(file.getInputStream(), targetLocation);

        return targetLocation.toString();
    }
}
