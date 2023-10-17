package com.zinkworks.gradpetstore.service;

import com.zinkworks.gradpetstore.model.User;

public interface IUserService {
    User createUser(User user);
    User getByUsername(String username);
    Long deleteByUsername(String username);
}
