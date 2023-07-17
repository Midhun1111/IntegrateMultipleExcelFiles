package com.readmultiplefiles.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.readmultiplefiles.app.services.StudentService;

@RestController
public class StudentController {
	@Autowired
	StudentService studentService;
	
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam MultipartFile[] files) throws IOException{
		for(MultipartFile file:files) {
			studentService.parseStudentDetails(file);
		}
		
		return ResponseEntity.ok().body("File Uploaded Successfully");
	}
}
