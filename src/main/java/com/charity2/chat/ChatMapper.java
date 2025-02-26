package com.charity2.chat;

import org.springframework.stereotype.Service;

@Service
public class ChatMapper {
    public ChatResponse toChatResponse(Chat chat, String senderId) {
        return ChatResponse.builder()
                .id(chat.getId())
                .name(chat.getChatName(senderId))
                .unreadCount(chat.getUnreadMessageCount(senderId))
                .lastmessage(chat.getLastMessage())
                .isRecipientOnline(chat.getRecipient().isUsreOnline())
                .senderId(String.valueOf(chat.getSender().getId()))
                .receicerId(String.valueOf(chat.getRecipient().getId()))
                .build();
    }
}
