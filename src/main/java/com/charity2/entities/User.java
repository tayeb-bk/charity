package com.charity2.entities;

import com.charity2.chat.Chat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name = UserConstants.FIND_USER_BY_EMAIL, query="SELECT u FROM User u WHERE u.email = : email")
@NamedQuery(name = UserConstants.FIND_ALL_USERS_EXCEPT_SELF,
              query = "SELECT u FROM  User u WHERE u.id != :publicId")
@NamedQuery(name = UserConstants.FIND_USER_BY_PUBLIC_ID,
               query = "SELECT u FROM  User u WHERE u.id = :publicId")
public class User extends BaseAuditing implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique=true)
    private String email;
    private String password;
    private boolean enabled;
    @Enumerated(EnumType.STRING)
    private Role role;

    private String phonenumber;

    private String image;
    private String passwordResetToken;



    private LocalDateTime lastSeen;



    @OneToMany(mappedBy = "sender")
    private List<Chat> chatsAsSender;
    @OneToMany(mappedBy = "recipient")
    private List<Chat> chatsAsRecipient;
    private static final int LAST_ACTIVATE_INTERVAL =5;

    @Transient
    public boolean isUsreOnline(){
        //lastSeen=10:05
        //now 10:09-->online
        //now 10:12-->offline
        return lastSeen != null && lastSeen.isAfter(LocalDateTime.now().minusMinutes(LAST_ACTIVATE_INTERVAL));
    }











    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true ;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    private String fullName(){
        return firstName + " " + lastName;
    }
}
