package ci.digitalacademy.MonEtabV14.services.impl;

import ci.digitalacademy.MonEtabV14.services.ReportService;
import ci.digitalacademy.MonEtabV14.services.StudentService;
import ci.digitalacademy.MonEtabV14.services.TeacherService;
import ci.digitalacademy.MonEtabV14.services.UserService;
import ci.digitalacademy.MonEtabV14.services.dto.*;
import ci.digitalacademy.MonEtabV14.services.dto.ExcelStudentDTO;
import ci.digitalacademy.MonEtabV14.services.dto.ExcelTeacherDTO;
import ci.digitalacademy.MonEtabV14.services.dto.ExcelUserDTO;
import ci.digitalacademy.MonEtabV14.services.dto.StudentDTO;
import ci.digitalacademy.MonEtabV14.services.dto.TeacherDTO;
import ci.digitalacademy.MonEtabV14.services.dto.UserDTO;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final UserService userService;

    @Override
    public void generateReportExcelStudent(HttpServletResponse response) throws IOException {

        List<ExcelStudentDTO> excelStudentDTOS = new ArrayList<>();
        List<StudentDTO> studentDTOS = studentService.findAll();

        studentDTOS.forEach(studentDTO -> {
            ExcelStudentDTO excelStudentDTO = new ExcelStudentDTO();
            excelStudentDTO.setFirstName(studentDTO.getFirstName());
            excelStudentDTO.setLastName(studentDTO.getLastName());
            excelStudentDTO.setNumbers(studentDTO.getPhoneNumber());
            excelStudentDTO.setMatricule(studentDTO.getMatricule());

            excelStudentDTOS.add(excelStudentDTO);
        });

        // 1) creation du workbook
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 2 ) creation de la feuille de style excel
        HSSFSheet sheet = workbook.createSheet("Student Report");


        // 3) creation de la premiere ligne
        HSSFRow row = sheet.createRow(0);

        // Creation des cellules d'en-tete de notre feuille de style Excel
        row.createCell(0).setCellValue("MATRICLE");
        row.createCell(1).setCellValue("NOM");
        row.createCell(2).setCellValue("PRENOM");
        row.createCell(3).setCellValue("TELEPHONE");

        int dataRowIndex = 1;

        for (ExcelStudentDTO student : excelStudentDTOS) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(student.getMatricule());
            dataRow.createCell(1).setCellValue(student.getFirstName());
            dataRow.createCell(2).setCellValue(student.getLastName());
            dataRow.createCell(3).setCellValue(student.getNumbers());

            dataRowIndex++;
        }

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    @Override
    public void generateReportExcelTeacher(HttpServletResponse response) throws IOException {

        List<ExcelTeacherDTO> excelTeacherDTOS = new ArrayList<>();
        List<TeacherDTO> teacherDTOS = teacherService.findAll();

        teacherDTOS.forEach(teacherDTO -> {
            ExcelTeacherDTO excelTeacherDTO = new ExcelTeacherDTO();
            excelTeacherDTO.setFirstName(teacherDTO.getFirstName());
            excelTeacherDTO.setLastName(teacherDTO.getLastName());
            excelTeacherDTO.setNumbers(teacherDTO.getPhoneNumber());
            excelTeacherDTO.setAvailable(teacherDTO.getAvailable());
            excelTeacherDTO.setSpecialty(teacherDTO.getSpecialty());

            excelTeacherDTOS.add(excelTeacherDTO);
        });

        // 1) creation du workbook
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 2 ) creation de la feuille de style excel
        HSSFSheet sheet = workbook.createSheet("Teacher Report");


        // 3) creation de la premiere ligne
        HSSFRow row = sheet.createRow(0);

        // Creation des cellules d'en-tete de notre feuille de style Excel
        row.createCell(0).setCellValue("SPECIALITE");
        row.createCell(1).setCellValue("NOM");
        row.createCell(2).setCellValue("PRENOM");
        row.createCell(3).setCellValue("TELEPHONE");
        row.createCell(4).setCellValue("VACANT");

        int dataRowIndex = 1;

        for (ExcelTeacherDTO teacher : excelTeacherDTOS) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(teacher.getSpecialty());
            dataRow.createCell(1).setCellValue(teacher.getFirstName());
            dataRow.createCell(2).setCellValue(teacher.getLastName());
            dataRow.createCell(3).setCellValue(teacher.getNumbers());
            dataRow.createCell(4).setCellValue(teacher.getAvailable());

            dataRowIndex++;
        }

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    @Override
    public void generateReportExcelUser(HttpServletResponse response) throws IOException {
        List<ExcelUserDTO> excelUserDTOS = new ArrayList<>();
        List<UserDTO> userDTOS = userService.findAll();

        userDTOS.forEach(userDTO -> {
            ExcelUserDTO excelUserDTO = new ExcelUserDTO();
            excelUserDTO.setPseudo(userDTO.getPseudo());
            excelUserDTO.setCreatedDate(userDTO.getCreationDate());

            excelUserDTOS.add(excelUserDTO);
        });

        // 1) creation du workbook
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 2 ) creation de la feuille de style excel
        HSSFSheet sheet = workbook.createSheet("User Report");


        // 3) creation de la premiere ligne
        HSSFRow row = sheet.createRow(0);

        // Creation des cellules d'en-tete de notre feuille de style Excel ( ligne 1 )
        row.createCell(0).setCellValue("PSEUDO");
        row.createCell(1).setCellValue("DATE DE CREATION");

        int dataRowIndex = 1;

        for (ExcelUserDTO user : excelUserDTOS) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(user.getPseudo());
            dataRow.createCell(1).setCellValue(java.util.Date.from(user.getCreatedDate()));

            dataRowIndex++;
        }

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
