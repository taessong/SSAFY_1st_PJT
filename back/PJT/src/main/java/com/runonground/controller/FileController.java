package com.runonground.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${file.upload-dir}")
    private String uploadDir;
    
    @Value("${file.postUpload-dir}")
    private String postUploadDir;

    @PostMapping("/profileUpload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename(); // 고유한 파일 이름 생성
            Path filePath = Paths.get(uploadDir, fileName);
            Files.copy(file.getInputStream(), filePath);

            String fileUrl = "http://localhost:8080/uploads/" + fileName;
            return new ResponseEntity<>(fileUrl, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("파일 업로드 실패!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/postfileUpload")
    public ResponseEntity<String> uploadPostFile(@RequestParam("postFile") MultipartFile postFile){
    	try {
    		//파일 이름 지정 
			String fileName = UUID.randomUUID().toString() + "_" + postFile.getOriginalFilename();
			Path postFilePath = Paths.get(postUploadDir, fileName);
			Files.copy(postFile.getInputStream(), postFilePath);
			
			String postFileUrl = "http://localhost:8080/postUploads/" + fileName;
			
			return new ResponseEntity<String>(postFileUrl, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("파일 업로드 실패요!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    
}
