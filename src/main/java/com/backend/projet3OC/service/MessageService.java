package com.backend.projet3OC.service;

import com.backend.projet3OC.dto.MessageCreateDTO;
import com.backend.projet3OC.dto.MessageResponseDTO;

import java.util.List;

//define methods to get and create messages
public interface MessageService {

    void createMessage(MessageCreateDTO messageCreateDTO);

    List<MessageResponseDTO> getMessagesByRentalId(Long rentalId);
}
