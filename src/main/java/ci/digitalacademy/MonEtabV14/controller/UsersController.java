package ci.digitalacademy.MonEtabV14.controller;

import ci.digitalacademy.MonEtabV14.services.RoleUserService;
import ci.digitalacademy.MonEtabV14.services.SchoolService;
import ci.digitalacademy.MonEtabV14.services.UserService;
import ci.digitalacademy.MonEtabV14.services.dto.RoleUserDTO;
import ci.digitalacademy.MonEtabV14.services.dto.SchoolDTO;
import ci.digitalacademy.MonEtabV14.services.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;
    private final RoleUserService roleUserService;
    private final SchoolService schoolService;

    @GetMapping
    public String showUsersPage(Model model) {
        log.debug("Request to show users page");
        List<UserDTO> users = userService.findAll();
        List<RoleUserDTO> roles = roleUserService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);

        List<SchoolDTO> schools = schoolService.findAll();
        if (!schools.isEmpty()) {
            Optional<SchoolDTO> schoolDTO = schools.stream().findFirst();
            model.addAttribute("school", schoolDTO.get());
        }

        return "users/list";
    }

    @GetMapping("/add")
    public String showUsersAddPage(Model model) {
        log.debug("Request to show users add page");

        UserDTO userDTO = new UserDTO();
        List<RoleUserDTO> rolesUsers = new ArrayList<>();
        userDTO.setSchoolDTO(new SchoolDTO());
        userDTO.setRoleUsers(rolesUsers);

        model.addAttribute("user", userDTO);
        model.addAttribute("roles", roleUserService.findAll());
        model.addAttribute("action", "add");
        return "users/forms";
    }

    @PostMapping
    public String saveUser(UserDTO user) {
        log.debug("Request to save user {}", user);
        user.setCreationDate(Instant.now());
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String updateUserForms(Model model, @PathVariable Long id) {
        log.debug("Request to update user {}", id);

        Optional<UserDTO> user = userService.findOne(id);

        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "users/forms";
        } else {
            return "redirect:/users";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        log.debug("Request to delete user {}", id);
        userService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/search")
    public String searchUsers(@RequestParam LocalDate creationDate, @RequestParam String role, Model model) {
        log.debug("Request to search users {}", creationDate);
        List<UserDTO> users = userService.findByCreationDateLessThanAndRoleUsersRole(Instant.from(creationDate.atStartOfDay(ZoneOffset.systemDefault())), role);
        List<RoleUserDTO> roles = roleUserService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("creationDate", creationDate);
        model.addAttribute("role", role);
        model.addAttribute("roles", roles);
        return "users/list";
    }

    @PostMapping("/disable/{id}")
    public String disableCount(@PathVariable Long id) {
        Optional<UserDTO> userDTO = userService.findOne(id);

        if (userDTO.isPresent()) {
            userDTO.get().setStatus(!userDTO.get().getStatus());
        }

        userService.save(userDTO.get());
        return "redirect:/users";
    }
}
