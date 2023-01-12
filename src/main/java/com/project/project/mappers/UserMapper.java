package com.project.project.mappers;

import com.project.project.dto.UserDto;
import com.project.project.models.User;
import org.mapstruct.Mapper;
@Mapper( componentModel = "spring", uses = {ReferenceMapper.class})
public interface UserMapper extends MapperInterface<User, UserDto> {
}
