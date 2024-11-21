package com.backend.projet3OC.service;

import com.backend.projet3OC.dto.MessageCreateDTO;
import com.backend.projet3OC.dto.MessageResponseDTO;

import java.util.List;

public interface MessageService {

    void createMessage(MessageCreateDTO messageCreateDTO);

    List<MessageResponseDTO> getMessagesByRentalId(Long rentalId);
}
