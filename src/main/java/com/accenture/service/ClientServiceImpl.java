package com.accenture.service;

import com.accenture.exception.ClientException;
import com.accenture.repository.ClientDao;
import com.accenture.repository.entity.Client;
import com.accenture.service.dto.AdresseRequestDto;
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
    public static final String ID_NON_PRESENT = "Cet id n'existe pas!";
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
        Optional<Client> optClient = clientDao.findByEmailAndPassword(email, password);
        if (optClient.isEmpty())
            throw new EntityNotFoundException("Ce mail n'existe pas dans notre base de données");
        Client client = optClient.get();
        return clientMapper.toClientResponseDto(client);
    }
    @Override
    public void supprimer(int id) throws EntityNotFoundException {
        if (clientDao.existsById(id))
            clientDao.deleteById(id);
        else
            throw new EntityNotFoundException(ID_NON_PRESENT);
    }

    @Override
    public ClientResponseDto modifierPartiellement(int id, ClientRequestDto clientRequestDto) throws ClientException {
        Optional<Client> optClient = clientDao.findById(id);
        if (optClient.isEmpty())
            throw new EntityNotFoundException(ID_NON_PRESENT);
        Client clientExist = optClient.get();//on prend le client trouve
        Client nouveauClient = clientMapper.toClient(clientRequestDto);//on cree le nouveau clientRequestDto et on le transforme en nouveau client
        remplacer(clientExist, nouveauClient);
        Client clientEnreg = clientDao.save(clientExist);//on enregistre ce client
        return clientMapper.toClientResponseDto(clientEnreg);// on transforme le client enregistre en clientResponseDto
    }



    private static void verifierClient(ClientRequestDto clientRequestDto) throws ClientException, EntityNotFoundException {
        if (clientRequestDto == null)
            throw new ClientException("Le client est nul");

        verifierNomPrenom(clientRequestDto);
        verifierEmail(clientRequestDto.email());
        verifierPassword(clientRequestDto.password());
        verifierAdresse(clientRequestDto.adresse());
        verifierDateNaissance(clientRequestDto.dateNaissance());
    }

    private static void verifierNomPrenom(ClientRequestDto clientRequestDto) throws ClientException {
        if (clientRequestDto.nom() == null || clientRequestDto.nom().isBlank())
            throw new ClientException("Le nom est obligatoire");
        if (clientRequestDto.prenom() == null || clientRequestDto.prenom().isBlank())
            throw new ClientException("Le prenom est obligatoire");
    }

    private static void verifierEmail(String email) throws ClientException {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (email == null || email.isBlank() || !email.matches(emailRegex))
            throw new ClientException("L'email est obligatoire et doit être écrit en format email");
    }

    private static void verifierPassword(String password) throws ClientException {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[&@#-_§]).{8,16}$";
        if (password == null || password.isBlank())
            throw new ClientException("Le mot de passe est obligatoire");
        if (!password.matches(passwordRegex))
            throw new ClientException("Le mot de passe doit faire entre 8 et 16 caractères, avec une majuscule, une minuscule, un chiffre, et un caractère spécial parmi : & # @ - _ §");
    }

    private static void verifierAdresse(AdresseRequestDto adresseRequestDto) throws ClientException {
        if (adresseRequestDto == null)
            throw new ClientException("L'adresse est obligatoire");
        if (adresseRequestDto.rue() == null || adresseRequestDto.rue().isBlank())
            throw new ClientException("Le nom de la rue est obligatoire");
        if (adresseRequestDto.codePostal() == null || adresseRequestDto.codePostal().isBlank())
            throw new ClientException("Le code postal est obligatoire");
        if (adresseRequestDto.ville() == null || adresseRequestDto.ville().isBlank())
            throw new ClientException("La ville est obligatoire");
    }

    private static void verifierDateNaissance(LocalDate dateNaissance) throws ClientException {
        if (dateNaissance == null)
            throw new ClientException("La date de naissance est obligatoire");
        if (Period.between(dateNaissance, LocalDate.now()).getYears() < 18)
            throw new ClientException("Vous devez avoir au moins 18 ans, désolé !");
    }

    private static void remplacer(Client clientExist, Client nouveauClient) {
        if (nouveauClient == null)
            throw new ClientException("Le client ne peut pas etre null");
        if (nouveauClient.getNom() != null)
            clientExist.setNom(nouveauClient.getNom());
        if (nouveauClient.getPrenom() != null)
            clientExist.setPrenom(nouveauClient.getPrenom());
        if (nouveauClient.getEmail() != null)
            clientExist.setEmail(nouveauClient.getEmail());
        if (nouveauClient.getPassword() != null)
            clientExist.setPassword(nouveauClient.getPassword());
        if (nouveauClient.getAdresse() != null){
            if (nouveauClient.getAdresse().getRue() != null)
                clientExist.getAdresse().setRue(nouveauClient.getAdresse().getRue());
            if (nouveauClient.getAdresse().getCodePostal() != null)
                clientExist.getAdresse().setCodePostal(nouveauClient.getAdresse().getCodePostal());
            if (nouveauClient.getAdresse().getVille() != null)
                clientExist.getAdresse().setVille(nouveauClient.getAdresse().getVille());
        }
        if (nouveauClient.getDateNaissance()!= null)
            clientExist.setDateNaissance(nouveauClient.getDateNaissance());
        if (nouveauClient.getPermis() != null)
            clientExist.setPermis(nouveauClient.getPermis());
    }

}
