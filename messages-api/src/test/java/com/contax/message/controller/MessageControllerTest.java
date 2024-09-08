package com.contax.message.controller;

import com.contax.message.core.service.MessageService;
import com.contax.message.model.MessageRequest;
import com.contax.message.model.MessageResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MessageControllerTest {

    @Mock
    private MessageService messageService;

    @InjectMocks
    private MessageController messageController;

    @Test
    public void testGetMessages() {
        MessageResponse message = createMessageResponse();
        when(messageService.getMessages()).thenReturn(List.of(message));
        ResponseEntity<List<MessageResponse>> response = messageController.getMessages();
        assertNotNull(response);
        List<MessageResponse> messageResponseList = response.getBody();
        assertNotNull(messageResponseList);
        assertEquals(message, messageResponseList.get(0));


    }

    @Test
    public void testGetMessage() {
        MessageResponse message = createMessageResponse();
        when(messageService.getMessage(anyLong())).thenReturn(message);
        ResponseEntity<MessageResponse> response = messageController.getMessage(1);
        assertNotNull(response);
        MessageResponse messageResponse = response.getBody();
        assertNotNull(messageResponse);
        assertEquals(message, messageResponse);
    }

    @Test
    public void testAddMessage() {
        MessageRequest message = createMessageRequest();
        MessageResponse messageResponse = createMessageResponse();
        when(messageService.addMessage(message)).thenReturn(messageResponse);
        ResponseEntity<MessageResponse> response = messageController.addMessage(message);
        assertNotNull(response);
        MessageResponse messageResp = response.getBody();
        assertNotNull(messageResp);
        assertEquals(messageResponse, messageResp);
    }


    @Test
    public void testUpdateMessage() {
        MessageRequest message = createMessageRequest();
        MessageResponse messageResponse = createMessageResponse();
        when(messageService.addMessage(message)).thenReturn(messageResponse);
        ResponseEntity<MessageResponse> response = messageController.addMessage(message);
        assertNotNull(response);
        MessageResponse messageResp = response.getBody();
        assertNotNull(messageResp);
        assertEquals(messageResponse, messageResp);

        message.setContent("Update test Message");
        messageResponse.setContent(message.getContent());
        when(messageService.updateMessage(messageResponse.getId(), message)).thenReturn(messageResponse);
        ResponseEntity<MessageResponse> updatedResponse = messageController.updateMessage(messageResponse.getId(), message);
        assertNotNull(updatedResponse);
        MessageResponse updatedMessageResp = updatedResponse.getBody();
        assertNotNull(updatedMessageResp);
        assertEquals(messageResponse, updatedMessageResp);
    }

    @Test
    public void testDeleteMessage() {

        doNothing().when(messageService).deleteMessage(anyLong());
        messageController.deleteMessage(1);
        verify(messageService).deleteMessage(1);
    }

    private MessageResponse createMessageResponse() {
        return MessageResponse.builder().id(1).content("Test Message").createdOn(LocalDateTime.now()).build();
    }

    private MessageRequest createMessageRequest() {
        return MessageRequest.builder().content("Test Message").build();
    }

}
