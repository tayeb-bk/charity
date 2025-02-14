package com.charity2.Repository;

import com.charity2.entities.Role;
import com.charity2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByRole(Role role);

}
