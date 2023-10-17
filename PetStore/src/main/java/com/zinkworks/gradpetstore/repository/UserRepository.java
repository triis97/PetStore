package com.zinkworks.gradpetstore.repository;

import com.zinkworks.gradpetstore.model.Pet;
import com.zinkworks.gradpetstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Transactional
    Long deleteByUsername(String username);
    List<User> getByUsername(String username);
}
