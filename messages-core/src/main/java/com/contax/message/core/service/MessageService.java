package com.contax.message.core.service;

import com.contax.message.model.MessageRequest;
import com.contax.message.model.MessageResponse;

import java.util.List;

public interface MessageService {
    List<MessageResponse> getMessages();

    MessageResponse getMessage(long messageId);

    MessageResponse addMessage(MessageRequest messageRequest);

    MessageResponse updateMessage(long messageId, MessageRequest messageRequest);

    void deleteMessage(long messageId);
}
