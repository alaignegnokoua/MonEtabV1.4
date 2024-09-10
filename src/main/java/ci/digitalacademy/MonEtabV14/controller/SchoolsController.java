package ci.digitalacademy.MonEtabV14.controller;

import ci.digitalacademy.MonEtabV14.services.*;
import ci.digitalacademy.MonEtabV14.services.dto.*;
import ci.digitalacademy.MonEtabV14.services.impl.FileStorgeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/schools")
public class SchoolsController {

    private final SchoolService schoolService;
    private final AppSettingService appSettingService;
    private final AppService appService;
    private final UserService userService;
    private final RoleUserService roleUserService;
    private final FileStorgeServiceImpl fileStorgeServiceImpl;

    @GetMapping
    public String showAddSchool(Model model) {
        log.debug("Request to show add school page");

        Optional<SchoolDTO> schoolDTO = schoolService.findAll().stream().findFirst();
        if (schoolDTO.isPresent()) {
            return "redirect:/login";
        }
        model.addAttribute("school", new RegistrationSchoolDTO());
        return "schools/forms";
    }

    @PostMapping
    public String saveSchool(@ModelAttribute RegistrationSchoolDTO schoolDTO) throws IOException {
        log.debug("Request to save school {}", schoolDTO);
        String upload = fileStorgeServiceImpl.upload(schoolDTO.getFile());
        AppSettingDTO appSettingDTO = appSettingService.findAll().get(0);
        schoolDTO.setUrlLogo(upload);
        appService.initSchool(schoolDTO, appSettingDTO);

        RoleUserDTO roleUserDTO = new RoleUserDTO();
        roleUserDTO.setRole("ADMIN");

        appService.initRoles(createRoleUser());
        userService.initUser(createdUser());

        return "redirect:/login";
    }

    public List<RoleUserDTO> createRoleUser() {
        List<RoleUserDTO> roleUserDTOList = new ArrayList<>();

        RoleUserDTO roleUserDTO = new RoleUserDTO();
        roleUserDTO.setRole("USER");
        roleUserDTOList.add(roleUserDTO);

        RoleUserDTO roleUserDTO2 = new RoleUserDTO();
        roleUserDTO2.setRole("ADMIN");
        roleUserDTOList.add(roleUserDTO2);

        return roleUserDTOList;
    }

    public List<UserDTO> createdUser() {
        List<UserDTO> userDTOList = new ArrayList<>();
        SchoolDTO schoolDTO = schoolService.findAll().get(0);

        List<RoleUserDTO> roleUserDTOList = roleUserService.findAll();
        List<RoleUserDTO> roleUserAdmin = new ArrayList<>();
        roleUserAdmin.add(roleUserDTOList.get(0));

        List<RoleUserDTO> roleUserUser = new ArrayList<>();
        roleUserUser.add(roleUserDTOList.get(1));

        UserDTO userAdmin = new UserDTO();
        userAdmin.setPseudo("Admin");
        userAdmin.setPassword("admin");
        userAdmin.setCreationDate(Instant.now());
        userAdmin.setSchoolDTO(schoolDTO);
        userAdmin.setRoleUsers(roleUserAdmin);
        userDTOList.add(userAdmin);

        UserDTO userUser = new UserDTO();
        userUser.setPseudo("user");
        userUser.setPassword("user");
        userUser.setCreationDate(Instant.now());
        userUser.setSchoolDTO(schoolDTO);
        userUser.setRoleUsers(roleUserUser);
        userDTOList.add(userUser);

        return userDTOList;
    }

    @GetMapping("/{id}")
    public String updateSchoolsForms(Model model, @PathVariable Long id) {
        log.debug("Request to update school {}", id);

        SchoolDTO schoolDTO = schoolService.findOne(id).get();
        RegistrationSchoolDTO registrationSchool = new RegistrationSchoolDTO();
        registrationSchool.setUrlLogo(schoolDTO.getUrlLogo());
        registrationSchool.setName(schoolDTO.getName());
        registrationSchool.setAppSettingDTO(schoolDTO.getAppSettingDTO());
        model.addAttribute("school", registrationSchool);

        return "/schools/forms";
    }
}
