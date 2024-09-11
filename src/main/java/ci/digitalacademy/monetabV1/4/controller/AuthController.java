package ci.digitalacademy.MonEtabV14.controller;

import ci.digitalacademy.MonEtabV14.services.SchoolService;
import ci.digitalacademy.MonEtabV14.services.dto.SchoolDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    private final SchoolService schoolService;

    @GetMapping
    public String showLoginPage(Model model) {

        List<SchoolDTO> schools = schoolService.findAll();
        if (!schools.isEmpty()) {
            Optional<SchoolDTO> schoolDTO = schools.stream().findFirst();
            model.addAttribute("school", schoolDTO.get());
        }
        return "auth/login";
    }
}
