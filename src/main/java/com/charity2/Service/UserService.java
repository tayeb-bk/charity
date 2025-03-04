package com.charity2.Service;

import com.charity2.entities.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();// Récupérer tous les utilisateurs

    Optional<User> getUserById(Long id);  // Récupérer un utilisateur par ID


    User saveUser(User user);  // Enregistrer un nouvel utilisateur

    void deleteUser(Long id);  // Supprimer un utilisateur

    public User updateUser(User user);


}
