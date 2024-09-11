package ci.digitalacademy.MonEtabV14.controller;

import ci.digitalacademy.MonEtabV14.services.SchoolService;
import ci.digitalacademy.MonEtabV14.services.StudentService;
import ci.digitalacademy.MonEtabV14.services.dto.SchoolDTO;
import ci.digitalacademy.MonEtabV14.services.dto.StudentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/students")
public class StudentsController {

    private final StudentService studentService;
    private final SchoolService schoolService;

    @GetMapping
    public String showStudentPage(Model model){
        log.debug("Request to show student page");
        List<StudentDTO> students = studentService.findAll();
        model.addAttribute("students", students);

        List<SchoolDTO> schools = schoolService.findAll();
        if (!schools.isEmpty()) {
            Optional<SchoolDTO> schoolDTO = schools.stream().findFirst();
            model.addAttribute("school", schoolDTO.get());
        }

        return "students/list";
    }

    @GetMapping("/add")
    public String showAddStudentPage(Model model) {
        log.debug("Request to show add student page");
        model.addAttribute("student", new StudentDTO());
        return "students/forms";
    }

    @PostMapping
    public String saveStudent(StudentDTO student) {
        log.debug("Request to save student {}", student);
        studentService.save(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}")
    public String showUpdateStudentForms(Model model, @PathVariable Long id) {
        log.debug("Request to update student {}", id);
        Optional<StudentDTO> student = studentService.findOne(id);

        if (student.isPresent()) {
            model.addAttribute("student", student.get());
            return "students/forms";
        } else {
            return "redirect:/students";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        log.debug("Request to delete student {}", id);
        studentService.delete(id);
        return "redirect:/students";

    }

    @GetMapping("/search")
    public String searchStudents(@RequestParam String query, @RequestParam String gender, Model model) {
        log.debug("Request to search student {}", query);
        List<StudentDTO> students = studentService.findByLastNameOrGenderOrMatricule(query, gender);
        model.addAttribute("students", students);
        model.addAttribute("query", query);
        model.addAttribute("gender", gender);
        return "students/list";
    }
}
