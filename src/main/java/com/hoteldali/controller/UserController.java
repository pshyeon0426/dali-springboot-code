package com.hoteldali.controller;

import java.util.HashMap;
import java.util.Map;

import com.hoteldali.model.LoginDto;
import com.hoteldali.model.UserDto;
import com.hoteldali.model.service.JwtService;
import com.hoteldali.model.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
@Api("사용자 컨트롤러 API")
public class UserController {
    
    private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
    private static final String ERROR = "error case";

    private JwtService jwtService;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(이메일, 비밀번호).", required = true) LoginDto loginDto) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        try {
			UserDto loginUser = userService.login(loginDto.getEmail());
			if (loginUser != null && passwordEncoder.matches(loginDto.getPassword(), loginUser.getPassword())) {
                System.out.println("user 로그인 성공");
			    String token = jwtService.create("nickname", loginUser.getNickname(), "access-token"); // key, data, subject
                //		logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
                System.out.println("user 로그인 실패 : " + passwordEncoder.encode(loginDto.getPassword()));
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
		//	logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "회원가입", notes = "회원가입 결과 리턴", response = String.class)
	@PostMapping("/register")
	public ResponseEntity<String> registerMember (
			@RequestBody @ApiParam(value = "회원 가입 정보(닉네임, 이름, 비밀번호, 이메일, 성별, 나이, 전화번호, 생년월일).", required = true) UserDto userDto) {
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			if (userService.loadUserByUsername(userDto.getNickname()) == null && userService.registerMember(userDto.toEntityWithPasswordEncode(passwordEncoder))) {
				System.out.println("회원가입 성공 : " + userDto.getNickname());
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
			else {
				System.out.println("회원가입 실패 : " + userDto.getNickname());
				return new ResponseEntity<String>(ERROR, status);
			}
		} catch (Exception e) {
			System.out.println("서버 오류로 인한 회원가입 실패 : " + userDto.getNickname() + e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<String>(FAIL, status);
		}
	}

    @ApiOperation(value = "회원탈퇴", notes = "회원탈퇴 결과 리턴", response = String.class)
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteMember (
			@RequestBody @ApiParam(value = "닉네임", required = true) String nickname) {
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			if (userService.deleteMember(nickname)) {
				System.out.println("회원탈퇴 성공 : " + nickname);
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
			else {
				System.out.println("회원탈퇴 실패 : " + nickname);
				return new ResponseEntity<String>(ERROR, status);
			}
		} catch (Exception e) {
			System.out.println("서버 오류로 인한 회원탈퇴 실패 : " + nickname);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<String>(FAIL, status);
		}
	}

    @ApiOperation(value = "이메일 유일 여부", notes = "이메일 유일 여부 리턴", response = String.class)
	@PostMapping("/check/email")
	public ResponseEntity<String> checkEmail (
			@RequestBody @ApiParam(value = "이메일", required = true) String email) {
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			if (userService.checkEmail(email)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>(ERROR, status);
			}
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<String>(FAIL, status);
		}
	}

    @ApiOperation(value = "닉네임 유일 여부", notes = "닉네임 유일 여부 리턴", response = String.class)
	@PostMapping("/check/nickname")
	public ResponseEntity<String> checkNickname (
			@RequestBody @ApiParam(value = "닉네임", required = true) String nickname) {
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			if (userService.checkNickname(nickname)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>(ERROR, status);
			}
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<String>(FAIL, status);
		}
	}

    @ApiOperation(value = "전화번호 중복 체크", notes = "전화번호 중복 여부 리턴", response = String.class)
	@PostMapping("/check/phone")
	public ResponseEntity<String> checkPhone (
			@RequestBody @ApiParam(value = "전화번호", required = true) String phone) {
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			if (userService.checkPhone(phone)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>(ERROR, status);
			}
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<String>(FAIL, status);
		}
	}


/*
    @ApiOperation(value = "회원정보 수정", notes = "회원정보 수정을 한다", response = String.class)
	@PostMapping("/update")
    public ResponseEntity<String> updateMember(
			@RequestBody @ApiParam(value = "회원 수정 정보(이름, 비밀번호, 이메일).", required = true) User user) {
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			User member = (User)userService.loadUserByUsername(user.getNickname());
			if (user != null && userService.updateMember(member)) {
				//logger.info("회원정보 수정 성공 : {}", memberDto.getUserid());
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
			else {
				//logger.error("회원정보 수정 실패 : {} 없음", user.getUserid());
			}
		} catch (Exception e) {
			//logger.error("회원정보 수정 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<String>(FAIL, status);
	}   
*/  
}
