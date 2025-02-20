package com.accenture.service;

import com.accenture.exception.ClientException;
import com.accenture.repository.ClientDao;
import com.accenture.repository.entity.Client;
import com.accenture.service.dto.ClientRequestDto;
import com.accenture.service.dto.ClientResponseDto;
import com.accenture.service.mapper.ClientMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientDao clientDao;
    private final ClientMapper clientMapper;

    public ClientServiceImpl(ClientDao clientDao, ClientMapper clientMapper) {
        this.clientDao = clientDao;
        this.clientMapper = clientMapper;
    }

    @Override
    public ClientResponseDto ajouterClient(ClientRequestDto clientRequestDto) throws ClientException {
        verifierClient(clientRequestDto);
        Client client = clientMapper.toClient(clientRequestDto);
        Client clientRetour = clientDao.save(client);

        return clientMapper.toClientResponseDto(client);
    }

    @Override
    public List<ClientResponseDto> trouverTousClients() {
        return List.of();
    }

    private static void verifierClient(ClientRequestDto clientRequestDto) throws ClientException, EntityNotFoundException {
        if (clientRequestDto == null)
            throw new ClientException("Le client est nulle");
        if (clientRequestDto.nom() == null || clientRequestDto.nom().isBlank())
            throw new ClientException("Le nom est obligatoire");
        if (clientRequestDto.prenom() == null || clientRequestDto.prenom().isBlank())
            throw new ClientException("Le prenom est obligatoire");
        if (clientRequestDto.email() == null || clientRequestDto.email().isBlank() )
            throw new ClientException("Le email est obligatoire");
        if (clientRequestDto.password() == null || clientRequestDto.password().isBlank() )
            throw new ClientException("Le mot de passe est obligatoite");
        if ((clientRequestDto.adresse() == null))
            throw new ClientException("L'adresse est obligatoire");
        if ((clientRequestDto.adresse().getRue() == null) || clientRequestDto.adresse().getRue().isBlank())
            throw new ClientException("Le nom de la rue est obligatoire");
        if ((clientRequestDto.adresse().getCodePostal() == null) || clientRequestDto.adresse().getCodePostal().isBlank())
            throw new ClientException("Le code postale est obligatoire");
        if ((clientRequestDto.adresse().getVille() == null) || clientRequestDto.adresse().getVille().isBlank())
            throw new ClientException("La ville est obligatoire");

        if (clientRequestDto.dateNaissance() == null || Period.between( clientRequestDto.dateNaissance(), LocalDate.now()).getYears() < 18)
            throw new ClientException("La date de naissance est obligatoire et le client doit etre majeur");



    }


}
}