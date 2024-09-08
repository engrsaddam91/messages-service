import com.contax.message.core.dao.entity.Message;
import com.contax.message.core.dao.repository.MessageRepository;
import com.contax.message.core.mapper.MessageMapper;
import com.contax.message.core.service.impl.MessageServiceImp;
import com.contax.message.model.MessageRequest;
import com.contax.message.model.MessageResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private MessageMapper mapper;

    @InjectMocks
    private MessageServiceImp messageService;


    @Test
    public void testGetMessages() {
        Message message = createMessageEntity();
        MessageResponse messageResponse = createMessageResponse();
        messageResponse.setCreatedOn(message.getCreatedOn());
        when(messageRepository.findAll()).thenReturn(List.of(message));
        when(mapper.map(List.of(message))).thenReturn(List.of(messageResponse));
        List<MessageResponse> response = messageService.getMessages();
        assertNotNull(response);
        assertEquals(message.getId(), response.get(0).getId());
        assertEquals(message.getContent(), response.get(0).getContent());
    }

    @Test
    public void testGetMessage() {
        Message message = createMessageEntity();
        MessageResponse messageResponse = createMessageResponse();
        messageResponse.setCreatedOn(message.getCreatedOn());
        when(messageRepository.findById(message.getId())).thenReturn(Optional.of(message));
        when(mapper.map(message)).thenReturn(messageResponse);
        MessageResponse response = messageService.getMessage(message.getId());
        assertNotNull(response);
        assertEquals(message.getId(), response.getId());
        assertEquals(message.getContent(), response.getContent());
    }

    @Test
    public void testAddMessage() {
        Message message = createMessageEntity();
        MessageRequest messagerequest = createMessageRequest();
        MessageResponse messageResponse = createMessageResponse();
        when(messageRepository.save(any(Message.class))).thenReturn(message);
        when(mapper.map(any(Message.class))).thenReturn(messageResponse);
        MessageResponse response = messageService.addMessage(messagerequest);
        assertNotNull(response);
        assertEquals(message.getId(), response.getId());
        assertEquals(message.getContent(), response.getContent());
    }

    @Test
    public void testUpdateMessage() {
        Message message = createMessageEntity();
        MessageRequest messagerequest = createMessageRequest();
        MessageResponse messageResponse = createMessageResponse();
        messageResponse.setCreatedOn(message.getCreatedOn());
        when(messageRepository.findById(message.getId())).thenReturn(Optional.of(message));
        when(messageRepository.save(any(Message.class))).thenReturn(message);
        when(mapper.map(any(Message.class))).thenReturn(messageResponse);
        MessageResponse response = messageService.updateMessage(message.getId(), messagerequest);
        assertNotNull(response);
        assertEquals(message.getId(), response.getId());
        assertEquals(message.getContent(), response.getContent());
    }

    @Test
    public void testDeleteMessage() {
        Message message = createMessageEntity();
        doNothing().when(messageRepository).deleteById(message.getId());
        messageService.deleteMessage(message.getId());
        verify(messageRepository).deleteById(message.getId());
    }

    private Message createMessageEntity(){
        return Message.builder().Id(1).content("Test Message").createdOn(LocalDateTime.now()).build();
    }

    private MessageResponse createMessageResponse() {
        return MessageResponse.builder().id(1).content("Test Message").createdOn(LocalDateTime.now()).build();
    }

    private MessageRequest createMessageRequest() {
        return MessageRequest.builder().content("Test Message").build();
    }
}
