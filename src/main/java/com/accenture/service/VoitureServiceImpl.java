package com.accenture.service;

import com.accenture.exception.VoitureException;
import com.accenture.repository.VoitureDao;
import com.accenture.repository.entity.Client;
import com.accenture.repository.entity.Voiture;
import com.accenture.service.dto.VoitureRequestDto;
import com.accenture.service.dto.VoitureResponseDto;
import com.accenture.service.mapper.VoitureMapper;
import org.springframework.stereotype.Service;

@Service
public class VoitureServiceImpl implements VoitureService{
    private final VoitureDao voitureDao;
    private final VoitureMapper voitureMapper;

    public VoitureServiceImpl(VoitureDao voitureDao, VoitureMapper voitureMapper) {
        this.voitureDao = voitureDao;
        this.voitureMapper = voitureMapper;
    }

    @Override
    public VoitureResponseDto ajouterVoiture(VoitureRequestDto voitureRequestDto) throws VoitureException {
        verifierVoiture(voitureRequestDto);
       Voiture voiture = voitureMapper.toVoiture(voitureRequestDto);
        Voiture voitureRetour = voitureDao.save(voiture);

        return voitureMapper.toVoitureResponseDto(voiture);
    }
   private static void verifierVoiture(VoitureRequestDto voitureRequestDto){


   }
}
