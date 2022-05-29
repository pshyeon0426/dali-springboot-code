package com.hoteldali.model;

import com.hoteldali.model.domain.User;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {  

    private String email;
    private String password;

    public User toEntity() {
        return new User(email, password, "", "", "", 0, "", "");
    }

    public User toEntityWithPasswordEncode(PasswordEncoder bCryptPasswordEncoder) {
        return new User(email, bCryptPasswordEncoder.encode(password), "", "", "", 0, "", "");
    }
}
