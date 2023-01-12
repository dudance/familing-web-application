package com.project.project.mappers;

import com.project.project.dto.UserFamilyDto;
import com.project.project.models.UserFamily;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring", uses = {ReferenceMapper.class} )
public interface UserFamilyMapper extends MapperInterface<UserFamily, UserFamilyDto> {
}
