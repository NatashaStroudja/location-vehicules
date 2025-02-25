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
import java.util.Optional;

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
        return clientDao.findAll().stream()
                .map(clientMapper::toClientResponseDto)
                .toList();
    }

    @Override
    public ClientResponseDto trouverByEmailEtPassword(String email, String password) throws EntityNotFoundException {
        Optional<Client> optClient = clientDao. findByEmailAndPassword(email, password);
        if (optClient.isEmpty())
            throw new EntityNotFoundException("Ce mail n'existe pas dans notre base de données");
        Client client = optClient.get();
        return clientMapper.toClientResponseDto(client);
    }


    private static void verifierClient(ClientRequestDto clientRequestDto) throws ClientException, EntityNotFoundException {
        String emailRegex =  "^[A-Za-z0-9+_.-]+@(.+)$";
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[&@#-_§]).{8,16}$";
        if (clientRequestDto == null)
            throw new ClientException("Le client est nulle");
        if (clientRequestDto.nom() == null || clientRequestDto.nom().isBlank())
            throw new ClientException("Le nom est obligatoire");
        if (clientRequestDto.prenom() == null || clientRequestDto.prenom().isBlank())
            throw new ClientException("Le prenom est obligatoire");
        if (clientRequestDto.email() == null || clientRequestDto.email().isBlank()|| !clientRequestDto.email().matches(emailRegex))
            throw new ClientException("Le email est obligatoire et doit etre ecrit en format mail");

        if (clientRequestDto.password() == null || clientRequestDto.password().isBlank())
            throw new ClientException("Le mot de passe est obligatoite");

        if  (!clientRequestDto.password().matches(passwordRegex))
            throw new ClientException("Le mot de passe doit faire entre 8 et 16 caractères, doit comporter obligatoirement au minimum une \n" +
                    "majuscule, une minuscule, un chiffre et un caractère parmi la liste suivante : & # @ - _ §");

        if ((clientRequestDto.adresse() == null))
            throw new ClientException("L'adresse est obligatoire");
        if ((clientRequestDto.adresse().rue() == null) || clientRequestDto.adresse().rue().isBlank())
            throw new ClientException("Le nom de la rue est obligatoire");
        if ((clientRequestDto.adresse().codePostal() == null) || clientRequestDto.adresse().codePostal().isBlank())
            throw new ClientException("Le code postale est obligatoire");
        if ((clientRequestDto.adresse().ville() == null) || clientRequestDto.adresse().ville().isBlank())
            throw new ClientException("La ville est obligatoire");

        if (clientRequestDto.dateNaissance() == null)
            throw new ClientException("La date de naissance est obligatoire ");
        if (Period.between(clientRequestDto.dateNaissance(), LocalDate.now()).getYears() < 18)
            throw new ClientException("Vous devez avoir au moin 18 ans, desolé! ");
    }


}
