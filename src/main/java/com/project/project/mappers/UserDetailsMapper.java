package com.project.project.mappers;

import com.project.project.dto.UserDetailsDto;
import com.project.project.models.UserDetails;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring", uses = {ReferenceMapper.class})
public interface UserDetailsMapper extends MapperInterface<UserDetails, UserDetailsDto> {
}
