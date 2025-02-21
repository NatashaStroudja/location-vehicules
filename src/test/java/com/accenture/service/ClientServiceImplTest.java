package com.accenture.service;

import com.accenture.repository.ClientDao;
import com.accenture.service.mapper.ClientMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

  /*  @DisplayName("""
            Test de la methode 
            """)*/

}