package it.epicode.Gestione_viaggi_aziendali.azienda.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import it.epicode.Gestione_viaggi_aziendali.azienda.dto.DipendenteDTO;
import it.epicode.Gestione_viaggi_aziendali.azienda.entities.Dipendente;
import it.epicode.Gestione_viaggi_aziendali.azienda.exception.BadRequestException;
import it.epicode.Gestione_viaggi_aziendali.azienda.exception.NotFoundException;
import it.epicode.Gestione_viaggi_aziendali.azienda.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private Cloudinary cloudinary;

    public List<Dipendente> findAllDipendenti() {
        return dipendenteRepository.findAll();
    }

    public Dipendente saveDipendente(DipendenteDTO body) {
        if (dipendenteRepository.existsByUsername(body.username()))
            throw new BadRequestException("Username already in use");
        if (dipendenteRepository.existsByEmail(body.email())) throw new BadRequestException("Email already in use");
        return dipendenteRepository.save(new Dipendente(body.username(), body.nome(), body.cognome(), body.email()));
    }

    public Dipendente findDipendenteById(Long dipendenteId) {
        return dipendenteRepository.findById(dipendenteId).orElseThrow(() -> new NotFoundException(dipendenteId, "Dipendente"
        ));
    }

    public Dipendente findDipendenteByIdAndUpdate(Long DipendenteId, DipendenteDTO body) {
        Dipendente searched = findDipendenteById(DipendenteId);
        if (!searched.getCognome().equals(body.cognome()) || !searched.getNome().equals(body.nome())) {
            if (searched.getAvatarUrl().equals("https://ui-avatars.com/api/?name=" + searched.getNome() + "+" + searched.getCognome()))
                searched.setAvatarUrl("https://ui-avatars.com/api/?name=" + body.nome() + "+" + body.cognome());
        }
        if (!body.username().equals(searched.getUsername())) {
            if (dipendenteRepository.existsByUsername(body.username()))
                throw new BadRequestException("Username already in use");
            searched.setUsername(body.username());
        }
        if (!body.email().equals(searched.getEmail())) {
            if (dipendenteRepository.existsByEmail(body.email())) throw new BadRequestException("Email already in use");
            searched.setEmail(body.email());
        }
        searched.setNome(body.nome());
        searched.setCognome(body.cognome());
        return dipendenteRepository.save(searched);
    }

    public void findDipendenteByIdAndDelete(Long dipendenteId) {
        Dipendente searched = findDipendenteById(dipendenteId);
        dipendenteRepository.delete(searched);
    }

    public void uploadAvatar(MultipartFile file, Long dipendenteId) {
        try {
            Dipendente searched = findDipendenteById(dipendenteId);
            String url = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
            searched.setAvatarUrl(url);
            dipendenteRepository.save(searched);
        } catch (IOException e) {
            throw new BadRequestException("File provided not supported");
        }
    }
}
