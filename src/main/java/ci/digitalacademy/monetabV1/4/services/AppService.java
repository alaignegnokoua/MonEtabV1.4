package ci.digitalacademy.MonEtabV14.services;

import ci.digitalacademy.MonEtabV14.services.dto.AppSettingDTO;
import ci.digitalacademy.MonEtabV14.services.dto.RoleUserDTO;
import ci.digitalacademy.MonEtabV14.services.dto.SchoolDTO;

import java.util.List;

public interface AppService {

    AppSettingDTO initApp(AppSettingDTO appSettingDTO);

    SchoolDTO initSchool(SchoolDTO schoolDTO, AppSettingDTO appSettingDTO);

    List<RoleUserDTO> initRoles(List<RoleUserDTO> roleUserDTO);
}
