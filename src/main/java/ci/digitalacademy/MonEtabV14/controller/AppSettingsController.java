package ci.digitalacademy.MonEtabV14.controller;

import ci.digitalacademy.MonEtabV14.services.AppSettingService;
import ci.digitalacademy.MonEtabV14.services.SchoolService;
import ci.digitalacademy.MonEtabV14.services.dto.AppSettingDTO;
import ci.digitalacademy.MonEtabV14.services.dto.SchoolDTO;
import ci.digitalacademy.MonEtabV14.services.dto.StudentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping
public class AppSettingsController {

    private final AppSettingService appSettingService;
    private final SchoolService schoolService;

    @GetMapping
    public String showAddAppSettingPage(Model model) {
        log.debug("Request to show add App setting page");
        List<AppSettingDTO> appSetting = appSettingService.findAll();
        List<SchoolDTO> schools = schoolService.findAll();

        if (appSetting.isEmpty()) {
            model.addAttribute("appSetting", new AppSettingDTO());
             return "appSettings/forms";
        } else if (schools.isEmpty()) {
            return "redirect:/schools";
        } else {
            return "redirect:/login";
        }

    }

    @PostMapping
    public String saveAppSetting(AppSettingDTO appSettingDTO) {
        log.debug("Request to save app setting {}", appSettingDTO);
        appSettingService.save(appSettingDTO);
        return "redirect:/schools";
    }
}
