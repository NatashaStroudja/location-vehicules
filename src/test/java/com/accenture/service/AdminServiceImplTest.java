package com.accenture.service;

import com.accenture.exception.AdminException;
import com.accenture.repository.AdminDao;

import com.accenture.service.mapper.AdminMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {
    @Mock
    AdminDao adminDao;

    @Mock
    AdminMapper adminMapper;

    @InjectMocks
    AdminServiceImpl service;

    @BeforeEach
    void init() {
    }
    @DisplayName("""
            Test de la methode ajouter(null) qui doit renvoyer une exception lors que l'admin n'existe pas
            """)
    @Test
    void testAjouterAdminNull(){
        assertThrows(AdminException.class,()->service.ajouterAdmin(null));
    }

}
