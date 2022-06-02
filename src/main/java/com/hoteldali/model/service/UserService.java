package com.hoteldali.model.service;

import com.hoteldali.data.UserRepository;
import com.hoteldali.model.UserDto;
import com.hoteldali.model.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    
    private final UserRepository userRepo;
	
	@Autowired
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByNickname(username);
		if (user != null) {
			System.out.print("Load nickname : ");
			System.out.println(user.getNickname());
			return user;
		}
        else {
            System.out.println("Not found");
            return null;
        }
	}

    public boolean registerMember(User user) throws Exception {
        User member = userRepo.findByNickname(user.getNickname());
        if (member == null) {
            userRepo.save(user);
            return true;
        }
        else {
            System.out.println("Duplicate");
            return false;
        }
    }

    public boolean deleteMember(String nickname) throws Exception {
        User user = userRepo.findByNickname(nickname);
        if (user != null) {
            userRepo.delete(user);
            return true;
        }
        else {
            System.out.println("Not found");
            return false;
        }
    }

    public boolean checkNickname(String nickname) throws Exception {
        User user = userRepo.findByNickname(nickname);
        if (user != null) return true;
        else return false;
    }

    public boolean checkEmail(String email) throws Exception {
        User user = userRepo.findByEmail(email);
        if (user != null) return true;
        else return false;
    }

    public boolean checkPhone(String phone) throws Exception {
        User user = userRepo.findByPhone(phone);
        if (user != null) return true;
        else return false;
    }

    public UserDto login(String email) throws Exception {
        User member = userRepo.findByEmail(email);
        if (member != null) return new UserDto(member);
        else return null;
    }

/*
    public boolean updateMember(User user) throws Exception {
        User member = userRepo.findByNickname(user.getNickname());
        if (user != null) {
            userRepo.(user);
            return true;
        }
        else {
            System.out.println("Not found");
            return false;
        }
    }
*/

  
}
