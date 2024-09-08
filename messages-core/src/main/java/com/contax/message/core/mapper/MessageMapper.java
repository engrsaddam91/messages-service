package com.contax.message.core.mapper;

import com.contax.message.core.dao.entity.Message;
import com.contax.message.model.MessageResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageResponse map(Message message);

    List<MessageResponse> map(List<Message> message);

}
