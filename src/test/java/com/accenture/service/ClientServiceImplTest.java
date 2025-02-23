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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
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

    @BeforeEach
    void init() {
    }

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
        ClientRequestDto clientRequestDto = new ClientRequestDto(null, "Joe", "joe@tut.by", "fGhjk123!HJ", new AdresseRequestDto("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975, 12, 12), Permis.A);
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si le nom est blank")
    @Test
    void testAjouterClientNomBlank() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("\t", "Joe", "joe@tut.by", "fGhjk123!HJ", new AdresseRequestDto("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975, 12, 12), Permis.A);
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si le prenom est null")
    @Test
    void testAjouterClientSansPrenom() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", null, "joe@tut.by", "fGhjk123!HJ", new AdresseRequestDto("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975, 12, 12), Permis.A);
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si le prenom est blank")
    @Test
    void testAjouterClientPrenomBlank() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "\t", "joe@tut.by", "fGhjk123!HJ", new AdresseRequestDto("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975, 12, 12), Permis.A);
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si le email est null")
    @Test
    void testAjouterClientSansEmail() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", null, "fGhjk123!HJ", new AdresseRequestDto("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975, 12, 12), Permis.A);
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si le email est blank")
    @Test
    void testAjouterClientEmailBlank() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "\t", "fGhjk123!HJ", new AdresseRequestDto("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975, 12, 12), Permis.A);
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si l'email ne correspond pas au format email")
    @Test
    void testAjouterClientEmailFormatMauvais() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "joe", "fGhjkJ", new AdresseRequestDto("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975, 12, 12), Permis.A);
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si le password est null")
    @Test
    void testAjouterClientSansPassword() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "joe@tut.by", null, new AdresseRequestDto("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975, 12, 12), Permis.A);
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si le password est blank")
    @Test
    void testAjouterClientPasswordBlank() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "joe@tut.by", "\t", new AdresseRequestDto("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975, 12, 12), Permis.A);
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si le password n'est pas valide")
    @Test
    void testAjouterClientPasswordNotValide() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "joe@tut.by", "fghjkdfgh", new AdresseRequestDto("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975, 12, 12), Permis.A);
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }


    @DisplayName("si adresse est null")
    @Test
    void testAjouterClientSansAdresse(){
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "joe@tut.by", "fGhjk123!HJ", null, LocalDate.of(1975, 12, 12), Permis.A);
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si la rue est null")
    @Test
    void testAjouterClientSansRue() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "joe@tut.by", "fGhjk123!HJ", new AdresseRequestDto(null, "44000", "Nantes"), LocalDate.of(1975, 12, 12), Permis.A);
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si la rue est blank")
    @Test
    void testAjouterClientRueBlank() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "joe@tut.by", "fGhjk123!HJ", new AdresseRequestDto("\t", "44000", "Nantes"), LocalDate.of(1975, 12, 12), Permis.A);
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si code postal est null")
    @Test
    void testAjouterClientSansCodePostal() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "joe@tut.by", "fGhjk123!HJ", new AdresseRequestDto("rue du Soleil", null, "Nantes"), LocalDate.of(1975, 12, 12), Permis.A);
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si code postal est blank")
    @Test
    void testAjouterClientCodePostalBlank() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "joe@tut.by", "fGhjk123!HJ", new AdresseRequestDto("rue du Soleil", "\t", "Nantes"), LocalDate.of(1975, 12, 12), Permis.A);
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si la ville est null")
    @Test
    void testAjouterClientSansVille() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "joe@tut.by", "fGhjk123!HJ", new AdresseRequestDto("rue du Soleil", "44000", null), LocalDate.of(1975, 12, 12), Permis.A);
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si la ville est blank")
    @Test
    void testAjouterClientVilleBlank() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "joe@tut.by", "fGhjk123!HJ", new AdresseRequestDto("rue du Soleil", "44000", "\t"), LocalDate.of(1975, 12, 12), Permis.A);
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si la date de naissance est null")
    @Test
    void testAjouterClientSansDateNaissance() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "joe@tut.by", "fGhjk123!HJ", new AdresseRequestDto("rue du Soleil", "44000", "Nantes"), null, Permis.A);
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
    }

    @DisplayName("si le client est majeur")
    @Test
    void testAjouterClientMineur() {
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand", "Joe", "joe@tut.by", "fGhjk123§HJ", new AdresseRequestDto("rue du Soleil", "44000", "Nantes"), LocalDate.of(2024,9,9), Permis.A);
        assertThrows(ClientException.class, () -> service.ajouterClient(clientRequestDto));
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
        client.setDateNaissance( LocalDate.of(1883, 7, 3));
        client.setDateDInscription(LocalDate.now());
        client.setCategoriePermis(Permis.A);
        client.setDesactive(true);
        return client;
    }

    public static ClientResponseDto creerClientResponseDto(){
        AdresseResponseDto adresse = new AdresseResponseDto (1, "1 rue du Pont", "310987", "Prague");
        return new ClientResponseDto(1,"Kafka", "Franz", "chateau@praga.ch",
                adresse, LocalDate.of(1883, 7, 3), LocalDate.now(), Permis.A, true);
    }

    @DisplayName("si ajouterClient(ClientRequestDto ok), alors save et renvoie un clientResponseDto")
    @Test
    void testAjouterClient() {
        AdresseRequestDto adresse = new AdresseRequestDto ("1 rue du Pont", "310987", "Prague");
        ClientRequestDto clientRequestDto = new ClientRequestDto("Kafka", "Franz", "chateau@praga.ch",
                "Jaipeur2monPere§",  adresse, LocalDate.of(1883, 7, 3), Permis.A);
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
    void testTrouverByEmailExistePas(){
        Mockito.when(clientDaoMock.findByEmailAndPassword("catsfarm@mail.ru", "fghfjfhjfjh")).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, ()->service.trouverByEmailEtPassword("catsfarm@mail.ru", "fghfjfhjfjh"));
        assertEquals("L'id non present", exception.getMessage());

    }
/*Optional<Client> optClient = Optional.of(client);
        AdresseResponseDto adresseResponseDto = new AdresseResponseDto(1,"1 rue du Pont", "310987", "Prague");
        ClientResponseDto dto = new ClientResponseDto(
                1, "Kafka", "Franz", "chateau@praga.ch", adresseResponseDto,
                LocalDate.of(1883, 7, 3), LocalDate.now(), Permis.A, true);

        Mockito.when(clientMapperMock.toClientResponseDto(client)).thenReturn(dto);

        assertSame(dto, service.trouverByEmailEtPassword(1));
 */
}