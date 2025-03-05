package com.accenture.service;


import com.accenture.exception.ClientException;
import com.accenture.repository.ClientDao;
import com.accenture.repository.entity.Adresse;
import com.accenture.repository.entity.Client;
import com.accenture.service.dto.AdresseRequestDto;
import com.accenture.service.dto.AdresseResponseDto;
import com.accenture.service.dto.ClientRequestDto;
import com.accenture.service.dto.ClientResponseDto;
import com.accenture.service.mapper.ClientMapper;
import com.accenture.shared.Permis;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {
    @Mock
    ClientDao clientDaoMock;

    @Mock
    ClientMapper clientMapperMock;

    @InjectMocks
    ClientServiceImpl service;


    @DisplayName("""
            Test de la methode ajouter(null) qui doit renvoyer une exception lors que le client n'existe pas
            """)
    @Test
    void testAjouterClientNull() {
        assertThrows(ClientException.class, () -> service.ajouterClient(null));
    }

    @DisplayName("si le nom est null")
    @Test
    void testAjouterClientSansNom() {
        ClientRequestDto clientRequestDto = new ClientRequestDto(null, "Joe", "joe@tut.by", "fGhjk123!HJ", new AdresseRequestDto("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975, 12, 12), List.of(Permis.A));
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si le nom est blank")
    @Test
    void testAjouterClientNomBlank() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("\t", "Joe", "joe@tut.by", "fGhjk123!HJ", new AdresseRequestDto("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975, 12, 12), List.of(Permis.A));
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si le prenom est null")
    @Test
    void testAjouterClientSansPrenom() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", null, "joe@tut.by", "fGhjk123!HJ", new AdresseRequestDto("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975, 12, 12),List.of(Permis.A) );
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si le prenom est blank")
    @Test
    void testAjouterClientPrenomBlank() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "\t", "joe@tut.by", "fGhjk123!HJ", new AdresseRequestDto("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975, 12, 12),List.of(Permis.A));
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si le email est null")
    @Test
    void testAjouterClientSansEmail() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", null, "fGhjk123!HJ", new AdresseRequestDto("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975, 12, 12),List.of(Permis.A) );
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si le email est blank")
    @Test
    void testAjouterClientEmailBlank() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "\t", "fGhjk123!HJ", new AdresseRequestDto("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975, 12, 12),List.of(Permis.A) );
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si l'email ne correspond pas au format email")
    @Test
    void testAjouterClientEmailFormatMauvais() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "joe", "fGhjkJ", new AdresseRequestDto("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975, 12, 12),List.of(Permis.A));
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si le password est null")
    @Test
    void testAjouterClientSansPassword() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "joe@tut.by", null, new AdresseRequestDto("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975, 12, 12), List.of(Permis.A));
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si le password est blank")
    @Test
    void testAjouterClientPasswordBlank() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "joe@tut.by", "\t", new AdresseRequestDto("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975, 12, 12),List.of(Permis.A)) ;
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si le password n'est pas valide")
    @Test
    void testAjouterClientPasswordNotValide() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "joe@tut.by", "fghjkdfgh", new AdresseRequestDto("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975, 12, 12),List.of(Permis.A) );
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }


    @DisplayName("si adresse est null")
    @Test
    void testAjouterClientSansAdresse() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "joe@tut.by", "fGhjk123!HJ", null, LocalDate.of(1975, 12, 12),List.of(Permis.A) );
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si la rue est null")
    @Test
    void testAjouterClientSansRue() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "joe@tut.by", "fGhjk123!HJ", new AdresseRequestDto(null, "44000", "Nantes"), LocalDate.of(1975, 12, 12),List.of(Permis.A));
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si la rue est blank")
    @Test
    void testAjouterClientRueBlank() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "joe@tut.by", "fGhjk123!HJ", new AdresseRequestDto("\t", "44000", "Nantes"), LocalDate.of(1975, 12, 12), List.of(Permis.A));
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si code postal est null")
    @Test
    void testAjouterClientSansCodePostal() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "joe@tut.by", "fGhjk123!HJ", new AdresseRequestDto("rue du Soleil", null, "Nantes"), LocalDate.of(1975, 12, 12), List.of(Permis.A));
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si code postal est blank")
    @Test
    void testAjouterClientCodePostalBlank() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "joe@tut.by", "fGhjk123!HJ", new AdresseRequestDto("rue du Soleil", "\t", "Nantes"), LocalDate.of(1975, 12, 12), List.of(Permis.A));
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si la ville est null")
    @Test
    void testAjouterClientSansVille() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "joe@tut.by", "fGhjk123!HJ", new AdresseRequestDto("rue du Soleil", "44000", null), LocalDate.of(1975, 12, 12),List.of(Permis.A) );
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si la ville est blank")
    @Test
    void testAjouterClientVilleBlank() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "joe@tut.by", "fGhjk123!HJ", new AdresseRequestDto("rue du Soleil", "44000", "\t"), LocalDate.of(1975, 12, 12),List.of(Permis.A) );
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si la date de naissance est null")
    @Test
    void testAjouterClientSansDateNaissance() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "joe@tut.by", "fGhjk123!HJ", new AdresseRequestDto("rue du Soleil", "44000", "Nantes"), null,List.of(Permis.A) );
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si le client est majeur")
    @Test
    void testAjouterClientMineur() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "joe@tut.by", "fGhjk123§HJ", new AdresseRequestDto("rue du Soleil", "44000", "Nantes"), LocalDate.of(2024, 9, 9),List.of(Permis.A));
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si ajouterClient(ClientRequestDto ok), alors save et renvoie un clientResponseDto")
    @Test
    void testAjouterClient() {
        AdresseRequestDto adresse = new AdresseRequestDto("1 rue du Pont", "310987", "Prague");
        ClientRequestDto clientRequestDto = new ClientRequestDto("Kafka", "Franz", "chateau@praga.ch",
                "Jaipeur2monPere§", adresse, LocalDate.of(1883, 7, 3), List.of(Permis.A));
        Client clientAvantEnreg = creerClient();
        clientAvantEnreg.setId(1);

        Client clientApresEnreg = creerClient();
        ClientResponseDto responseDto = creerClientResponseDto();

        Mockito.when(clientMapperMock.toClient(clientRequestDto)).thenReturn(clientAvantEnreg);
        Mockito.when(clientDaoMock.save(clientAvantEnreg)).thenReturn(clientApresEnreg);
        Mockito.when(clientMapperMock.toClientResponseDto(clientApresEnreg)).thenReturn(responseDto);
        assertSame(responseDto, service.ajouterClient(clientRequestDto));
        Mockito.verify(clientDaoMock, Mockito.times(1)).save((clientAvantEnreg));

    }

    @DisplayName("test la methode trouverByEmailEtPassword(String email, String password) si email n'existe pas")
    @Test
    void testTrouverByEmailExistePas() {
        Mockito.when(clientDaoMock.findByEmailAndPassword("catsfarm@mail.ru", "fghfjfhjfjh")).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> service.trouverByEmailEtPassword("catsfarm@mail.ru", "fghfjfhjfjh"));
        assertEquals("Ce mail n'existe pas dans notre base de données", exception.getMessage());

    }

    @DisplayName("test la methode trouverByEmailEtPassword(String email, String password) si email existe ")
    @Test
    void testTrouverByEmailExiste() {
        Client client = creerClient();
        Optional<Client> optClient = Optional.of(client);
        Mockito.when(clientDaoMock.findByEmailAndPassword("chateau@praga.ch", "Jaipeur2monPere§")).thenReturn(optClient);

        AdresseResponseDto adresseResponseDto = new AdresseResponseDto(1, "1 rue du Pont", "310987", "Prague");
        ClientResponseDto dto = new ClientResponseDto(
                1, "Kafka", "Franz", "chateau@praga.ch", adresseResponseDto,
                LocalDate.of(1883, 7, 3), LocalDate.now(),List.of(Permis.A));
        ;

        Mockito.when(clientMapperMock.toClientResponseDto(client)).thenReturn(dto);
        assertSame(dto, service.trouverByEmailEtPassword("chateau@praga.ch", "Jaipeur2monPere§"));
    }

    @DisplayName("test trouverTousClients qui renvoie la list de tous les clients de la base de données")
    @Test
    void testTrouverTous() {
        Client client = creerClient();
        Client clientAutre = creerClientAutre();
        List<Client> clients = List.of(creerClient(), creerClientAutre());
        AdresseResponseDto adresseResponseDto = new AdresseResponseDto(1, "1 rue du Pont", "310987", "Prague");
        ClientResponseDto clientResponseDto = new ClientResponseDto(1, "Kafka", "Franz", "chateau@praga.ch", adresseResponseDto,
                LocalDate.of(1883, 7, 3), LocalDate.now(),List.of(Permis.A) );
        AdresseResponseDto adresseResponseDtoAutre = new AdresseResponseDto(2, "1 rue de la Joie", "250000", "Minsk");
        ClientResponseDto clientResponseDtoAutre = new ClientResponseDto(2, "Pitt", "Brad", "JohnnySuede@tut.by", adresseResponseDtoAutre,
                LocalDate.of(1963, 12, 18), LocalDate.now(), List.of(Permis.A));
        List<ClientResponseDto> dtos = List.of(clientResponseDto, clientResponseDtoAutre);

        Mockito.when(clientDaoMock.findAll()).thenReturn(clients);
        Mockito.when(clientMapperMock.toClientResponseDto(client)).thenReturn(clientResponseDto);
        Mockito.when(clientMapperMock.toClientResponseDto(clientAutre)).thenReturn(clientResponseDtoAutre);
        assertEquals(dtos, service.trouverTousClients());
    }

    @DisplayName("test de la methode supprimer(id) si l'id n'existe pas")
    @Test
    void testSupprimerClientNonExist() {
        int id = 99;
        Mockito.when(clientDaoMock.existsById(99)).thenReturn(false);
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> service.supprimer(id));
        assertEquals("Ce id n'existe pas!", exception.getMessage());
    }

    @DisplayName("test de la methode supprimer(id) si l'id existe ")
    @Test
    void testSupprimerClientExist() {
        Mockito.when(clientDaoMock.existsById(1)).thenReturn(true);
        service.supprimer(1);
        Mockito.verify(clientDaoMock, Mockito.times(1)).deleteById(1);
    }

    @DisplayName("test methode modifierPartiellement(int id, ClientRequestDto clientRequestDto) si le client n'existe pas")
    @Test
    void testModifPartielIdExistPas() {
        Mockito.when(clientDaoMock.findById(99)).thenReturn(Optional.empty());
        ClientRequestDto clientRequestDto = new ClientRequestDto("Dichillo", "Tom", "tommy@tut.by", "234DFGHh§", new AdresseRequestDto("rue de la paix", "44400", "Rezé"),
                LocalDate.of(1953, 8, 14),List.of(Permis.B));
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> service.modifierPartiellement(99, clientRequestDto));
        assertEquals("Ce id n'existe pas!", exception.getMessage());
    }

    @DisplayName("test methode modifierPartiellement(int id, ClientRequestDto clientRequestDto) si le client est null")
    @Test
    void testModifPartielClientNull() {
        Client client = creerClient();
        Optional<Client> optionalClient = Optional.of(client);
        Mockito.when(clientDaoMock.findById(2)).thenReturn(optionalClient);
        Mockito.when(clientMapperMock.toClient(null)).thenReturn(null);
        assertThrows(ClientException.class, () -> service.modifierPartiellement(2, null));
    }

    @DisplayName("Test methode modifierPartiellement(int id, ClientRequestDto clientRequestDto) en modifiant nom")
    @Test
    void testModifPartielNouveauNom() {
        Client client = creerClient();
        Optional<Client> optionalClient = Optional.of(client);
        Mockito.when(clientDaoMock.findById(1)).thenReturn(optionalClient);
        ClientRequestDto clientRequestDto = creerClientRequestDto();
        Mockito.when(clientMapperMock.toClient(clientRequestDto)).thenReturn(client);
        Client clientNouveauNom = creerClientNouveauNom();
        ClientResponseDto clientResponseDto = creerClientResponseDto();
       Mockito.when(clientDaoMock.save(client)).thenReturn(clientNouveauNom);
        Mockito.when(clientMapperMock.toClientResponseDto(clientNouveauNom)).thenReturn(clientResponseDto);
        assertEquals(clientResponseDto,  service.modifierPartiellement(1, clientRequestDto) );

    }


    private static Client creerClient() {
        Client client = new Client();
        client.setId(1);
        client.setNom("Kafka");
        client.setPrenom("Franz");
        client.setEmail("chateau@praga.ch");
        client.setPassword("Jaipeur2monPere§");
        Adresse adresse = new Adresse(1, "1 rue du Pont", "310987", "Prague");
        client.setAdresse(adresse);
        client.setDateNaissance(LocalDate.of(1883, 7, 3));
        client.setDateDInscription(LocalDate.now());
        client.setPermis(List.of(Permis.A));
        client.setDesactive(true);
        return client;
    }

    private static Client creerClientAutre() {
        Client client = new Client();
        client.setId(2);
        client.setNom("Pitt");
        client.setPrenom("Brad");
        client.setEmail("JohnnySuede@tut.by");
        client.setPassword("LaVieEtBelle1§");
        Adresse adresse = new Adresse(2, "1 rue de la Joie", "250000", "Minsk");
        client.setAdresse(adresse);
        client.setDateNaissance(LocalDate.of(1963, 12, 18));
        client.setDateDInscription(LocalDate.now());
        client.setPermis(List.of(Permis.A));
        client.setDesactive(true);
        return client;
    }

    public static ClientResponseDto creerClientResponseDto() {
        AdresseResponseDto adresse = new AdresseResponseDto(1, "1 rue du Pont", "310987", "Prague");
        return new ClientResponseDto(1, "Tolstoj", "Franz", "chateau@praga.ch",
                adresse, LocalDate.of(1883, 7, 3), LocalDate.now(), List.of(Permis.A));
    }

    public static ClientRequestDto creerClientRequestDto() {
        AdresseRequestDto adresse = new AdresseRequestDto("1 rue du Pont", "310987", "Prague");
        return new ClientRequestDto("Kafka", "Franz", "chateau@praga.ch", "Jaipeur2monPere§", adresse, LocalDate.of(1883, 7, 3), List.of(Permis.A));
    }

    public static Client creerClientNouveauNom() {
        Client client = new Client();
        client.setId(1);
        client.setNom("Tolstoj");
        client.setPrenom("Franz");
        client.setEmail("chateau@praga.ch");
        client.setPassword("Jaipeur2monPere§");
        Adresse adresse = new Adresse(1, "1 rue du Pont", "310987", "Prague");
        client.setAdresse(adresse);
        client.setDateNaissance(LocalDate.of(1883, 7, 3));
        client.setDateDInscription(LocalDate.now());
        client.setPermis(List.of(Permis.A));
        client.setDesactive(true);
        return client;
    }
    /*   */
}