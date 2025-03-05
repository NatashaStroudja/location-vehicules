package com.accenture.service;

import com.accenture.exception.VehiculeException;
import com.accenture.repository.LocationDao;
import com.accenture.repository.VehiculeDao;
import com.accenture.repository.entity.Location;
import com.accenture.repository.entity.Moto;
import com.accenture.repository.entity.Vehicule;
import com.accenture.repository.entity.Voiture;
import com.accenture.service.dto.MotoResponseDto;
import com.accenture.service.dto.VehiculeResponseDto;
import com.accenture.service.dto.VoitureResponseDto;
import com.accenture.service.mapper.LocationMapper;
import com.accenture.service.mapper.MotoMapper;
import com.accenture.service.mapper.VoitureMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class VehiculeServiceImpl implements VehiculeService {
    private final VehiculeDao vehiculeDao;
    private final MotoMapper motoMapper;
    private final VoitureMapper voitureMapper;
    private final LocationDao locationDao;
    private final LocationMapper locationMapper;


    public VehiculeServiceImpl(VehiculeDao vehiculeDao, MotoMapper motoMapper, VoitureMapper voitureMapper, LocationDao locationDao, LocationMapper locationMapper) {
        this.vehiculeDao = vehiculeDao;
        this.motoMapper = motoMapper;
        this.voitureMapper = voitureMapper;
        this.locationDao = locationDao;
        this.locationMapper = locationMapper;
    }

    @Override
    public VehiculeResponseDto trouverTous() {
        List<Vehicule> listeFinale = vehiculeDao.findAll();
       return getVehiculeResponseDto(listeFinale);
    }


    @Override
    public VehiculeResponseDto rechercherParDate(LocalDateTime dateDebut, LocalDateTime dateFin) throws VehiculeException{
        List<Location> locations = locationDao.findAll();
        List<Vehicule> vehiculesIndispo = locations.stream()//prendre les veh indispo
                .filter(location -> location.getDateDebut().isBefore(dateFin) &&
                        location.getDateFin().isAfter(dateDebut))
                .map(location -> location.getVehicule())
                .toList();
        List<Vehicule> listeVehiculesComplete= vehiculeDao.findAll();
        listeVehiculesComplete.removeAll(vehiculesIndispo);// on envele les vehicules indispos

        return getVehiculeResponseDto(listeVehiculesComplete);
    }

    private VehiculeResponseDto getVehiculeResponseDto(List<Vehicule> listeVehiculesComplete) {
        List<VoitureResponseDto> voitures = new ArrayList<>();
        List<MotoResponseDto> motos = new ArrayList<>();
        for (Vehicule v : listeVehiculesComplete) {
            if (v instanceof Voiture)
                voitures.add(voitureMapper.toVoitureResponseDto((Voiture) v));
            if (v instanceof Moto)
                motos.add(motoMapper.toMotoResponseDto((Moto) v));
        }
        return new VehiculeResponseDto(voitures, motos);
    }


}