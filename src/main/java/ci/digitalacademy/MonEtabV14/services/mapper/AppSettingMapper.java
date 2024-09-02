package ci.digitalacademy.MonEtabV14.services.mapper;

import ci.digitalacademy.MonEtabV14.models.AppSetting;
import ci.digitalacademy.MonEtabV14.services.dto.AppSettingDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppSettingMapper extends EntityMapper<AppSettingDTO, AppSetting> {
}
