package com.charity2.chat;

import com.charity2.entities.BaseAuditing;
import com.charity2.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chat")
@NamedQuery(name = ChatConstant.FIND_CHAT_BY_SENDER_ID,
            query = "SELECT DISTINCT c FROM Chat c WHERE c.sender.id = :senderId OR c.recipient.id = :senderId ORDER BY createdDate DESC ")

@NamedQuery(name = ChatConstant.FIND_CHAT_BY_SENDER_ID_AND_RECEIVER,
              query = "SELECT DISTINCT c FROM Chat c WHERE (c.sender.id = :senderId AND c.recipient.id = :recipientId) OR (c.sender.id = :recipientId AND c.recipient.id = :senderId)")

public class Chat extends BaseAuditing {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender ;
    @ManyToOne
    @JoinColumn(name = "recepient_id")
    private User recipient ;


    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER)
    @OrderBy("createdDate DESC ")
    public List<Message> messages;

    @Transient
    public String getChatName(final String senderId) {
        if (recipient.getId().equals(senderId)) {
            return sender.getFirstName() + " " + sender.getLastName();
        }
        return recipient.getFirstName() + " " + recipient.getLastName();

    }

    @Transient
    public long getUnreadMessageCount(final String senderId) {
        return messages.
                    stream().
                filter(m ->m.getReceiverId().equals(senderId)).
                filter(m -> MessageState.SENT == m.getState()).count();
    }

    @Transient
    public String getLastMessage(){
        if (messages != null && !messages.isEmpty()) {
            if (messages.get(0).getType() != MessageType.TEXT   ) {
                return "ATTACHEMENT";
            }
            return messages.get(0).getContent();
        }
        return null;
    }

    @Transient
    public LocalDateTime getLastMessageTime(){
        if (messages != null && !messages.isEmpty()) {
            return messages.get(0).getCreatedDate();
        }
        return null;
    }



}
