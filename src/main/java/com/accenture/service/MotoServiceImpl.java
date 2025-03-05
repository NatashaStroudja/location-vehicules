package com.accenture.service;

import com.accenture.exception.VehiculeException;
import com.accenture.repository.MotoDao;
import com.accenture.repository.entity.Moto;
import com.accenture.service.dto.MotoRequestDto;
import com.accenture.service.dto.MotoResponseDto;
import com.accenture.service.mapper.MotoMapper;
import com.accenture.shared.Permis;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotoServiceImpl implements MotoService {
    public static final String ID_NON_PRESENT = "Cet id n'existe pas!";
    private final MotoDao motoDao;
    private final MotoMapper motoMapper;

    public MotoServiceImpl(MotoDao motoDao, MotoMapper motoMapper) {
        this.motoDao = motoDao;
        this.motoMapper = motoMapper;
    }

    @Override
    public MotoResponseDto ajouter(MotoRequestDto motoRequestDto) throws VehiculeException {
        verifierMoto(motoRequestDto);
        Moto moto = motoMapper.toMoto(motoRequestDto);
        ajouterPermis(moto);
        Moto motoRetour = motoDao.save(moto);

        return motoMapper.toMotoResponseDto(moto);
    }


    @Override
    public List<MotoResponseDto> trouverToutes() {
        return motoDao.findAll().stream()
                .map(motoMapper::toMotoResponseDto)
                .toList();
    }

    @Override
    public MotoResponseDto trouver(int id) throws EntityNotFoundException {
        Optional<Moto> optMoto = motoDao.findById(id);
        if (optMoto.isEmpty())
            throw new EntityNotFoundException("L'id n'existe pas");
        Moto Moto = optMoto.get();
        return motoMapper.toMotoResponseDto(Moto);
    }

    @Override
    public void supprimer(int id) throws EntityNotFoundException {
        if (motoDao.existsById(id))
            motoDao.deleteById(id);
        else
            throw new EntityNotFoundException(ID_NON_PRESENT);
    }
    @Override
    public MotoResponseDto modifierPartiellement(int id, MotoRequestDto motoRequestDto) throws VehiculeException {
        Optional<Moto> optMoto = motoDao.findById(id);
        if (optMoto.isEmpty())
            throw new EntityNotFoundException(ID_NON_PRESENT);
        Moto motoExist = optMoto.get();
        Moto nouvelleMoto = motoMapper.toMoto(motoRequestDto);
        remplacer(motoExist, nouvelleMoto);
        Moto motoEnreg = motoDao.save(motoExist);
        return motoMapper.toMotoResponseDto(motoEnreg);
    }
    private static void ajouterPermis(Moto moto) {
        if ( moto.getCylindree() <= 125 && moto.getPuissance() <= 11.00)
            moto.setPermis(Permis.A1);
        else
            if (moto.getPuissance() <=  35.00)
                moto.setPermis(Permis.A2);
            else
                moto.setPermis(Permis.A);
    }
    private static void verifierMoto(MotoRequestDto motoRequestDto) throws VehiculeException, EntityNotFoundException {
        if (motoRequestDto == null)
            throw new VehiculeException("La moto ne peut pas etre nulle");
        if (motoRequestDto.marque() == null || motoRequestDto.marque().isBlank())
            throw new VehiculeException("La marque est obligatoire");
        if (motoRequestDto.model() == null || motoRequestDto.model().isBlank())
            throw new VehiculeException("Le model est obligatoire");
        if (motoRequestDto.couleur() == null || motoRequestDto.couleur().isBlank())
            throw new VehiculeException("La couleur est obligatoire");
        if (motoRequestDto.nombreDeCylindres() == null)
            throw new VehiculeException("Le nombre de cylindrée est obligatoire");
        if (motoRequestDto.cylindree() == null)
            throw new VehiculeException("La cylindrée est obligatoire");
        if (motoRequestDto.poids() == null)
            throw new VehiculeException("Le poids est obligatoire");
        if (motoRequestDto.puissance() == null)
            throw new VehiculeException("La puissance est obligatoire");
        if (motoRequestDto.hauteurDeSelle() == null)
            throw new VehiculeException("La hauteur de selle est obligatoire");
        if (motoRequestDto.transmission() == null)
            throw new VehiculeException("La transmission est obligatoire");
    }
    private static void remplacer(Moto motoExist, Moto nouvelleMoto) {
        if (nouvelleMoto == null)
            throw new VehiculeException("La moto ne peut pas etre null");
        if (nouvelleMoto.getMarque() != null) {
            if (nouvelleMoto.getMarque().isBlank())
                throw new VehiculeException("Ce champ est obligatoire");
        }
        motoExist.setMarque(nouvelleMoto.getMarque());

        if (nouvelleMoto.getModel() != null) {
            if (nouvelleMoto.getModel().isBlank())
                throw new VehiculeException("Ce champ est obligatoire");
        }
        motoExist.setModel(nouvelleMoto.getModel());

        if (nouvelleMoto.getCouleur() != null) {
            if (nouvelleMoto.getCouleur().isBlank())
                throw new VehiculeException("Ce champ est obligatoire");
        }
        motoExist.setCouleur(nouvelleMoto.getCouleur());
        if (nouvelleMoto.getNombreDeCylindres() != null)
            motoExist.setNombreDeCylindres(nouvelleMoto.getNombreDeCylindres());
        if (nouvelleMoto.getCylindree() != null)
            motoExist.setCylindree(nouvelleMoto.getCylindree());
        if (nouvelleMoto.getPoids() != null)
            motoExist.setPoids(nouvelleMoto.getPoids());
        if (nouvelleMoto.getTransmission() != null)
            motoExist.setTransmission(nouvelleMoto.getTransmission());
        if (nouvelleMoto.getPuissance() != null)
            motoExist.setPuissance(nouvelleMoto.getPuissance());
        if (nouvelleMoto.getHauteurDeSelle() != null)
            motoExist.setHauteurDeSelle(nouvelleMoto.getHauteurDeSelle());
    }
}