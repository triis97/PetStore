package com.zinkworks.gradpetstore.service;

import com.zinkworks.gradpetstore.model.User;
import com.zinkworks.gradpetstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username).get(0);
    }

    @Override
    public Long deleteByUsername(String username) {
        return userRepository.deleteByUsername(username);
    }
}
