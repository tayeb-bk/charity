package com.charity2.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, String> {
    @Query(name = ChatConstant.FIND_CHAT_BY_SENDER_ID)
    List<Chat> findChatBySenderId(@Param("senderId") String userId);


    @Query(name = ChatConstant.FIND_CHAT_BY_SENDER_ID_AND_RECEIVER)
    Optional<Chat> findChatByReceiverAndSender(@Param("senderId") String senderId,@Param("recipientId") String receiverId);
}
