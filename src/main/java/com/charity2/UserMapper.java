package com.charity2;

import com.charity2.Service.UserResponse;
import com.charity2.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserMapper {
    public User fromTokenAttributes(Map<String, Object> attributes) {
        User user = new User();

        if (attributes.containsKey("sub")) {
            user.setId(Long.valueOf(attributes.get("sub").toString()));
        }
        if (attributes.containsKey("given_name")) {
            user.setFirstName(attributes.get("given_name").toString());
        }else if (attributes.containsKey("nickname")) {
            user.setFirstName(attributes.get("nickname").toString());
        }
        if (attributes.containsKey("family_name")) {
            user.setLastName(attributes.get("family_name").toString());
        }
        if (attributes.containsKey("email")) {
            user.setEmail(attributes.get("email").toString());
        }

        user.setLastSeen(LocalDateTime.now());

        return user;
    }

    public UserResponse toUserResponse(User user) {
    return UserResponse.builder()
            .id(String.valueOf(user.getId()))
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .lastSeen(user.getLastSeen())
            .isOnline(user.isUsreOnline())
            .build();
    }
}
