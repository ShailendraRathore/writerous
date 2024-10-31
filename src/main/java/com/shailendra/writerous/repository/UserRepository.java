package com.shailendra.writerous.repository;

import com.shailendra.writerous.entity.Post;
import com.shailendra.writerous.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
        User findByEmail(String email);
}
