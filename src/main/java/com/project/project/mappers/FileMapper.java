package com.project.project.mappers;

import com.project.project.dto.FileDto;
import com.project.project.models.File;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring", uses = {ReferenceMapper.class})
public interface FileMapper extends MapperInterface<File, FileDto> {
}
