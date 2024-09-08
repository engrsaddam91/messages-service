package com.contax.message.controller;

import com.contax.message.core.service.MessageService;
import com.contax.message.model.MessageRequest;
import com.contax.message.model.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "messages")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MessageResponse>> getMessages() {
        final List<MessageResponse> messages = messageService.getMessages();
        return ResponseEntity.ok(messages);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> getMessage(@PathVariable(name = "id") long messageId) {
        final MessageResponse message = messageService.getMessage(messageId);
        return ResponseEntity.ok(message);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> addMessage(@Valid @NotNull @RequestBody MessageRequest messageRequest) {
        final MessageResponse message = messageService.addMessage(messageRequest);
        return ResponseEntity.ok(message);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> updateMessage(@PathVariable(name = "id") long messageId, @RequestBody MessageRequest messageRequest) {
        final MessageResponse message = messageService.updateMessage(messageId, messageRequest);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable(name = "id") long messageId) {
        messageService.deleteMessage(messageId);
        return ResponseEntity.noContent().build();
    }

}
