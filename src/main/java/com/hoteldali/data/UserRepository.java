package com.hoteldali.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hoteldali.model.domain.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String> {
	User findByEmail(String email);
	User findByNickname(String nickname);
	User findByUsername(String username);
	User findByPhone(String phone);
	User findByEmailAndPassword(String email, String password);
}