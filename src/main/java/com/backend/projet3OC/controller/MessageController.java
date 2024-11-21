package com.backend.projet3OC.controller;

import com.backend.projet3OC.dto.MessageCreateDTO;
import com.backend.projet3OC.dto.MessageResponseDTO;
import com.backend.projet3OC.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@Validated
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/")
    public ResponseEntity<String> createMessage(@Valid @RequestBody MessageCreateDTO messageCreateDTO) {
        messageService.createMessage(messageCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Message created successfully");
    }

    @GetMapping("/rental/{rentalId}")
    public ResponseEntity<List<MessageResponseDTO>> getMessagesByRentalId(@PathVariable Long rentalId) {
        List<MessageResponseDTO> messages = messageService.getMessagesByRentalId(rentalId);
        return ResponseEntity.ok(messages);
    }
}
