package com.readmultiplefiles.app.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.readmultiplefiles.app.model.Student;
import com.readmultiplefiles.app.repository.StudentRepository;
import com.readmultiplefiles.app.utils.ConvertExcelService;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	private static final String OUTPUT_FILE_NAME = "C:\\Users\\233748\\student_details.xlsx";
    private static final String[] HEADERS = { "Student ID", "Student Name", "Physics", "Chemistry", "Maths" };
//    // Get the project directory
//    String projectDirectory = System.getProperty("user.dir");
//
//    // Set the output file path relative to the project directory
//    String outputPath = projectDirectory + File.separator + OUTPUT_FILE_NAME;

	public void parseStudentDetails(MultipartFile file) {
		try {
			List<Student> students= ConvertExcelService.convertExcelToListOfStudents(file.getInputStream());
			studentRepository.saveAll(students);
			integrateExcelFiles();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Student> getAllStudents(){
		return studentRepository.findAll(); 
	}
	
	public void integrateExcelFiles() {
		List<Student> students=this.getAllStudents();
		
		Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Student Details");

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < HEADERS.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(HEADERS[i]);
        }

        int rowNum = 1;
        for (Student student : students) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(student.getStudentid());
            row.createCell(1).setCellValue(student.getStudentname());
            row.createCell(2).setCellValue(student.getPhysics());
            row.createCell(3).setCellValue(student.getChemistry());
            row.createCell(4).setCellValue(student.getMaths());
        }
        
        try (FileOutputStream outputStream = new FileOutputStream(OUTPUT_FILE_NAME)) {
            workbook.write(outputStream);
        } catch (IOException e) {
           
        }
	}
}
