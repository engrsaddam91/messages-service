package com.contax.message.core.service.impl;

import com.contax.message.core.dao.entity.Message;
import com.contax.message.core.dao.repository.MessageRepository;
import com.contax.message.core.mapper.MessageMapper;
import com.contax.message.core.service.MessageService;
import com.contax.message.model.MessageRequest;
import com.contax.message.model.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class MessageServiceImp implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper mapper;

    @Autowired
    public MessageServiceImp(MessageRepository messageRepository, MessageMapper mapper) {
        this.messageRepository = messageRepository;
        this.mapper = mapper;
    }

    @Override
    public List<MessageResponse> getMessages() {
        log.info("Fetching All Messages");
        final List<Message> messages = (List<Message>) messageRepository.findAll();
        return mapper.map(messages);
    }

    @Override
    public MessageResponse getMessage(final long messageId) {
        log.info("Fetching Message By Id {}", messageId);
        final Message message = messageRepository.findById(messageId).orElseThrow(() -> new ResourceNotFoundException("Message Not Found"));
        return mapper.map(message);
    }

    @Override
    public MessageResponse addMessage(final MessageRequest messageRequest) {
        log.info("Inserting new message");
        Message message = new Message();
        message.setContent(messageRequest.getContent());
        message.setCreatedOn(LocalDateTime.now());
        final Message savedMessage = messageRepository.save(message);
        return mapper.map(savedMessage);
    }

    @Override
    @Transactional
    public MessageResponse updateMessage(final long messageId, final MessageRequest messageRequest) {
        log.info("updating message content for id {}", messageId);
        Message message = messageRepository.findById(messageId).orElseThrow(() -> new ResourceNotFoundException("Message not Found"));
        message.setContent(messageRequest.getContent());
        message.setUpdatedOn(LocalDateTime.now());
        final Message updatedMessage = messageRepository.save(message);
        return mapper.map(updatedMessage);
    }

    @Override
    public void deleteMessage(final long messageId) {
        log.info("Deleting message {}", messageId);
        messageRepository.deleteById(messageId);
    }
}
