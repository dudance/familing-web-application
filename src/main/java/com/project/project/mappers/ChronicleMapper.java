package com.project.project.mappers;

import com.project.project.dto.ChronicleDto;
import com.project.project.models.Chronicle;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring", uses = {ReferenceMapper.class} )
public interface ChronicleMapper extends MapperInterface<Chronicle, ChronicleDto> {
}
