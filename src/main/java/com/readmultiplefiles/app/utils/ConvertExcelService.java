package com.readmultiplefiles.app.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import com.readmultiplefiles.app.model.Student;

@Service
public class ConvertExcelService {
	private static List<Student> studentDetails=new ArrayList<>();

	public static List<Student> convertExcelToListOfStudents(InputStream excelData){
		try {
			Workbook workbook=new XSSFWorkbook(excelData);

			for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
				Sheet sheet= workbook.getSheetAt(sheetIndex);
				Row headerRow=sheet.getRow(0);
				Map<String,Integer> columnIndexMap=new HashMap<>();
				for(Cell cell:headerRow) {
					String columnHeader=cell.getStringCellValue().toLowerCase().trim();
					if(!columnIndexMap.containsKey(columnHeader)) {
						columnIndexMap.put(columnHeader, cell.getColumnIndex());
					}
				}

				for(int rowNum=1;rowNum<=sheet.getLastRowNum();rowNum++) {
					Row row=sheet.getRow(rowNum);
					Student student=new Student();

					if (columnIndexMap.containsKey("studentid") || columnIndexMap.containsKey("id")) {
						Cell studentidCell=row.getCell(columnIndexMap.get("studentid"));
						if(studentidCell==null) {
							studentidCell=row.getCell(columnIndexMap.get("id"));
						}
						if (studentidCell != null && studentidCell.getCellType() == CellType.NUMERIC) {
							student.setStudentid((long)studentidCell.getNumericCellValue());
						}
					}

					if (columnIndexMap.containsKey("studentname") || columnIndexMap.containsKey("name")) {
						Integer studentNameColumnIndex=columnIndexMap.get("studentname");
						if(studentNameColumnIndex==null) {
							studentNameColumnIndex=columnIndexMap.get("name");
						}
						if(studentNameColumnIndex!=null) {
							Cell studentNameCell=row.getCell(studentNameColumnIndex);		
							if (studentNameCell != null && studentNameCell.getCellType() == CellType.STRING) {
								student.setStudentname(studentNameCell.getStringCellValue());
							}
						}
					}


					if (columnIndexMap.containsKey("physics") || columnIndexMap.containsKey("phy")) {
						Integer physicsColumnIndex=columnIndexMap.get("physics");
						if(physicsColumnIndex==null) {
							physicsColumnIndex=columnIndexMap.get("phy");
						}
						if(physicsColumnIndex!=null) {
							Cell physicsCell=row.getCell(physicsColumnIndex);
							if (physicsCell != null && physicsCell.getCellType() == CellType.NUMERIC) {
								student.setPhysics(physicsCell.getNumericCellValue());
							}
						}
					}

					if (columnIndexMap.containsKey("chemistry") || columnIndexMap.containsKey("chem")) {
						Integer chemistryColumnIndex=columnIndexMap.get("chemistry");
						if(chemistryColumnIndex==null) {
							chemistryColumnIndex=columnIndexMap.get("chem");
						}
						if(chemistryColumnIndex!=null) {
							Cell chemistryCell=row.getCell(chemistryColumnIndex);
							if (chemistryCell != null && chemistryCell.getCellType() == CellType.NUMERIC) {
								student.setChemistry(chemistryCell.getNumericCellValue());
							}
						}
					}

					if (columnIndexMap.containsKey("maths")) {
						Cell mathsCell=row.getCell(columnIndexMap.get("maths"));
						if (mathsCell != null && mathsCell.getCellType() == CellType.NUMERIC) {
							student.setMaths(mathsCell.getNumericCellValue());
						}
					}

					studentDetails.add(student);	
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return studentDetails;
	}
}
