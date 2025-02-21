package com.accenture.service;

import com.accenture.exception.ClientException;
import com.accenture.service.dto.ClientRequestDto;
import com.accenture.service.dto.ClientResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClientService {
    ClientResponseDto ajouterClient(ClientRequestDto clientRequestDto) throws ClientException;
    List<ClientResponseDto> trouverTousClients();
    ClientResponseDto trouverByEmail(String email);
}
