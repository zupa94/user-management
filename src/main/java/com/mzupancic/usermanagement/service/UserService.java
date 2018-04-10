package com.mzupancic.usermanagement.service;

import com.mzupancic.usermanagement.exception.EmailAlreadyExistException;
import com.mzupancic.usermanagement.exception.UserDoNotExistException;
import com.mzupancic.usermanagement.exception.UsernameAlreadyExistException;
import com.mzupancic.usermanagement.model.User;
import com.mzupancic.usermanagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void addUser(User newUser) {
        User user = new User();
        if (userRepository.existsByEmail(newUser.getEmail())) {
            throw new EmailAlreadyExistException(String.format("Email %s already exists.", newUser.getEmail()));
        }

        if (userRepository.existsByUsername(newUser.getUsername())) {
            throw new UsernameAlreadyExistException(String.format("Username %s already exist.", newUser.getUsername()));
        }

        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));

        userRepository.save(user);
    }

    @Transactional
    public User getUserById(final Long id){

        if(!userRepository.existsById(id)){
            throw new UserDoNotExistException(String.format("User with id %s do not exist.", id));
        }

        return userRepository.findOneById(id);
    }

    @Transactional
    public void modifyUser(User modifiedUser){

        if(!userRepository.existsById(modifiedUser.getId())){
            throw new UserDoNotExistException(String.format("User with id %s do not exist.", modifiedUser.getId()));
        }

        User user = userRepository.findOneById(modifiedUser.getId());

        user.setFirstName(modifiedUser.getFirstName());
        user.setLastName(modifiedUser.getLastName());
        user.setUsername(modifiedUser.getUsername());
        user.setEmail(modifiedUser.getEmail());

        userRepository.save(user);

    }

    @Transactional
    public void deleteUser(String username){

        if(!userRepository.existsByUsername(username)){
            throw new UserDoNotExistException(String.format("User with username %s do not exist.", username));
        }

        userRepository.deleteByUsername(username);
    }
}
