package com.accenture.service;

import com.accenture.exception.VehiculeException;
import com.accenture.repository.VoitureDao;
import com.accenture.repository.entity.Voiture;
import com.accenture.service.dto.VoitureRequestDto;
import com.accenture.service.dto.VoitureResponseDto;
import com.accenture.service.mapper.VoitureMapper;
import com.accenture.shared.Permis;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoitureServiceImpl implements VoitureService {
    public static final String ID_NON_PRESENT = "Cet id n'existe pas!";
    private final VoitureDao voitureDao;
    private final VoitureMapper voitureMapper;

    public VoitureServiceImpl(VoitureDao voitureDao, VoitureMapper voitureMapper) {
        this.voitureDao = voitureDao;
        this.voitureMapper = voitureMapper;
    }

    @Override
    public VoitureResponseDto ajouterVoiture(VoitureRequestDto voitureRequestDto) throws VehiculeException {
        verifierVoiture(voitureRequestDto);
        Voiture voiture = voitureMapper.toVoiture(voitureRequestDto);
        ajouterPermis(voiture);
        Voiture voitureRetour = voitureDao.save(voiture);

        return voitureMapper.toVoitureResponseDto(voiture);
    }

    @Override
    public List<VoitureResponseDto> trouverToutesVoitures() {
        return voitureDao.findAll().stream()
                .map(voitureMapper::toVoitureResponseDto)
                .toList();
    }

    @Override
    public VoitureResponseDto trouver(int id) throws EntityNotFoundException {
        Optional<Voiture> optVoiture = voitureDao.findById(id);
        if (optVoiture.isEmpty())
            throw new EntityNotFoundException("L'id n'existe pas");
        Voiture voiture = optVoiture.get();
        return voitureMapper.toVoitureResponseDto(voiture);
    }

    @Override
    public void supprimer(int id) throws EntityNotFoundException {
        if (voitureDao.existsById(id))
            voitureDao.deleteById(id);
        else
            throw new EntityNotFoundException(ID_NON_PRESENT);
    }

    @Override
    public VoitureResponseDto modifierPartiellement(int id, VoitureRequestDto voitureRequestDto) throws VehiculeException {
        Optional<Voiture> optVoiture = voitureDao.findById(id);
        if (optVoiture.isEmpty())
            throw new EntityNotFoundException(ID_NON_PRESENT);
        Voiture voitureExist = optVoiture.get();
        Voiture nouvelleVoiture = voitureMapper.toVoiture(voitureRequestDto);
        remplacer(voitureExist, nouvelleVoiture);
        Voiture voitureEnreg = voitureDao.save(voitureExist);
        return voitureMapper.toVoitureResponseDto(voitureEnreg);
    }

    private static void verifierVoiture(VoitureRequestDto voitureRequestDto) throws VehiculeException, EntityNotFoundException {
        if (voitureRequestDto == null)
            throw new VehiculeException("La voiture ne peut pas etre nulle");
        if (voitureRequestDto.marque() == null || voitureRequestDto.marque().isBlank())
            throw new VehiculeException("La marque est obligatoire");
        if (voitureRequestDto.model() == null || voitureRequestDto.model().isBlank())
            throw new VehiculeException("Le model est obligatoire");
        if (voitureRequestDto.couleur() == null || voitureRequestDto.couleur().isBlank())
            throw new VehiculeException("La couleur est obligatoire");
        if (voitureRequestDto.nombreDePlaces() == null)
            throw new VehiculeException("Le nombre des places est obligatoire");
        if (voitureRequestDto.carburantOuEnergie() == null)
            throw new VehiculeException("Le type de carburant ou d'énergie est obligatoire");
        if (voitureRequestDto.nombreDePortes() == null)
            throw new VehiculeException("le nombre des portes est obligatoire");
        if (voitureRequestDto.transmission() == null)
            throw new VehiculeException("La transmission est obligatoire");
        if (voitureRequestDto.clim() == null)
            throw new VehiculeException("La présence de clim doit etre indiquée");
        if (voitureRequestDto.nombreDeBagages() == null)
            throw new VehiculeException("Le nombre des bagages est obligatoire");

    }

    private static void ajouterPermis(Voiture voiture) {
        if (voiture.getNombreDePlaces() >= 1 && voiture.getNombreDePlaces() <= 9)
            voiture.setPermis(Permis.B);
        else if (voiture.getNombreDePlaces() >= 10 && voiture.getNombreDePlaces() <= 16)
            voiture.setPermis(Permis.D1);
    }

    private static void remplacer(Voiture voitureExist, Voiture nouvelleVoiture) {
        if (nouvelleVoiture == null)
            throw new VehiculeException("La voiture ne peut pas etre null");
        if (nouvelleVoiture.getMarque() != null) {
            if (nouvelleVoiture.getMarque().isBlank())
                throw new VehiculeException("Ce champ est obligatoire");
        }
        voitureExist.setMarque(nouvelleVoiture.getMarque());

        if (nouvelleVoiture.getModel() != null) {
            if (nouvelleVoiture.getModel().isBlank())
                throw new VehiculeException("Ce champ est obligatoire");
        }
        voitureExist.setModel(nouvelleVoiture.getModel());

        if (nouvelleVoiture.getCouleur() != null) {
            if (nouvelleVoiture.getCouleur().isBlank())
                throw new VehiculeException("Ce champ est obligatoire");
        }
        voitureExist.setCouleur(nouvelleVoiture.getCouleur());

        if (nouvelleVoiture.getNombreDePlaces() != null) {
            if (nouvelleVoiture.getNombreDePlaces() <= 0 || nouvelleVoiture.getNombreDePlaces() > 16)
                throw new VehiculeException("Le nombre de places doit etre entre 1 et 16");
        }
        voitureExist.setNombreDePlaces(nouvelleVoiture.getNombreDePlaces());

        if (nouvelleVoiture.getCarburantOuEnergie() != null)
            voitureExist.setCarburantOuEnergie(nouvelleVoiture.getCarburantOuEnergie());
        if (nouvelleVoiture.getNombreDePortes() != null)
            voitureExist.setNombreDePortes(nouvelleVoiture.getNombreDePortes());
        if (nouvelleVoiture.getTransmission() != null)
            voitureExist.setTransmission(nouvelleVoiture.getTransmission());
        if (nouvelleVoiture.getClim() != null)
            voitureExist.setClim(nouvelleVoiture.getClim());

        if (nouvelleVoiture.getNombreDeBagages() != null) {
            if (nouvelleVoiture.getNombreDeBagages() < 0)
                throw new VehiculeException("Le nombre doit etre positif");
        }
        voitureExist.setNombreDeBagages(nouvelleVoiture.getNombreDeBagages());

        if (nouvelleVoiture.getTypeVoiture() != null)
            voitureExist.setTypeVoiture(nouvelleVoiture.getTypeVoiture());
    }
}
