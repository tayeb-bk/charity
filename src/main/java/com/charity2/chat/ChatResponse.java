package com.charity2.chat;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatResponse {
    private String id;
    private String name;
    private Long unreadCount;
    private String lastmessage;
    private LocalDateTime lastMessageTime;
    private Boolean isRecipientOnline;
    private String senderId;
    private String receicerId;
}
