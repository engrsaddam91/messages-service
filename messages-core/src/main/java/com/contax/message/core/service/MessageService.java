package com.contax.message.core.service;

import com.contax.message.model.MessageRequest;
import com.contax.message.model.MessageResponse;

import java.util.List;

public interface MessageService {

    /**
     * This Method is used to get All Messages
     *
     * @return - List<MessageResponse>
     */
    List<MessageResponse> getMessages();

    /**
     * This Method is used get Message By ID
     *
     * @param - messageId
     * @return - MessageResponse
     */
    MessageResponse getMessage(long messageId);

    /**
     * This Method is used to insert new message
     *
     * @param - messageRequest
     * @return - MessageResponse
     */
    MessageResponse addMessage(MessageRequest messageRequest);

    /**
     * This Method is used to Update the content of Message
     *
     * @param - messageId
     * @param - messageRequest
     * @return - MessageResponse
     */
    MessageResponse updateMessage(long messageId, MessageRequest messageRequest);

    /**
     * This Method is used to delete the Message
     *
     * @param - messageId
     */
    void deleteMessage(long messageId);
}
