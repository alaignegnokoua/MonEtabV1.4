package ci.digitalacademy.MonEtabV14.controller;

import ci.digitalacademy.MonEtabV14.services.ReportService;
import ci.digitalacademy.MonEtabV14.services.SchoolService;
import ci.digitalacademy.MonEtabV14.services.dto.SchoolDTO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;
    private final SchoolService schoolService;

    @GetMapping
    public String showRapportPage(Model model) {

        List<SchoolDTO> schools = schoolService.findAll();
        if (!schools.isEmpty()) {
            Optional<SchoolDTO> schoolDTO = schools.stream().findFirst();
            model.addAttribute("school", schoolDTO.get());
        }
        return "report/default";
    }

    @GetMapping("/excel")
    public void generateRapport(@RequestParam String query, HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream"); //octet-stream
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=Reports.xls"; //courses.xls

        response.setHeader(headerKey, headerValue);

        if(query.equals("student")){
            reportService.generateReportExcelStudent(response);
        }else if(query.equals("teacher")){
            reportService.generateReportExcelTeacher(response);
        }else if(query.equals("user")){
            reportService.generateReportExcelUser(response);
        }

    }
}
