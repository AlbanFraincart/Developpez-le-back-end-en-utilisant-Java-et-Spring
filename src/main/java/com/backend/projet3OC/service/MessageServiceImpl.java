package com.backend.projet3OC.service;

import com.backend.projet3OC.dto.MessageCreateDTO;
import com.backend.projet3OC.dto.MessageResponseDTO;
import com.backend.projet3OC.dto.UserResponseDTO;
import com.backend.projet3OC.model.Message;
import com.backend.projet3OC.model.Rental;
import com.backend.projet3OC.model.User;
import com.backend.projet3OC.repository.MessageRepository;
import com.backend.projet3OC.repository.RentalRepository;
import com.backend.projet3OC.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


//messages implementation to handle file storage
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final AuthService authService;

    public MessageServiceImpl(MessageRepository messageRepository, RentalRepository rentalRepository, UserRepository userRepository, AuthService authService) {
        this.messageRepository = messageRepository;
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @Override
    public void createMessage(MessageCreateDTO messageCreateDTO) {
        Message message = new Message();
        message.setMessage(messageCreateDTO.getMessage());

        Rental rental = rentalRepository.findById(messageCreateDTO.getRental_id())
                .orElseThrow(() -> new RuntimeException("Rental not found with id: " + messageCreateDTO.getRental_id()));
        message.setRental(rental);

        UserResponseDTO currentUserDTO = authService.getCurrentUser();
        User user = userRepository.findById(currentUserDTO.getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + currentUserDTO.getId()));
        message.setUser(user);

        messageRepository.save(message);
    }


    @Override
    public List<MessageResponseDTO> getMessagesByRentalId(Long rentalId) {
        List<Message> messages = messageRepository.findByRentalId(rentalId);
        return messages.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private MessageResponseDTO convertToResponseDTO(Message message) {
        MessageResponseDTO dto = new MessageResponseDTO();
        dto.setId(message.getId());
        dto.setMessage(message.getMessage());
        dto.setUser_id(message.getUser().getId());
        dto.setRental_id(message.getRental().getId());
        dto.setCreated_at(message.getCreated_at());
        dto.setUpdated_at(message.getUpdated_at());
        return dto;
    }
}
