package ci.digitalacademy.MonEtabV14.services.impl;

import ci.digitalacademy.MonEtabV14.services.*;
import ci.digitalacademy.MonEtabV14.services.dto.AppSettingDTO;
import ci.digitalacademy.MonEtabV14.services.dto.RoleUserDTO;
import ci.digitalacademy.MonEtabV14.services.dto.SchoolDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AppServiceImpl implements AppService {

    private final AppSettingService appSettingService;
    private final SchoolService schoolService;
    private final RoleUserService roleUserService;
    private final UserService userService;

    @Override
    public AppSettingDTO initApp(AppSettingDTO appSettingDTO) {
        return appSettingService.initApp(appSettingDTO);
    }

    @Override
    public SchoolDTO initSchool(SchoolDTO schoolDTO, AppSettingDTO appSettingDTO) {
        schoolDTO.setAppSettingDTO(appSettingDTO);
        return schoolService.initSchool(schoolDTO);
    }

    @Override
    public List<RoleUserDTO> initRoles(List<RoleUserDTO> roleUsersDTO) {
        return roleUserService.initRoles(roleUsersDTO);
    }
}
