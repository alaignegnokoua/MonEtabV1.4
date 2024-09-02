package ci.digitalacademy.MonEtabV14.controller;

import ci.digitalacademy.MonEtabV14.services.TeacherService;
import ci.digitalacademy.MonEtabV14.services.dto.TeacherDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/teachers")
public class TeachersController {

    private final TeacherService teacherService;

    @GetMapping
    public String showTeacherPage(Model model){
        List<TeacherDTO> teachers = teacherService.findAll();
        model.addAttribute("teachers", teachers);
        return "teachers/list";
    }

    @GetMapping("/add")
    public String showAddTeacherForms(Model model) {
        log.debug("Request to show add teacher forms");
        model.addAttribute("teacher", new TeacherDTO());
        return "teachers/forms";
    }

    @PostMapping
    public String saveTeacher(TeacherDTO teacher) {
        log.debug("Request to save teacher : {}", teacher);
        teacherService.save(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/{id}")
    public String showUpdateTeacherForms(Model model, @PathVariable Long id) {
        Optional<TeacherDTO>  teacher = teacherService.findOne(id);

        if (teacher.isPresent()) {
            model.addAttribute("teacher", teacher.get());
            return "teachers/forms";
        } else {
            return "redirect:/teachers";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        log.debug("Request to delete teacher {}", id);
        teacherService.delete(id);
        return "redirect:/teachers";
    }
}
