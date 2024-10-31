package com.shailendra.writerous.repository;

import com.shailendra.writerous.entity.Post;
import com.shailendra.writerous.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
