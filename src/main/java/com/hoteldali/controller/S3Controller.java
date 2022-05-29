package com.hoteldali.controller;

import com.hoteldali.util.S3Uploader;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/s3")
@Api("S3 컨트롤러 API")
public class S3Controller {

    private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

    private final S3Uploader s3Uploader;

    @ApiOperation(value = "유저 프로필 업로드", notes = "유저 프로필 이미지 업로드", response = String.class)
    @PostMapping("/upload/profile")
    public ResponseEntity<String> updateUserImage(@RequestPart("images") MultipartFile multipartFile) {
        try {
            s3Uploader.uploadFiles(multipartFile, "user_imgs/profile");
        } catch (Exception e) { 
            return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }
}