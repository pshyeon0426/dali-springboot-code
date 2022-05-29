package com.hoteldali.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.hoteldali.model.domain.User;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    
    @Email
    private String email;
    @NotBlank
    @Size(min = 8, max = 30)
    private String password;
    @NotBlank
    @Size(min = 3, max = 20)
    private String nickname;
    private String username;
    private String phone;
    private int gender;
    private String birth;
    private String address;


    public User toEntity() {
        return new User(email, password, nickname, username, phone, gender, birth, address);
    }

    public User toEntityWithPasswordEncode(PasswordEncoder bCryptPasswordEncoder) {
        return new User(email, bCryptPasswordEncoder.encode(password), nickname, username, phone, gender, birth, address);
    }

    public UserDto(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.nickname = user.getNickname();
        this.username = user.getUsername();
        this.phone = user.getPhone();
        this.gender = user.getGender();
        this.birth = user.getBirth();
        this.address = user.getAddress();
    }
    
}
