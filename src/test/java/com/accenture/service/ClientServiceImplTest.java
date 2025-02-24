package com.accenture.service;

import com.accenture.exception.AdminException;
import com.accenture.exception.ClientException;
import com.accenture.repository.ClientDao;
import com.accenture.service.dto.AdresseRequestDto;
import com.accenture.service.dto.AdresseResponseDto;
import com.accenture.service.dto.ClientRequestDto;
import com.accenture.service.mapper.ClientMapper;
import com.accenture.shared.Permis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {
    @Mock
    ClientDao clientDao;

    @Mock
    ClientMapper clientMapper;

    @InjectMocks
    ClientServiceImpl service;

    @BeforeEach
    void init(){
    }

  @DisplayName("""
            Test de la methode ajouter(null) qui doit renvoyer une exception lors que le client n'existe pas
            """)
    @Test
    void testAjouterClientNull(){
        assertThrows(ClientException.class,()->service.ajouterClient(null));
  }
  @DisplayName("si le nom est null")
    @Test
    void testAjouterClientSansNom(){
      ClientRequestDto clientRequestDto = new ClientRequestDto(null,"Joe","joe@tut.by", "fGhjk123!HJ",new AdresseRequestDto ("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975,12,12), Permis.A);
  }
    @DisplayName("si le nom est blank")
    @Test
    void testAjouterClientNomBlank(){
        ClientRequestDto clientRequestDto = new ClientRequestDto("\t","Joe","joe@tut.by", "fGhjk123!HJ",new AdresseRequestDto ("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975,12,12), Permis.A);
    }
    @DisplayName("si le prenom est null")
    @Test
    void testAjouterClientSansPrenom(){
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand",null,"joe@tut.by", "fGhjk123!HJ",new AdresseRequestDto ("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975,12,12), Permis.A);
    }
    @DisplayName("si le prenom est blank")
    @Test
    void testAjouterClientPrenomBlank(){
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand","\t","joe@tut.by", "fGhjk123!HJ",new AdresseRequestDto ("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975,12,12), Permis.A);
    }

    @DisplayName("si le email est null")
    @Test
    void testAjouterClientSansEmail(){
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand","Joe",null, "fGhjk123!HJ",new AdresseRequestDto ("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975,12,12), Permis.A);
    }
    @DisplayName("si le email est blank")
    @Test
    void testAjouterClientEmailBlank(){
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand","Joe","\t", "fGhjk123!HJ",new AdresseRequestDto ("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975,12,12), Permis.A);
    }
    @DisplayName("si le password est null")
    @Test
    void testAjouterClientSansPassword(){
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand","Joe","joe@tut.by", null,new AdresseRequestDto ("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975,12,12), Permis.A);
    }
    @DisplayName("si le password est blank")
    @Test
    void testAjouterClientPasswordBlank(){
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand","Joe","joe@tut.by", "\t",new AdresseRequestDto ("rue du Soleil", "44000", "Nantes"), LocalDate.of(1975,12,12), Permis.A);
    }
    @DisplayName("si la rue est null")
    @Test
    void testAjouterClientSansRue(){
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand","Joe","joe@tut.by", "fGhjk123!HJ",new AdresseRequestDto (null, "44000", "Nantes"), LocalDate.of(1975,12,12), Permis.A);
    }
    @DisplayName("si la rue est blank")
    @Test
    void testAjouterClientRueBlank(){
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand","Joe","joe@tut.by", "fGhjk123!HJ",new AdresseRequestDto ("\t", "44000", "Nantes"), LocalDate.of(1975,12,12), Permis.A);
    }
    @DisplayName("si code postal est null")
    @Test
    void testAjouterClientSansCodePostal(){
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand","Joe","joe@tut.by", "fGhjk123!HJ",new AdresseRequestDto ("rue du Soleil", null, "Nantes"), LocalDate.of(1975,12,12), Permis.A);
    }
    @DisplayName("si code postal est blank")
    @Test
    void testAjouterClientCodePostalBlank(){
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand","Joe","joe@tut.by", "fGhjk123!HJ",new AdresseRequestDto ("rue du Soleil", "\t", "Nantes"), LocalDate.of(1975,12,12), Permis.A);
    }
    @DisplayName("si la ville est null")
    @Test
    void testAjouterClientSansVille(){
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand","Joe","joe@tut.by", "fGhjk123!HJ",new AdresseRequestDto ("rue du Soleil","44000" , null), LocalDate.of(1975,12,12), Permis.A);
    }
    @DisplayName("si la ville est blank")
    @Test
    void testAjouterClientVilleBlank(){
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand","Joe","joe@tut.by", "fGhjk123!HJ",new AdresseRequestDto ("rue du Soleil","44000" , "\t"), LocalDate.of(1975,12,12), Permis.A);
    }

    @DisplayName("si la date de naissance est null")
    @Test
    void testAjouterClientSansDateNaissance(){
        ClientRequestDto clientRequestDto = new ClientRequestDto("Legrand","Joe","joe@tut.by", "fGhjk123!HJ",new AdresseRequestDto ("rue du Soleil","44000" , "Nantes"), null, Permis.A);
    }

}