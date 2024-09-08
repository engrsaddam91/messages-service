package com.contax.message.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
    private long id;
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
