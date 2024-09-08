package com.contax.message;

import com.contax.message.model.MessageResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class MessageIntegrationTest {

    private static final String MESSAGES_API_PATH = "/messages";
    private static final String MESSAGE_REQUEST = "{\"content\":\"test message\"}";
    private static final String UPDATED_MESSAGE_REQUEST = "{\"content\":\"update test message\"}";

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new JsonMapper().registerModule(new JSR310Module());

    @Test
    public void testAddMessage() throws Exception {
        MvcResult result = mockMvc.perform(post(MESSAGES_API_PATH).content(MESSAGE_REQUEST).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        MessageResponse message = objectMapper.readValue(json, MessageResponse.class);
        assertNotNull(message);
        assertEquals("test message", message.getContent());
    }

    @Test
    public void testGetMessages() throws Exception {
        MvcResult result = mockMvc.perform(get(MESSAGES_API_PATH))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, MessageResponse.class);
        List<MessageResponse> message = objectMapper.readValue(json, collectionType);
        assertNotNull(message);
        assertEquals(1, message.size());
    }

    @Test
    public void testUpdateMessage() throws Exception {
        MvcResult result = mockMvc.perform(put(MESSAGES_API_PATH + "/1").content(UPDATED_MESSAGE_REQUEST).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        MessageResponse message = objectMapper.readValue(json, MessageResponse.class);
        assertNotNull(message);
        assertEquals("update test message", message.getContent());
    }

    @Test
    public void testGetMessage() throws Exception {
        MvcResult result = mockMvc.perform(get(MESSAGES_API_PATH + "/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        MessageResponse message = objectMapper.readValue(json, MessageResponse.class);
        assertNotNull(message);
        assertEquals(1, message.getId());
    }

    @Test
    public void testRemoveMessage() throws Exception {
        mockMvc.perform(post(MESSAGES_API_PATH).content(MESSAGE_REQUEST).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(delete(MESSAGES_API_PATH + "/2"))
                .andExpect(status().isNoContent());
    }

}
