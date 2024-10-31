package com.shailendra.writerous.service;

import com.shailendra.writerous.dto.RegistrationDto;
import com.shailendra.writerous.entity.User;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    User findByEmail(String email);
}
