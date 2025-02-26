package com.accenture.service.dto;

import com.accenture.shared.*;

import java.util.List;

public record VoirureResponseDtoAccesAdmin(Integer id,
                                           String marque,
                                           String model,
                                           String couleur,
                                           Integer nombreDePlaces,
                                           CarburantOuEnergie carburantOuEnergie,
                                           NombreDePortes nombreDePortes,
                                           Transmission transmission,
                                           Boolean clim,
                                           Integer nombreDeBagages,
                                           TypeVoiture typeVoiture,
                                           Permis permis,
                                           Double tarifDeLocationJour,
                                           Boolean desactive,
                                           Double kilometrage,
                                           Boolean actif,
                                           Boolean retireDuPark
) {
}
